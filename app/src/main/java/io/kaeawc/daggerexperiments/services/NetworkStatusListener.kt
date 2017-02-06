package io.kaeawc.daggerexperiments.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import io.kaeawc.daggerexperiments.App
import io.kaeawc.daggerexperiments.NetworkState
import io.kaeawc.daggerexperiments.RxEventBus
import javax.inject.Inject

class NetworkStatusListener : BroadcastReceiver() {

    @Inject lateinit var bus: RxEventBus
    @Inject lateinit var networkState: NetworkState
    @Inject lateinit var connectivity: ConnectivityManager

    companion object {
        const val ACTION = "android.net.conn.CONNECTIVITY_CHANGE"
    }

    override fun onReceive(context: Context?, intent: Intent?) {

        if(context == null || intent == null || intent.action != ACTION) {
            return
        }

//        (context.applicationContext as App).service.inject(this)

        // Assume there is no connectivity if we can't determine
        if (connectivity.activeNetworkInfo?.isConnected ?: false) {
            networkState.state = NetworkState.State.Good
        } else {
            networkState.state = NetworkState.State.None
        }

        bus.send(networkState)
    }
}
