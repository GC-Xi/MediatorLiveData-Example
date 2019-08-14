package com.xi_zz.livedataexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    private val _gateOneState = MutableLiveData(true)
    private val _gateTwoState = MutableLiveData(true)

    val gateOneState: LiveData<Boolean> = _gateOneState
    val gateTwoState: LiveData<Boolean> = _gateTwoState

    val showAndGate: LiveData<Boolean> = comblineLiveData(gateOneState, gateTwoState) { gate1, gate2 -> gate1 == true && gate2 == true }
    val showOrGate: LiveData<Boolean> = gateOneState.combine(gateTwoState) { gate1, gate2 -> gate1 == true || gate2 == true }

    fun taggleGateOne() {
        _gateOneState.value = _gateOneState.value != true
    }

    fun taggleGateTwo() {
        _gateTwoState.value = _gateTwoState.value != true
    }
}