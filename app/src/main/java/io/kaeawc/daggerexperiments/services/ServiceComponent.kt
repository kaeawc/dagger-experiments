package io.kaeawc.daggerexperiments.services

import dagger.Subcomponent

@ServiceScope
@Subcomponent(modules = arrayOf(ServiceModule::class))
interface ServiceComponent {

    fun inject(backgroundService: BackgroundService)
//    fun inject(networkStatusListener: NetworkStatusListener)

    @Subcomponent.Builder
    interface Builder {
        fun serviceModule(module: ServiceModule): Builder
        fun build(): ServiceComponent
    }
}