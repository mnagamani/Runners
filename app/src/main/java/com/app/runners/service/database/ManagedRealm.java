package com.app.runners.service.database;

import android.content.Context;
import android.util.Log;

import com.app.runners.service.database.interfaces.IManagedRealm;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ManagedRealm  implements IManagedRealm {
    
    private Context mContext;
    private static Realm mRealm;
    private static RealmConfiguration mRealmConfiguration;

    public ManagedRealm(Context context) {
        mContext = context;
    }
    
    public Realm getRealmInstance() {
        if (isRealmClosed()) {
            mRealm = Realm.getInstance(mRealmConfiguration);
            Log.d("IRealmProvider", "Realm instance created.");
        }
        return mRealm;
    }
    
    @Override
    public RealmConfiguration buildRealmConfig() {
        return new RealmConfiguration.Builder()
            .name("default.realm")
            .modules(Realm.getDefaultModule())
            .deleteRealmIfMigrationNeeded()
            .build();
    }
    
    @Override
    public void initRealm() {
        closeRealm();
        mRealmConfiguration = buildRealmConfig();
        
        try {
            mRealm = Realm.getInstance(mRealmConfiguration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteRealm() {
        
    }
    
    
    @Override
    public void closeRealm() {
        if (!isRealmClosed()) {
            mRealm.close();
            mRealm = null;
            Log.d("IRealmProvider", "Realm has been closed.");
        }
    }
    
    @Override
    public boolean isRealmClosed() {
        return (mRealm == null || mRealm.isClosed());
    }

    @Override
    public Context getContext() {
        return mContext;
    }
}
