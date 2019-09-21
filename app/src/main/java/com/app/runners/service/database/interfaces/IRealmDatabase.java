package com.app.runners.service.database.interfaces;

import io.realm.Realm;

public interface IRealmDatabase extends IDatabase {
    Realm getRealm();
    boolean isClosed();
}
