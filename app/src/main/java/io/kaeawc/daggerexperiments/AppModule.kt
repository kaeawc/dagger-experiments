package io.kaeawc.daggerexperiments

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.util.DisplayMetrics
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

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
}
