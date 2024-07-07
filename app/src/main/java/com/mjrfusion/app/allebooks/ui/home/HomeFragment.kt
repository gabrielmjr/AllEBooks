package com.mjrfusion.app.allebooks.ui.home

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mjrfusion.app.allebooks.R
import com.mjrfusion.app.allebooks.core.fragment.BaseFragment
import com.mjrfusion.app.allebooks.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun initializeFragment(view: View) {
        binding = FragmentHomeBinding.bind(view)
    }

    override fun initializeAttributes() {
        ViewModelProvider(this)[MainOptionsViewModel::class.java].apply {

        }
    }
}