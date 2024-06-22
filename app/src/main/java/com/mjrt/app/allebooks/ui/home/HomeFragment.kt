package com.mjrt.app.allebooks.ui.home

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mjrt.app.allebooks.R
import com.mjrt.app.allebooks.core.fragment.BaseFragment
import com.mjrt.app.allebooks.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun initializeFragment(view: View) {
        binding = FragmentHomeBinding.bind(view)
    }

    override fun initializeAttributes() {
        binding.docRecycler.layoutManager = LinearLayoutManager(requireContext())
    }
}