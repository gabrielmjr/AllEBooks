package com.mjrt.app.allebooks.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mjrt.app.allebooks.MainActivity
import com.mjrt.app.allebooks.R
import com.mjrt.app.allebooks.adapters.PdfDocumentsAdapter
import com.mjrt.app.allebooks.core.fragment.BaseFragment
import com.mjrt.app.allebooks.databinding.FragmentHomeBinding
import com.mjrt.app.allebooks.documents_manager.documents_manager.Document
import com.mjrt.app.allebooks.documents_manager.documents_manager.DocumentViewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var documentViewModel: DocumentViewModel
    private var pickDocumentActivityLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument()) { result ->
        run {
            Toast.makeText(requireContext(), result!!.path, Toast.LENGTH_SHORT).show()
        }
    }

    override fun initializeFragment(view: View) {
        binding = FragmentHomeBinding.bind(view)

    }

    override fun initializeAttributes() {
        documentViewModel = (baseActivity as MainActivity).documentsViewModel
        documentViewModel.allDocuments.observe(this) {
            onDocumentsLoaded(it as java.util.ArrayList)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            setupPickDocumentFAB()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onDocumentsLoaded(mDocuments: ArrayList<Document>) {
        Log.d(TAG, "onDocumentsLoaded: Docs  loaded " + mDocuments.size)
        handler.post {
            if (documentsAdapter == null)
                documentsAdapter = PdfDocumentsAdapter(requireContext(), mDocuments)
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
        pickDocumentActivityLauncher.launch(arrayOf("application/pdf"))
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var documentsAdapter: PdfDocumentsAdapter? = null
        const val TAG = "HomeFragment"
    }
}