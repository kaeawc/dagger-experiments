package io.kaeawc.daggerexperiments.ui

interface ActivityComponentBuilder<M : UiModule, C : UiComponent> {
    fun activityModule(activityModule: M): ActivityComponentBuilder<M, C>
    fun build(): C
}
