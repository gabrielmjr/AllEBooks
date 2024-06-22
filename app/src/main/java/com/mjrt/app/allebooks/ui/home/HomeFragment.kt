package com.mjrt.app.allebooks.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mjrt.app.allebooks.MainActivity
import com.mjrt.app.allebooks.R
import com.mjrt.app.allebooks.adapters.PdfDocumentsAdapter
import com.mjrt.app.allebooks.core.fragment.BaseFragment
import com.mjrt.app.allebooks.databinding.FragmentHomeBinding
import com.mjrt.app.allebooks.documents_manager.documents_manager.PdfDocument
import java.util.ArrayList

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun initializeFragment(view: View) {
        binding = FragmentHomeBinding.bind(view)
    }

    override fun initializeAttributes() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P)
            loadDocuments()
        else
            setupPickDocumentFAB()
    }

    private fun loadDocuments() {
        if (documents == null) {
            Log.d(TAG, "loadDocuments: Loading documents on API <= 29, it's empty")
            (baseActivity as MainActivity).documentsRepository.loadDocuments(::onDocumentsLoaded)
        } else {
            Log.d(TAG, "loadDocuments: No need to reload the documents")
            onDocumentsLoaded(documents!!)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onDocumentsLoaded(mDocuments: ArrayList<PdfDocument>) {
        handler.post {
            documents = mDocuments
            if (documentsAdapter == null)
                documentsAdapter = PdfDocumentsAdapter(requireContext(), documents!!)
            documentsAdapter!!.notifyDataSetChanged()
            binding.docRecycler.adapter = documentsAdapter
            binding.docRecycler.layoutManager = LinearLayoutManager(requireContext())
        }
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
            Toast.makeText(requireContext(), "Pick document", Toast.LENGTH_SHORT).show()
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

    companion object {
        var documents: ArrayList<PdfDocument>? = null
        @SuppressLint("StaticFieldLeak")
        var documentsAdapter: PdfDocumentsAdapter? = null
        const val TAG = "HomeFragment"
    }
}