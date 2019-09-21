package com.app.runners.service.database.interfaces;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public interface IManagedRealm {
    Context getContext();
    Realm getRealmInstance();
    RealmConfiguration buildRealmConfig();
    void initRealm();
    void deleteRealm();
    boolean isRealmClosed();
    void closeRealm();
}
