package com.app.runners.service.database.interfaces;

import com.app.runners.model.Race;
import com.app.runners.model.Runner;

import java.util.List;

import io.realm.RealmResults;
import rx.Subscription;
import rx.functions.Action1;

public interface IRunnersDatabase {

    List<Runner> getListOfRunners(String name);

    void saveRace(Race race);

    void setRanking(String name);

    Subscription subscribeToRaceDetail(String name, Action1<RealmResults<Race>> action);
}
