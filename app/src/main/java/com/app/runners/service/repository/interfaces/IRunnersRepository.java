package com.app.runners.service.repository.interfaces;

import com.app.runners.callbacks.ObjectCallback;
import com.app.runners.model.Race;

import io.realm.RealmResults;
import rx.Subscription;
import rx.functions.Action1;

public interface IRunnersRepository {

    void getRunnersList(ObjectCallback<Race> successCallback, ObjectCallback<Throwable> errorCallback);
    
    void cancelApiCalls();

    Subscription subscribeToRaceDetail(String mRaceName, Action1<RealmResults<Race>> callback);
}
