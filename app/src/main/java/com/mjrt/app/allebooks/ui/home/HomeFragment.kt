package com.mjrt.app.allebooks.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mjrt.app.allebooks.MainActivity
import com.mjrt.app.allebooks.adapters.PdfDocumentsAdapter
import com.mjrt.app.allebooks.databinding.FragmentHomeBinding
import com.mjrt.app.allebooks.document_manager.DocumentManager
import com.mjrt.app.allebooks.fragments.BaseFragment

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var documentManager: DocumentManager
    private lateinit var pdfDocumentsAdapter: PdfDocumentsAdapter

    override fun initializeFragment(inflator: LayoutInflater): View {
        binding = FragmentHomeBinding.inflate(inflator)
        return binding.root
    }

    override fun initializeAttributes() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.docRecycler.layoutManager = LinearLayoutManager(requireContext())
        documentManager = (requireActivity() as MainActivity).documentManager
        pdfDocumentsAdapter = (requireActivity() as MainActivity).pdfDocumentsAdapter
        binding.docRecycler.adapter = pdfDocumentsAdapter
        setViewModerObserver()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setViewModerObserver() {
        homeViewModel.pdfDocuments.observe(viewLifecycleOwner) {
            pdfDocumentsAdapter.pdfDocuments = it
            pdfDocumentsAdapter.notifyDataSetChanged()
        }
    }
}