package com.sona.babu88.data.viewmodel

import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        this.onCleared()
    }
}