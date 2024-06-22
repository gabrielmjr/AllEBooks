package com.mjrt.app.allebooks.ui.read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mjrt.app.allebooks.R
import com.mjrt.app.allebooks.core.fragment.BaseFragment
import com.mjrt.app.allebooks.databinding.FragmentReadBinding

class ReadFragment : BaseFragment(R.layout.fragment_read) {

    private lateinit var binding: FragmentReadBinding

    override fun initializeFragment(view: View) {
        binding = FragmentReadBinding.bind(view)
    }

    override fun initializeAttributes() {

    }
}