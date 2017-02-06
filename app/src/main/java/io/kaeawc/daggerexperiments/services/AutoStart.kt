package io.kaeawc.daggerexperiments.services

import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context

class AutoStart : BroadcastReceiver() {

    override fun onReceive(context: Context, i: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == i.action) {
            BackgroundService.start(context)
        }
    }
}
