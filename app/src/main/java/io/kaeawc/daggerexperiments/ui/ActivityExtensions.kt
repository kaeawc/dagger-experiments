package io.kaeawc.daggerexperiments.ui

import android.app.Activity
import io.kaeawc.daggerexperiments.App

fun Activity.getApp(): App? = if (application == null) null else application as App?

fun Activity.ui(): UiComponent? = getApp()?.ui
