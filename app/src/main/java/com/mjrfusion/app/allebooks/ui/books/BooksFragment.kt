package com.mjrfusion.app.allebooks.ui.books

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mjrfusion.app.allebook.reader.activity.ReaderActivity
import com.mjrfusion.app.allebooks.MainActivity
import com.mjrfusion.app.allebooks.R
import com.mjrfusion.app.allebooks.adapters.PdfDocumentsAdapter
import com.mjrfusion.app.allebooks.core.fragment.BaseFragment
import com.mjrfusion.app.allebooks.databinding.FragmentBooksBinding
import com.mjrfusion.app.allebooks.documents_manager.Document
import com.mjrfusion.app.allebooks.documents_manager.DocumentViewModel
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
        setupPickDocumentFAB()
    }

    private fun setupPickDocumentFAB() {
        showFab()
        setFabOpenClickListener()
        setFabOpenDocClickListener()
    }

    private fun showFab() {
        binding.fabsParentLayout.visibility = View.VISIBLE
    }

    private fun setFabOpenClickListener() {
        binding.fabOpen.setOnClickListener {
            revertFabVisibility()
        }
    }

    private fun setFabOpenDocClickListener() {
        binding.fabOpenDoc.setOnClickListener {
            revertFabVisibility()
            getDocumentFromIntent()
        }
    }

    private fun revertFabVisibility() {
        if (binding.fabOpenDoc.isVisible) {
            binding.fabOpenDoc.visibility = View.INVISIBLE
            binding.openDocFabLabel.visibility = View.INVISIBLE
        } else {
            binding.fabOpenDoc.visibility = View.VISIBLE
            binding.openDocFabLabel.visibility = View.VISIBLE
        }
    }

    private fun getDocumentFromIntent() {
        pickDocumentActivityLauncher.launch(arrayOf(PDF_MIME_TYPE))
    }

    private fun onDocumentPickedResult(data: Uri?) {
        val document = DocumentUtils.getDocument(data, baseActivity!!.contentResolver)
        baseActivity?.contentResolver?.takePersistableUriPermission(
            data!!,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
        documentViewModel.insert(document)
        documents.add(document)
        documentsAdapter.notifyItemInserted(documents.size)
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