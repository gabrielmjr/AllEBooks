package com.mjrfusion.app.allebooks

import android.os.Build
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.mjrfusion.app.allebooks.core.activity.BaseActivity
import com.mjrfusion.app.allebooks.databinding.ActivityMainBinding
import com.mjrfusion.app.allebooks.documents_manager.api29.DocumentViewModel
import com.mjrfusion.app.allebooks.ui.home.HomeFragment

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var documentsViewModel: DocumentViewModel

    override fun initializeActivity(): Boolean {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initializeAttributes() {
        initializeDocumentViewModel()
        checkForReadPermission()
        setDefaultFragment()
        setOnBackPressedListener()
    }

    private fun setOnBackPressedListener() {
        onBackPressedDispatcher.addCallback {
            if (!supportFragmentManager.popBackStackImmediate())
                finish()
        }
    }

    private fun initializeDocumentViewModel() {
        documentsViewModel = ViewModelProvider(this)[DocumentViewModel::class.java]
    }

    private fun setDefaultFragment() {
        replaceFragmentBy(R.id.fragment_container, HomeFragment::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkForReadPermission() {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            if (ContextCompat.checkSelfPermission(
                    applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_STORAGE_PERMISSION_CODE
                )
            } else {
                onPermissionGranted()
            }
        }*/
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        /*if (requestCode == READ_STORAGE_PERMISSION_CODE)
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                onPermissionGranted()*/
    }

    private fun onPermissionGranted() {
        //(documentsViewModel as DocumentViewModelPie).onPermissionGranted()
    }
}