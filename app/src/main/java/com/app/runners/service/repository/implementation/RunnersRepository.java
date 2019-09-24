package com.app.runners.service.repository.implementation;

import android.content.Context;

import com.app.runners.app.CoreApplication;
import com.app.runners.callbacks.ObjectCallback;
import com.app.runners.model.Race;
import com.app.runners.service.api.interfaces.IRaceWebService;
import com.app.runners.service.database.interfaces.IRunnersDatabase;
import com.app.runners.service.repository.interfaces.IRunnersRepository;
import javax.inject.Inject;
import io.realm.RealmResults;
import rx.Subscription;
import rx.functions.Action1;

public class RunnersRepository implements IRunnersRepository {
    @Inject Context mContext;
    @Inject IRunnersDatabase mRunnersDatabase;
    @Inject IRaceWebService mRunnersWebService;

    public RunnersRepository() {
        CoreApplication.getComponent().inject(this);
    }

    @Override
    public void cancelApiCalls() {
        mRunnersWebService.cancelApiCalls();
    }

    /**
     * returns a subscription to return list of runners when there is an update in the database
     * @param  mRaceName is the name of the race
     * @param action realm results
     * @return
     */
    @Override
    public Subscription subscribeToRaceDetail(String mRaceName, Action1<RealmResults<Race>> action) {
        return mRunnersDatabase.subscribeToRaceDetail(mRaceName, action);
    }

    /**
     * @param successCallback is callback to return list of runners
     * @param errorCallback is return throwable on error
     */
    @Override
    public void getRunnersList(ObjectCallback<Race> successCallback, ObjectCallback<Throwable> errorCallback) {
        mRunnersWebService.getRaces(mContext, response -> {
            mRunnersDatabase.saveRace(response);
           successCallback.onCallback(response);
        }, errorCallback);
    }
}
