package com.mjrt.app.allebooks.activities

import com.mjrt.app.allebooks.databinding.ActivityPdfViewerBinding

class PdfViewerActivity : BaseActivity() {
    private lateinit var binding: ActivityPdfViewerBinding

    override fun initializeActivity(): Boolean {
        binding = ActivityPdfViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        return true
    }

    override fun initializeAttributes() {
        TODO("Not yet implemented")
    }

}