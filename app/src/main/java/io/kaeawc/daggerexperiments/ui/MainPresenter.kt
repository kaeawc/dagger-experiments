package io.kaeawc.daggerexperiments.ui

class MainPresenter {

    lateinit var actions: MainViewActions

    fun onCreate(listener: MainViewActions) {
        actions = listener
    }

    fun onClick() {
        actions.displayText("HI!")
    }

    interface MainViewActions {

        fun displayText(value: String)

    }
}
