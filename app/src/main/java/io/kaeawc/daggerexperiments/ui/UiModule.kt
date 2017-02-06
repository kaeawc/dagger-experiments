package io.kaeawc.daggerexperiments.ui

import dagger.Module
import dagger.Provides

@Module
class UiModule {

    @UiScope
    @Provides
    fun provideMainPresenter(): MainPresenter = MainPresenter()

    @UiScope
    @Provides
    fun provideMainInteractor(): MainInteractor = MainInteractor()
}
