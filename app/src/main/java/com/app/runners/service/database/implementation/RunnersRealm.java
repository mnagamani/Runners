package com.app.runners.service.database.implementation;


import com.app.runners.model.Race;
import com.app.runners.model.Runner;
import com.app.runners.service.database.interfaces.IManagedRealm;
import com.app.runners.service.database.interfaces.IRunnersDatabase;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Subscription;
import rx.functions.Action1;

public class RunnersRealm extends BaseRealmHelper implements IRunnersDatabase {
    
    public RunnersRealm(IManagedRealm managedRealm) {
        super(managedRealm);
    }

    /**
     * returns the list of runners
     * @param name of the name
     * @return
     */
    @Override
    public List<Runner> getListOfRunners(String name) {
        return getRealm().where(Race.class)
                    .equalTo(Race.NAME, name)
                    .findFirst().getRunnersList().sort(Runner.TIME, Sort.ASCENDING);
    }

    /**
     * save the race to database
     * @param  race
     */
    @Override
    public void saveRace(Race race) {
        executeTransactionAsync(realm -> {
            realm.copyToRealmOrUpdate(race);
        });
    }

    /**
     * sets the rank of the runners based on the time and in the age group
     * @param  name of the race
     */
    @Override
    public void setRanking(String name) {
        getRealm().executeTransactionAsync(realm -> {
            RealmResults<Runner> runnerListForAgeGroup1 =  realm.where(Race.class)
                    .equalTo(Race.NAME, name)
                    .findFirst().getRunnersList().where().beginGroup().between(Runner.AGE, 0, 15).endGroup().findAll().sort(Runner.TIME, Sort.ASCENDING);

            RealmResults<Runner> runnerListForAgeGroup2 =  realm.where(Race.class)
                    .equalTo(Race.NAME, name)
                    .findFirst().getRunnersList().where().beginGroup().between(Runner.AGE, 16, 29).endGroup().findAll().sort(Runner.TIME, Sort.ASCENDING);
            RealmResults<Runner> runnerListForAgeGroup3 =  realm.where(Race.class)
                    .equalTo(Race.NAME, name)
                    .findFirst().getRunnersList().where().beginGroup().greaterThan(Runner.AGE, 29).endGroup().findAll().sort(Runner.TIME, Sort.ASCENDING);

            setRank(runnerListForAgeGroup1, realm);
            setRank(runnerListForAgeGroup2, realm);
            setRank(runnerListForAgeGroup3, realm);
        });
    }

    /**
     * set rank based on time taken for the race
     * @param  runners is the list of runners for the age group
     * @param  realm
     */
    private void setRank(RealmResults<Runner> runners, Realm realm){
        for(int i = 0; i < runners.size() ;  i++){
            if(i != 0 && runners.get(i).getTime() == runners.get(i - 1).getTime()){
                runners.get(i).setRank(runners.get(i - 1).getRank());
            } else {
                runners.get(i).setRank(i + 1);
            }
        }
       realm.copyToRealm(runners);
    }

    /**
     * returns subscription
     * @param name of the race
     * @param action
     */
    @Override
    public Subscription subscribeToRaceDetail(String name, Action1<RealmResults<Race>> action) {
        RealmResults<Race> races = getRealm().where(Race.class).equalTo(Race.NAME, name).findAll();
        if (races.size() == 0) {
            getRealm().beginTransaction();
            getRealm().createObject(Race.class, name);
            getRealm().commitTransaction();
            races = getRealm().where(Race.class).equalTo(Race.NAME, name).findAll();
        }
        return races.asObservable().subscribe(action);
    }

}
