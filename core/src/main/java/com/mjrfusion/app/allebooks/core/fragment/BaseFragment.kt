package com.mjrfusion.app.allebooks.core.fragment

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.mjrfusion.app.allebooks.core.activity.BaseActivity

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    protected var baseActivity: BaseActivity? = null
    protected var handler: Handler? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeThisAttributes()
        initializeFragment(view)
        initializeAttributes()
    }

    private fun initializeThisAttributes() {
        baseActivity = requireActivity() as BaseActivity
        handler = baseActivity!!.handler
    }

    protected abstract fun initializeFragment(view: View)

    protected abstract fun initializeAttributes()

    protected fun replaceFragmentBy(
        @IdRes fragmentContainer: Int,
        fragment: Class<out Fragment>,
        tag: String? = null,
        args: Bundle? = null,
        addToBackStack: Boolean = false
    ) {
        baseActivity!!.replaceFragmentBy(fragmentContainer, fragment, tag, args, addToBackStack)
    }
}
