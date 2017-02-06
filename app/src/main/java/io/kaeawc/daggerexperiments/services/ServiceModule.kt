package io.kaeawc.daggerexperiments.services

import dagger.Module
import dagger.Provides

@Module
class ServiceModule {

    @ServiceScope
    @Provides
    fun provideServiceInteractor(): ServiceInteractor = ServiceInteractor()
}
