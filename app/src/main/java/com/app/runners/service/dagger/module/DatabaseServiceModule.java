package com.app.runners.service.dagger.module;

import com.app.runners.service.database.implementation.RunnersRealm;
import com.app.runners.service.database.interfaces.IManagedRealm;
import com.app.runners.service.database.interfaces.IRunnersDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseServiceModule {
    private IManagedRealm mManagedRealm;
    
    public DatabaseServiceModule(IManagedRealm managedRealm) {
        mManagedRealm = managedRealm;
    }
    
    @Singleton
    @Provides
    public IRunnersDatabase provideRunnersDatabase() {
        return new RunnersRealm(mManagedRealm);
    }
    
    @Provides
    @Singleton
    public IManagedRealm provideManagedRealm(){
        return mManagedRealm;
    }
    
}
