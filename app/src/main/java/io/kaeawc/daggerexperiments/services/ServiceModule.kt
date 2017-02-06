package io.kaeawc.daggerexperiments.services

import dagger.Module
import dagger.Provides

@Module
abstract class ServiceModule {

    @ServiceScope
    @Provides
    fun provideServiceInteractor(): ServiceInteractor = ServiceInteractor()
}
