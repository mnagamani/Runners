package com.app.runners.service.dagger.component;

import com.app.runners.screen.main_activity.MainModel;
import com.app.runners.service.api.implementation.RaceWebService;
import com.app.runners.service.dagger.module.AppServiceModule;
import com.app.runners.service.dagger.module.DatabaseServiceModule;
import com.app.runners.service.dagger.module.RealmModule;
import com.app.runners.service.dagger.module.RunnersRepositoryServiceModule;
import com.app.runners.service.dagger.module.WebServiceModule;
import com.app.runners.service.repository.implementation.RunnersRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
    WebServiceModule.class,
    DatabaseServiceModule.class,
    RunnersRepositoryServiceModule.class,
    AppServiceModule.class,
    RealmModule.class
})

public interface RunnersApplicationComponent {

    void inject(RaceWebService raceWebService);

    void inject(RunnersRepository runnersRepository);

    void inject(MainModel mainModel);
}
