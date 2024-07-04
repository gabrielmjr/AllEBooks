package com.mjrt.app.allebook.reader.activity

import com.mjrt.app.allebook.reader.databinding.ActivityReaderBinding
import com.mjrt.app.allebooks.core.activity.BaseActivity
import com.mjrt.app.allebooks.utils.Constants.DOCUMENT_TYPE
import com.mjrt.app.allebooks.utils.Constants.PDF_DOCUMENT

class ReaderActivity: BaseActivity() {
    private lateinit var binding: ActivityReaderBinding

    override fun initializeActivity(): Boolean {
        binding = ActivityReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        return false
    }

    override fun initializeAttributes() {
        when (intent.getStringExtra(DOCUMENT_TYPE)!!) {
            PDF_DOCUMENT -> displayPdfDocument()
        }
    }

    private fun displayPdfDocument() {
        TODO("Display the document from intent to the fragment")
    }
}
