package io.kaeawc.daggerexperiments

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun appModule(module: AppModule): Builder
        fun build(): AppComponent
    }

    companion object {
        fun init(app: App): AppComponent {
            return DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()
        }
    }
}
