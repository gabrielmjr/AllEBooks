package com.mjrfusion.app.allebooks.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mjrfusion.app.allebooks.R
import com.mjrfusion.app.allebooks.model.MainOption

class MainOptionsViewModel : ViewModel() {
    private var _mainOptions = MutableLiveData<List<MainOption>>().apply {
        value = listOf(
            MainOption(
                R.drawable.ic_books_collection,
                R.string.collections
            ),
            MainOption(
                R.drawable.ic_opened_book,
                R.string.reading
            ),
            MainOption(
                R.drawable.ic_favourite_book,
                R.string.favourite_books
            ),
            MainOption(
                R.drawable.ic_settings,
                R.string.settings
            )
        )
    }

    var mainOptions: LiveData<List<MainOption>> = _mainOptions
}