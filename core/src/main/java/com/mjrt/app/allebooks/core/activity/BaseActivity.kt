package com.mjrt.app.allebooks.core.activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.mjrt.app.allebooks.core.R

abstract class BaseActivity: AppCompatActivity() {
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeThisAttributes()
        if (initializeActivity())
            setDefaultToolbar()
        initializeAttributes()
    }

    private fun initializeThisAttributes() {
        handler = Handler(mainLooper)
    }

    protected abstract fun initializeActivity(): Boolean

    private fun setDefaultToolbar() {
        setToolbar(R.id.toolbar)
    }

    private fun setToolbar(toolbar: Int) {
        setSupportActionBar(findViewById(toolbar))
    }

    protected abstract fun initializeAttributes()
}