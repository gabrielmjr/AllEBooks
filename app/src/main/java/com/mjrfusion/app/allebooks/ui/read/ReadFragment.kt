package com.mjrfusion.app.allebooks.ui.read

import android.view.View
import com.mjrfusion.app.allebooks.R
import com.mjrfusion.app.allebooks.core.fragment.BaseFragment
import com.mjrfusion.app.allebooks.databinding.FragmentReadBinding

class ReadFragment : BaseFragment(R.layout.fragment_read) {

    private lateinit var binding: FragmentReadBinding

    override fun initializeFragment(view: View) {
        binding = FragmentReadBinding.bind(view)
    }

    override fun initializeAttributes() {

    }
}