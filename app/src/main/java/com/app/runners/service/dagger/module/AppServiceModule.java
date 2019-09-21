package com.app.runners.service.dagger.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppServiceModule {

    private Context mApplicationContext;

    public AppServiceModule(Context context) {
        mApplicationContext = context;
    }

    @Provides
    public Context provideApplicationContext() {
        return mApplicationContext;
    }
}
