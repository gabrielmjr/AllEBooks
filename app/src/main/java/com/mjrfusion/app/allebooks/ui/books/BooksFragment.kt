package com.mjrfusion.app.allebooks.ui.books

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.mjrfusion.app.allebook.reader.activity.ReaderActivity
import com.mjrfusion.app.allebooks.MainActivity
import com.mjrfusion.app.allebooks.R
import com.mjrfusion.app.allebooks.adapters.PdfDocumentsAdapter
import com.mjrfusion.app.allebooks.core.fragment.BaseFragment
import com.mjrfusion.app.allebooks.databinding.FragmentBooksBinding
import com.mjrfusion.app.allebooks.documents_manager.api29.DocumentViewModel
import com.mjrfusion.app.allebooks.documents_manager.model.Document
import com.mjrfusion.app.allebooks.documents_manager.utils.DocumentUtils
import com.mjrfusion.app.allebooks.utils.Constants.DOCUMENT_OBJECT
import com.mjrfusion.app.allebooks.utils.Constants.PDF_MIME_TYPE

class BooksFragment : BaseFragment(R.layout.fragment_books), PdfDocumentsAdapter.ClickListener {
    private lateinit var binding: FragmentBooksBinding
    private lateinit var documentViewModel: DocumentViewModel
    private lateinit var documents: ArrayList<Document>
    private lateinit var documentsAdapter: PdfDocumentsAdapter
    private var pickDocumentActivityLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument(), this::onDocumentPickedResult
    )

    override fun initializeFragment(view: View) {
        binding = FragmentBooksBinding.bind(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initializeAttributes() {
        documentViewModel = (baseActivity as MainActivity).documentsViewModel
        documents = ArrayList()
        documentsAdapter = PdfDocumentsAdapter(requireContext(), documents, this)
        binding.docRecycler.adapter = documentsAdapter
        binding.docRecycler.layoutManager = LinearLayoutManager(requireContext())
        documentViewModel.allDocuments.observeForever {
            documentsAdapter.pdfDocuments = it as ArrayList
            documents = it
            documentsAdapter.notifyDataSetChanged()
            Log.d(TAG, "onDocumentsLoaded: Docs  loaded " + it.size)
        }
        setFabOpenDocClickListener()
    }

    private fun setFabOpenDocClickListener() {
        binding.fabOpenDoc.setOnClickListener {
            getDocumentFromIntent()
        }
    }

    private fun getDocumentFromIntent() {
        pickDocumentActivityLauncher.launch(arrayOf(PDF_MIME_TYPE))
    }

    private fun onDocumentPickedResult(data: Uri?) {
        if (data != null) {
            val document = DocumentUtils.getDocument(data, baseActivity!!.contentResolver)
            baseActivity?.contentResolver?.takePersistableUriPermission(
                data, Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            document.apply {
                docStatus = Document.DocumentStatus.NEVER_OPENED
                isFavourite = false
            }
            save(document, data)
        }
    }

    private fun save(document: Document, data: Uri) {
        var shouldShowTheToast = true
        documentViewModel.existsByUri(data).observe(this) {
            run {
                if (it) {
                    if (shouldShowTheToast) {
                        Toast.makeText(
                            requireContext(),
                            "The document was already added.", Toast.LENGTH_LONG
                        ).show()
                        shouldShowTheToast = false
                    }
                } else {
                    documentViewModel.insert(document)
                    documents.add(document)
                    documentsAdapter.notifyItemInserted(documents.size)
                    shouldShowTheToast = false
                }
            }
        }
    }

    override fun onDocumentClicked(position: Int) {
        Intent(requireContext(), ReaderActivity::class.java).apply {
            putExtra(DOCUMENT_OBJECT, documents[position])
            startActivity(this)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        const val TAG = "HomeFragment"
    }
}