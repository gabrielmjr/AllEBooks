package com.mjrt.app.allebooks.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mjrt.app.allebooks.document_manager.DocumentManager
import com.mjrt.app.allebooks.models.PdfDocument

class HomeViewModel : ViewModel() {

    private val _pdfDocuments = MutableLiveData<ArrayList<PdfDocument>>().apply {
        value = DocumentManager.getInstance()!!.pdfDocuments
    }
    val pdfDocuments: LiveData<ArrayList<PdfDocument>> = _pdfDocuments
}