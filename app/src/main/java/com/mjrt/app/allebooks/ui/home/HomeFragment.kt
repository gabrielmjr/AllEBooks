package com.mjrt.app.allebooks.ui.home

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mjrt.app.allebooks.MainActivity
import com.mjrt.app.allebooks.R
import com.mjrt.app.allebooks.adapters.PdfDocumentsAdapter
import com.mjrt.app.allebooks.core.fragment.BaseFragment
import com.mjrt.app.allebooks.databinding.FragmentHomeBinding
import com.mjrt.app.allebooks.documents_manager.documents_manager.Document
import com.mjrt.app.allebooks.documents_manager.documents_manager.DocumentViewModel
import com.mjrt.app.allebooks.documents_manager.documents_manager.utils.DocumentUtils

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var documentViewModel: DocumentViewModel
    private lateinit var documents: ArrayList<Document>
    @RequiresApi(Build.VERSION_CODES.O)
    private var pickDocumentActivityLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument(), this::onDocumentPickedResult)

    override fun initializeFragment(view: View) {
        binding = FragmentHomeBinding.bind(view)
    }

    override fun initializeAttributes() {
        documentViewModel = (baseActivity as MainActivity).documentsViewModel
        documentViewModel.allDocuments.observe(this) {
            documents = it as ArrayList
            onDocumentsLoaded()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            setupPickDocumentFAB()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onDocumentsLoaded() {
        Log.d(TAG, "onDocumentsLoaded: Docs  loaded " + documents.size)
        handler.post {
            if (documentsAdapter == null) {
                documentsAdapter = PdfDocumentsAdapter(requireContext(), documents)
                binding.docRecycler.adapter = documentsAdapter
                binding.docRecycler.layoutManager = LinearLayoutManager(requireContext())
            }
            documentsAdapter!!.notifyDataSetChanged()
        }
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
        documentsAdapter!!.notifyItemInserted(documents.size)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var documentsAdapter: PdfDocumentsAdapter? = null
        const val TAG = "HomeFragment"
    }
}