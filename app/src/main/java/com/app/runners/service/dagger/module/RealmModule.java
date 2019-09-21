package com.app.runners.service.dagger.module;

import com.app.runners.model.Race;
import com.app.runners.model.Runner;
import com.app.runners.service.database.ManagedRealm;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
@io.realm.annotations.RealmModule(classes={ Race.class, Runner.class})
public class RealmModule {

    private ManagedRealm mManagedRealm;

    public RealmModule(ManagedRealm managedRealm) {
        mManagedRealm = managedRealm;
    }

    @Singleton
    @Provides
    public ManagedRealm provideManagedRealm() { return mManagedRealm; }

}
