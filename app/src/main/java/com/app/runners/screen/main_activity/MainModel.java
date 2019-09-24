package com.app.runners.screen.main_activity;

import android.content.Context;

import com.app.runners.app.CoreApplication;
import com.app.runners.callbacks.ObjectCallback;
import com.app.runners.callbacks.SimpleCallback;
import com.app.runners.model.Runner;
import com.app.runners.screen.base.BaseModel;
import com.app.runners.service.database.interfaces.IRunnersDatabase;
import com.app.runners.service.repository.interfaces.IRunnersRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;


public class MainModel extends BaseModel {

    @Inject IRunnersRepository mRunnersRepository;
    @Inject IRunnersDatabase mRunnerDatabase;
    private String mRaceName;
    private Subscription mRaceSubscription;

    public MainModel(Context context) {
        super(context);
        CoreApplication.getComponent().inject(this);
    }

    /**
     * gets race details
     * @param successCallback
     * @param errorCallback
     */
    public void getRaceDetails(SimpleCallback successCallback, ObjectCallback<Throwable> errorCallback) {
        mRunnersRepository.getRunnersList(race -> {
            mRaceName = race.getName();
            successCallback.onCallback();
        }, errorCallback);
    }

    /**
     * returns list of runners in the race
     * @return
     */
    public List<Runner> getRunnersList() {
        return mRunnerDatabase.getListOfRunners(mRaceName);
    }

    /**
     * returns name of the race
     * @return
     */
    public String getTitle() {
        return mRaceName;
    }

    /**
     * subscribes to race details
     * @param callback
     */
    public void subscribeToRaceDetails(SimpleCallback callback) {
        mRaceSubscription = mRunnersRepository.subscribeToRaceDetail(mRaceName, race -> {
                    mRunnerDatabase.setRanking(mRaceName);
                    callback.onCallback();
                }
        );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRaceSubscription != null) {
            mRaceSubscription.unsubscribe();
            mRaceSubscription = null;
        }
    }
}
