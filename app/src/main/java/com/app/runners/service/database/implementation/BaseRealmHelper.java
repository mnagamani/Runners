package com.app.runners.service.database.implementation;

import android.content.Context;

import com.app.runners.callbacks.ObjectCallback;
import com.app.runners.callbacks.SimpleCallback;
import com.app.runners.service.database.interfaces.IManagedRealm;
import com.app.runners.service.database.interfaces.IRealmDatabase;

import io.realm.Realm;

public class BaseRealmHelper  implements IRealmDatabase {
    
    private IManagedRealm mManagedRealm;
    
    public BaseRealmHelper(IManagedRealm managedRealm) {
        mManagedRealm = managedRealm;
    }
    
    public Realm getRealm() {
        return mManagedRealm.getRealmInstance();
    }
    
    @Override
    public void executeTransaction(Realm.Transaction transaction) {
        try {
            getRealm().executeTransaction(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void executeTransactionAsync(Realm.Transaction transaction) {
        try {
            getRealm().executeTransactionAsync(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executeTransactionAsync(Realm.Transaction transaction, SimpleCallback successCallback) {
        try {
            getRealm().executeTransactionAsync(transaction, successCallback::onCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executeTransactionAsync(Realm.Transaction transaction, SimpleCallback successCallback, ObjectCallback<Throwable> errorCallback) {
        try {
            getRealm().executeTransactionAsync(transaction, successCallback::onCallback, errorCallback::onCallback);
        } catch (Exception e) {
            e.printStackTrace();
            errorCallback.onCallback(e);
        }
    }
    
    public boolean isClosed() {
        boolean isClosed = true;
        
        try {
            isClosed = getRealm().isClosed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isClosed;
    }
    
    protected Context getContext() {
        return mManagedRealm.getContext();
    }}
