package io.kaeawc.daggerexperiments

class NetworkState(var state: State? = null) {

    enum class State {
        Great,
        Good,
        Poor,
        None
    }
}
