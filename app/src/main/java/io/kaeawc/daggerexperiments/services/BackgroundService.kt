package io.kaeawc.daggerexperiments.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import io.kaeawc.daggerexperiments.App
import io.kaeawc.daggerexperiments.NetworkState
import io.kaeawc.daggerexperiments.RxEventBus
import javax.inject.Inject

class BackgroundService : Service() {

    @Inject lateinit var interactor: ServiceInteractor
    @Inject lateinit var bus: RxEventBus
    @Inject lateinit var networkState: NetworkState

    private val binder = LocalBinder()
    private var running = false

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (!running) {
            initialize()
        }

        return Service.START_STICKY
    }

    fun initialize() {
        running = true

        (application as App).service.inject(this)

        bus.onBackgroundThread(NetworkState::class.java).subscribe {
            Log.i("Service", "Network state changed to ${networkState.state}")
        }
    }

    override fun onBind(intent: Intent?): IBinder = binder

    inner class LocalBinder : Binder() {
        val service: BackgroundService
            get() { return this@BackgroundService }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, BackgroundService::class.java)
            val name = context.startService(intent)
            when (name) {
                null -> { Log.i("Service", "starting") }
                else -> { Log.i("Service", "already running") }
            }
        }
    }
}
