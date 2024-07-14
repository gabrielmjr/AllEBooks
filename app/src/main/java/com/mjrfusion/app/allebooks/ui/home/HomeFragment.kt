package com.mjrfusion.app.allebooks.ui.home

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mjrfusion.app.allebooks.R
import com.mjrfusion.app.allebooks.adapters.MainOptionsAdapter
import com.mjrfusion.app.allebooks.core.fragment.BaseFragment
import com.mjrfusion.app.allebooks.databinding.FragmentHomeBinding
import com.mjrfusion.app.allebooks.ui.collections.CollectionsFragment
import com.mjrfusion.app.allebooks.ui.reading.ReadingFragment
import com.mjrfusion.app.allebooks.utils.Constants.COLLECTIONS
import com.mjrfusion.app.allebooks.utils.Constants.READING

class HomeFragment : BaseFragment(R.layout.fragment_home), MainOptionsAdapter.ClickListener {
    private lateinit var binding: FragmentHomeBinding

    override fun initializeFragment(view: View) {
        binding = FragmentHomeBinding.bind(view)
    }

    override fun initializeAttributes() {
        ViewModelProvider(this)[MainOptionsViewModel::class.java].apply {
            binding.homeOptionsContainer.let {
                it.adapter = MainOptionsAdapter(
                    requireContext(),
                    this,
                    this@HomeFragment
                )
                it.setHasFixedSize(true)
            }
        }
    }

    override fun onItemClicked(position: Int) {
        when (position) {
            COLLECTIONS -> replaceFragmentBy(
                R.id.fragment_container,
                CollectionsFragment::class.java,
                tag = "collection-fragment",
                addToBackStack = true
            )

            READING -> replaceFragmentBy(
                R.id.fragment_container,
                ReadingFragment::class.java,
                tag = "reading-fragment",
                addToBackStack = true
            )
        }
    }
}