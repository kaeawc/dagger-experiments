package io.kaeawc.daggerexperiments.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import io.kaeawc.daggerexperiments.App
import io.kaeawc.daggerexperiments.NetworkState
import io.kaeawc.daggerexperiments.RxEventBus
import javax.inject.Inject

class NetworkStatusListener : BroadcastReceiver() {

    @Inject lateinit var bus: RxEventBus
    @Inject lateinit var networkState: NetworkState
    @Inject lateinit var connectivity: ConnectivityManager

    var injected: Boolean = false

    companion object {
        const val ACTION = ConnectivityManager.CONNECTIVITY_ACTION

        fun getFilter() = IntentFilter(ACTION)
    }

    override fun onReceive(context: Context?, intent: Intent?) {

        if(context == null || intent == null || intent.action != ACTION) {
            return
        }

        if (!injected) {
            (context.applicationContext as App).service.inject(this)
        }

        // Assume there is no connectivity if we can't determine
        val previous = networkState.state
        if (connectivity.activeNetworkInfo?.isConnected ?: false) {
            networkState.state = NetworkState.State.Good
        } else {
            networkState.state = NetworkState.State.None
        }

        if (previous != null && previous != networkState.state) {
            bus.send(networkState)
        }
    }
}
