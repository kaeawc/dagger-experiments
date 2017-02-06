package io.kaeawc.daggerexperiments.ui

import javax.inject.Inject

class MainPresenter : MainInteractor.DataChanges {

    override fun onNetworkChanged(value: String) {
        actions.displayText("Network is $value")
    }

    @Inject lateinit var interactor: MainInteractor

    lateinit var actions: MainViewActions

    fun onCreate(component: UiComponent, listener: MainViewActions) {
        component.inject(this)
        actions = listener
        interactor.onCreate(component, this)
    }

    fun onDestroy() {
        interactor.onDestroy()
    }

    fun onClick() {
        actions.displayText("HI!")
    }

    interface MainViewActions {

        fun displayText(value: String)

    }
}
