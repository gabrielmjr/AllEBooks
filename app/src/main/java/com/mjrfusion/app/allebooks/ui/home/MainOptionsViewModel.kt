package com.mjrfusion.app.allebooks.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mjrfusion.app.allebooks.R
import com.mjrfusion.app.allebooks.model.Main_Option

class MainOptionsViewModel : ViewModel() {
    private var _mainOptions = MutableLiveData<List<Main_Option>>().apply {
        value = listOf(
            Main_Option(R.drawable.ic_home, R.string.menu_home),
            Main_Option(R.drawable.ic_closed_book, R.string.menu_read)
        )
    }

    var mainOptions: LiveData<List<Main_Option>> = _mainOptions
}