package com.app.runners.service.dagger.module;


import com.app.runners.service.repository.implementation.RunnersRepository;
import com.app.runners.service.repository.interfaces.IRunnersRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RunnersRepositoryServiceModule {
    
    public RunnersRepositoryServiceModule() {
    }
    
    @Provides
    public IRunnersRepository provideRunnersRepository() {
        return new RunnersRepository();
    }
    
}
