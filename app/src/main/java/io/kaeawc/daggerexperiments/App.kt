package io.kaeawc.daggerexperiments

import android.app.Application
import io.kaeawc.daggerexperiments.services.BackgroundService
import io.kaeawc.daggerexperiments.services.ServiceComponent
import io.kaeawc.daggerexperiments.ui.UiComponent
import javax.inject.Inject
import android.os.StrictMode



class App : Application() {

    @Inject lateinit var ui: UiComponent
    @Inject lateinit var service: ServiceComponent

    lateinit var graph: AppComponent

    override fun onCreate() {
        super.onCreate()

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build())
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build())

        graph = AppComponent.init(this)
        graph.inject(this)
        BackgroundService.start(this)
    }
}
