package com.mjrfusion.app.allebook.reader.fragment

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mjrfusion.app.allebook.reader.R
import com.mjrfusion.app.allebook.reader.databinding.FragmentPdfReaderBinding
import com.mjrfusion.app.allebooks.core.fragment.BaseFragment
import com.mjrfusion.app.allebooks.documents_manager.api29.DocumentViewModel
import com.mjrfusion.app.allebooks.documents_manager.model.Document
import com.mjrfusion.app.allebooks.utils.Constants.DOCUMENT_OBJECT
import com.mjrfusion.app.allebooks.utils.ParcelableUtil

class PdfReaderFragment : BaseFragment(R.layout.fragment_pdf_reader) {
    private lateinit var binding: FragmentPdfReaderBinding
    private lateinit var pdfDoc: Document
    private lateinit var documentViewModel: DocumentViewModel

    override fun initializeFragment(view: View) {
        binding = FragmentPdfReaderBinding.bind(view)
        documentViewModel = ViewModelProvider(this)[DocumentViewModel::class.java]
    }

    override fun initializeAttributes() {
        pdfDoc = ParcelableUtil.getParcelable(requireArguments(), DOCUMENT_OBJECT, Document::class.java)
        if (pdfDoc.docStatus == Document.DocumentStatus.NEVER_OPENED)
            pdfDoc.apply {
                docStatus = Document.DocumentStatus.READING
                documentViewModel.update(this)
            }
        binding.pdfViewer.fromUri(pdfDoc.uri).onError {
            Log.d(tag, "initializeAttributes: Error: ${it.message}")
        }.onLoad {
            Toast.makeText(requireContext(), "Doc loaded", Toast.LENGTH_SHORT).show()
        }.load()
    }
}