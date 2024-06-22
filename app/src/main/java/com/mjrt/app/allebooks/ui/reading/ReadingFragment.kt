package com.mjrt.app.allebooks.ui.reading

import android.view.View
import com.mjrt.app.allebooks.R
import com.mjrt.app.allebooks.core.fragment.BaseFragment
import com.mjrt.app.allebooks.databinding.FragmentReadingBinding

class ReadingFragment : BaseFragment(R.layout.fragment_reading) {
    private lateinit var binding: FragmentReadingBinding

    override fun initializeFragment(view: View) {
        binding = FragmentReadingBinding.bind(view)
    }

    override fun initializeAttributes() {
    }
}