package io.kaeawc.daggerexperiments.ui

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import io.kaeawc.daggerexperiments.App
import io.kaeawc.daggerexperiments.R
import javax.inject.Inject

class MainActivity : Activity(), MainPresenter.MainViewActions {

    @Inject lateinit var presenter: MainPresenter

    lateinit var textView: TextView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App?)?.ui?.inject(this)

        textView = findViewById(R.id.hello_world) as TextView
        button = findViewById(R.id.button) as Button

        presenter.onCreate(this)

        button.setOnClickListener {
            presenter.onClick()
        }
    }

    override fun displayText(value: String) {
        textView.text = value
    }
}
