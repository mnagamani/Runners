package com.app.runners.app;

import android.app.Application;

import com.app.runners.service.dagger.component.DaggerRunnersApplicationComponent;
import com.app.runners.service.dagger.component.RunnersApplicationComponent;
import com.app.runners.service.dagger.module.AppServiceModule;
import com.app.runners.service.dagger.module.DatabaseServiceModule;
import com.app.runners.service.dagger.module.RealmModule;
import com.app.runners.service.dagger.module.RunnersRepositoryServiceModule;
import com.app.runners.service.dagger.module.WebServiceModule;
import com.app.runners.service.database.ManagedRealm;
import com.app.runners.service.database.implementation.DatabaseInitializer;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;


public class CoreApplication extends Application {
    private static ManagedRealm mManagedRealm;
    private static RunnersApplicationComponent mComponent;
    
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        initDatabaseManager();
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                .build());
        mComponent = initializeAppComponent(this);
    }
    
    private void initDatabaseManager() {
        mManagedRealm = new ManagedRealm(this);
        new DatabaseInitializer().initDatabase(mManagedRealm);
    }
    
    public static ManagedRealm getManagedRealm() {
        return mManagedRealm;
    }
    
    public  RunnersApplicationComponent initializeAppComponent(CoreApplication application) {
        
        WebServiceModule webServiceModule = new WebServiceModule(application);
        DatabaseServiceModule databaseServiceModule = new DatabaseServiceModule(application.getManagedRealm());
        return DaggerRunnersApplicationComponent.builder()
            .webServiceModule(webServiceModule)
            .databaseServiceModule(databaseServiceModule)
            .runnersRepositoryServiceModule(new RunnersRepositoryServiceModule())
            .realmModule(new RealmModule(application.getManagedRealm()))
            .appServiceModule(new AppServiceModule(application))
            .build();
    }
    
    public static RunnersApplicationComponent getComponent() {
        return mComponent;
    }
    
}
