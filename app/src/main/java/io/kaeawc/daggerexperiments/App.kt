package io.kaeawc.daggerexperiments

import android.app.Activity
import android.app.Application
import io.kaeawc.daggerexperiments.ui.UiComponent
import javax.inject.Inject

class App : Application() {

    // @Inject lateinit var componentBuilders: Map<Class<in Activity>, ComponentBuilder>

    @Inject lateinit var ui: UiComponent

    lateinit var graph: AppComponent

    override fun onCreate() {
        super.onCreate()

        graph = AppComponent.init(this)
        graph.inject(this)
    }
}
