package com.xi_zz.livedataexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T, A, B> comblineLiveData(liveData1: LiveData<A>, liveData2: LiveData<B>, onChange: (A?, B?) -> T): MediatorLiveData<T> =
    MediatorLiveData<T>().apply {
        addSource(liveData1) { value = onChange(liveData1.value, liveData2.value) }
        addSource(liveData2) { value = onChange(liveData1.value, liveData2.value) }
    }

fun <T, A, B> LiveData<A>.combine(that: LiveData<B>, onChange: (A?, B?) -> T): MediatorLiveData<T> =
    MediatorLiveData<T>().apply {
        addSource(this@combine) { value = onChange(this@combine.value, that.value) }
        addSource(that) { value = onChange(this@combine.value, that.value) }
    }
