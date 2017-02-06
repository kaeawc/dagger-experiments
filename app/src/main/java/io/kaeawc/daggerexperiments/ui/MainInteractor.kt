package io.kaeawc.daggerexperiments.ui

import io.kaeawc.daggerexperiments.NetworkState
import io.kaeawc.daggerexperiments.RxEventBus
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainInteractor {

    @Inject lateinit var bus: RxEventBus

    lateinit var changes: DataChanges

    lateinit var networkState: Disposable

    fun onCreate(component: UiComponent, listener: DataChanges) {
        component.inject(this)
        changes = listener
        networkState = bus.onMainThread(NetworkState::class.java).subscribe {
            changes.onNetworkChanged(it.state.toString())
        }
    }

    fun onDestroy() {
        if (!networkState.isDisposed) networkState.dispose()
    }

    interface DataChanges {

        fun onNetworkChanged(value: String)

    }
}