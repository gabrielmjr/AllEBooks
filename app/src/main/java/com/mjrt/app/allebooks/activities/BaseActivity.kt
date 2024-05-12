package com.mjrt.app.allebooks.activities

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.mjrt.app.allebooks.R

abstract class BaseActivity: AppCompatActivity() {
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeThisAttributes()
        if (initialzeActivity())
            setDefaultToolbar()
        initializeAttributes()
    }

    private fun initializeThisAttributes() {
        handler = Handler(mainLooper)
    }

    protected abstract fun initialzeActivity(): Boolean

    private fun setDefaultToolbar() {
        setToolbar(R.id.toolbar)
    }

    private fun setToolbar(toolbar: Int) {
        setSupportActionBar(findViewById(toolbar))
    }

    protected abstract fun initializeAttributes()


}