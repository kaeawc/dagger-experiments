package io.kaeawc.daggerexperiments

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.util.DisplayMetrics
import dagger.Module
import dagger.Provides
import io.kaeawc.daggerexperiments.services.ServiceComponent
import io.kaeawc.daggerexperiments.services.ServiceModule
import io.kaeawc.daggerexperiments.ui.UiComponent
import io.kaeawc.daggerexperiments.ui.UiModule
import javax.inject.Singleton

@Module(subcomponents = arrayOf(ServiceComponent::class, UiComponent::class))
class AppModule(val app: Application) {

    companion object {
        private const val SHARED_PREFS_NAME = "default"
    }

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideResources(): Resources = app.resources

    @Provides
    @Singleton
    fun provideDisplayMetrics(): DisplayMetrics = app.resources.displayMetrics

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return app.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideUi(uiModule: UiModule, builder: UiComponent.Builder): UiComponent {
        return builder.uiModule(uiModule).build()
    }

    @Provides
    @Singleton
    fun provideUiModule() = UiModule()

    @Provides
    @Singleton
    fun provideService(serviceModule: ServiceModule, builder: ServiceComponent.Builder): ServiceComponent {
        return builder.serviceModule(serviceModule).build()
    }
}
