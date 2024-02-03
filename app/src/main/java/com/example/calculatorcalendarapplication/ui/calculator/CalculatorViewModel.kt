package com.example.calculatorcalendarapplication.ui.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    private val _value = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _value
}