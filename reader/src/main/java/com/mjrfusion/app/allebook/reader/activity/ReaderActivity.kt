package com.mjrfusion.app.allebook.reader.activity

import android.os.Bundle
import com.mjrfusion.app.allebook.reader.R
import com.mjrfusion.app.allebook.reader.databinding.ActivityReaderBinding
import com.mjrfusion.app.allebook.reader.fragment.PdfReaderFragment
import com.mjrfusion.app.allebooks.core.activity.BaseActivity
import com.mjrfusion.app.allebooks.documents_manager.Document
import com.mjrfusion.app.allebooks.utils.Constants.DOCUMENT_OBJECT
import com.mjrfusion.app.allebooks.utils.Constants.PDF_DOCUMENT
import com.mjrfusion.app.allebooks.utils.Constants.PDF_MIME_TYPE
import com.mjrfusion.app.allebooks.utils.Constants.PDF_READER_TAG
import com.mjrfusion.app.allebooks.utils.ParcelableUtil

class ReaderActivity: BaseActivity() {
    private lateinit var binding: ActivityReaderBinding
    private lateinit var document: Document

    override fun initializeActivity(): Boolean {
        binding = ActivityReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        return false
    }

    override fun initializeAttributes() {
        document = ParcelableUtil.getParcelable(intent, DOCUMENT_OBJECT, Document::class.java)
        when (document.mimeType) {
            PDF_MIME_TYPE -> displayPdfDocument()
        }
    }

    private fun displayPdfDocument() {
        Bundle().apply {
            putParcelable(DOCUMENT_OBJECT, document)
            replaceFragmentBy(
                R.id.fragment_reader_container,
                PdfReaderFragment::class.java,
                null,
                this,
                false
            )
        }
    }
}
