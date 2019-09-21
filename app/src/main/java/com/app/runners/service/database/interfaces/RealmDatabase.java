package com.app.runners.service.database.interfaces;

import com.app.runners.callbacks.ObjectCallback;
import com.app.runners.callbacks.SimpleCallback;

import io.realm.Realm;

public interface RealmDatabase {
    Realm getRealm();
    boolean isClosed();
    void executeTransaction(Realm.Transaction transaction);
    void executeTransactionAsync(Realm.Transaction transaction);
    void executeTransactionAsync(Realm.Transaction transaction, SimpleCallback successCallback);
    void executeTransactionAsync(Realm.Transaction transaction, SimpleCallback successCallback, ObjectCallback<Throwable> errorCallback);
}
