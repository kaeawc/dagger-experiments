package io.kaeawc.daggerexperiments.ui

import dagger.Subcomponent

@UiScope
@Subcomponent(modules = arrayOf(UiModule::class))
interface UiComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainInteractor: MainInteractor)

    @Subcomponent.Builder
    interface Builder {
        fun uiModule(module: UiModule): Builder
        fun build(): UiComponent
    }
}
