package com.app.runners.service.database.implementation;

import com.app.runners.service.database.ManagedRealm;

public class DatabaseInitializer {

    public DatabaseInitializer() {

    }
    
    public void initDatabase(ManagedRealm managedRealm) {
        managedRealm.initRealm();
    }
}
