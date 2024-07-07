package com.mjrfusion.app.allebook.reader.fragment

import android.util.Log
import android.view.View
import android.widget.Toast
import com.mjrfusion.app.allebook.reader.R
import com.mjrfusion.app.allebook.reader.databinding.FragmentPdfReaderBinding
import com.mjrfusion.app.allebooks.core.fragment.BaseFragment
import com.mjrfusion.app.allebooks.documents_manager.Document
import com.mjrfusion.app.allebooks.utils.Constants.DOCUMENT_OBJECT
import com.mjrfusion.app.allebooks.utils.ParcelableUtil

class PdfReaderFragment : BaseFragment(R.layout.fragment_pdf_reader) {
    private lateinit var binding: FragmentPdfReaderBinding
    private lateinit var pdfDoc: Document

    override fun initializeFragment(view: View) {
        binding = FragmentPdfReaderBinding.bind(view)
    }

    override fun initializeAttributes() {
        pdfDoc = ParcelableUtil.getParcelable(requireArguments(), DOCUMENT_OBJECT, Document::class.java)
        binding.pdfViewer.fromUri(pdfDoc.uri).onError {
            Log.d(tag, "initializeAttributes: Error: ${it.message}")
        }.onLoad {
            Toast.makeText(requireContext(), "Doc loaded", Toast.LENGTH_SHORT).show()
        }.load()
    }
}