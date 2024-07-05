package com.mjrfusion.app.allebooks.ui.read

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReadViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Show read books"
    }
    val text: LiveData<String> = _text
}