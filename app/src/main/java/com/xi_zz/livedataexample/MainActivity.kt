package com.xi_zz.livedataexample

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.andGateText
import kotlinx.android.synthetic.main.activity_main.finishButton
import kotlinx.android.synthetic.main.activity_main.gateOneButton
import kotlinx.android.synthetic.main.activity_main.gateTwoButton
import kotlinx.android.synthetic.main.activity_main.orGateText
import kotlinx.android.synthetic.main.activity_main.showDialogButton

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.showAndGate.observe(this, Observer { andGateText.isVisible = it })
        viewModel.showOrGate.observe(this, Observer { orGateText.isVisible = it })
        viewModel.gateOneState.observe(this, Observer { gateOneButton.text = if (it) "On" else "Off" })
        viewModel.gateTwoState.observe(this, Observer { gateTwoButton.text = if (it) "On" else "Off" })

        gateOneButton.setOnClickListener { viewModel.taggleGateOne() }
        gateTwoButton.setOnClickListener { viewModel.taggleGateTwo() }

        // Navigation
        viewModel.showDialog.observe(this, Observer {
            AlertDialog.Builder(this).apply {
                setTitle("Hello")
                setMessage("How are you?")
            }.show()
        })
        viewModel.finish.observe(this, Observer { finish() })

        showDialogButton.setOnClickListener { viewModel.showDialog() }
        finishButton.setOnClickListener { viewModel.finish() }
    }


}
