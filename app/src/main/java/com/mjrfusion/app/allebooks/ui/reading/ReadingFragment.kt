package com.mjrfusion.app.allebooks.ui.reading

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mjrfusion.app.allebooks.MainActivity
import com.mjrfusion.app.allebooks.R
import com.mjrfusion.app.allebooks.adapters.PdfDocumentsAdapter
import com.mjrfusion.app.allebooks.core.fragment.BaseFragment
import com.mjrfusion.app.allebooks.databinding.FragmentReadingBinding
import com.mjrfusion.app.allebooks.documents_manager.api29.DocumentViewModel

class ReadingFragment : BaseFragment(R.layout.fragment_reading), PdfDocumentsAdapter.ClickListener {
    private lateinit var binding: FragmentReadingBinding
    private lateinit var documentViewModel: DocumentViewModel
    private lateinit var documentAdapter: PdfDocumentsAdapter

    override fun initializeFragment(view: View) {
        binding = FragmentReadingBinding.bind(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initializeAttributes() {
        documentViewModel = (baseActivity as MainActivity).documentsViewModel
        documentAdapter = PdfDocumentsAdapter(requireContext(), ArrayList(), this)
        binding.readingDocs.adapter = documentAdapter
        binding.readingDocs.layoutManager = LinearLayoutManager(requireContext())
        documentViewModel.allReadingDocs.observeForever {
            documentAdapter.pdfDocuments = it as ArrayList
            documentAdapter.notifyDataSetChanged()
        }
    }

    override fun onDocumentClicked(position: Int) {
        TODO("Not yet implemented")
    }
}