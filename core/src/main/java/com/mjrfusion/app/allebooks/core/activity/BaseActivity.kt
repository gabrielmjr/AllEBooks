package com.mjrfusion.app.allebooks.core.activity

import android.os.Bundle
import android.os.Handler
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity() {
    @JvmField
    var handler: Handler? = null

    @IdRes
    private var defaultToolbarRes = 0

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeThisAttributes()
        if (initializeActivity()) setDefaultToolbar()
        initializeAttributes()
    }

    private fun initializeThisAttributes() {
        handler = Handler(mainLooper)
    }

    protected abstract fun initializeActivity(): Boolean

    private fun setDefaultToolbar() {
        setToolbar(defaultToolbarRes)
    }

    private fun setToolbar(@IdRes toolbar: Int) {
        setSupportActionBar(findViewById(toolbar))
    }


    fun replaceFragmentBy(
        @IdRes fragmentContainer: Int,
        fragment: Class<out Fragment?>,
        tag: String? = null,
        args: Bundle? = null,
        addToBackStack: Boolean = false
    ) {
        val supportFragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(fragmentContainer, fragment, args, tag)
        if (addToBackStack) supportFragmentTransaction.addToBackStack(tag)
        supportFragmentTransaction.commit()
    }

    protected abstract fun initializeAttributes()

    fun setDefaultToolbarRes(@IdRes defaultToolbarRes: Int) {
        this.defaultToolbarRes = defaultToolbarRes
    }
}