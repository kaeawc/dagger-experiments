package io.kaeawc.daggerexperiments.ui

import io.kaeawc.daggerexperiments.NetworkState
import io.kaeawc.daggerexperiments.RxEventBus
import javax.inject.Inject

class MainInteractor {

    @Inject lateinit var bus: RxEventBus

    lateinit var changes: DataChanges

    fun onCreate(component: UiComponent, listener: DataChanges) {
        component.inject(this)
        changes = listener
        bus.onMainThread(NetworkState::class.java).subscribe {
            changes.onNetworkChanged(it.state.toString())
        }
    }

    interface DataChanges {

        fun onNetworkChanged(value: String)

    }
}