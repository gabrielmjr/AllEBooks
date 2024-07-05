package com.mjrfusion.app.allebooks.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mjrfusion.app.allebook.reader.activity.ReaderActivity
import com.mjrfusion.app.allebooks.MainActivity
import com.mjrfusion.app.allebooks.R
import com.mjrfusion.app.allebooks.adapters.PdfDocumentsAdapter
import com.mjrfusion.app.allebooks.core.core.fragment.BaseFragment
import com.mjrfusion.app.allebooks.databinding.FragmentHomeBinding
import com.mjrfusion.app.allebooks.documents_manager.documents_manager.documents_manager.Document
import com.mjrfusion.app.allebooks.documents_manager.documents_manager.documents_manager.DocumentViewModel
import com.mjrfusion.app.allebooks.documents_manager.documents_manager.documents_manager.utils.DocumentUtils
import com.mjrfusion.app.allebooks.utils.Constants.DOCUMENT_OBJECT

class HomeFragment : BaseFragment(R.layout.fragment_home), PdfDocumentsAdapter.ClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var documentViewModel: DocumentViewModel
    private lateinit var documents: ArrayList<Document>
    private lateinit var documentsAdapter: PdfDocumentsAdapter
    @RequiresApi(Build.VERSION_CODES.O)
    private var pickDocumentActivityLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument(), this::onDocumentPickedResult)

    override fun initializeFragment(view: View) {
        binding = FragmentHomeBinding.bind(view)
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
            documentsAdapter.notifyDataSetChanged()
            Log.d(TAG, "onDocumentsLoaded: Docs  loaded " + it.size)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            setupPickDocumentFAB()
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDocumentFromIntent() {
        pickDocumentActivityLauncher.launch(arrayOf("application/pdf"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onDocumentPickedResult(data: Uri?) {
        val document = DocumentUtils.getDocument(data, baseActivity.contentResolver)
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