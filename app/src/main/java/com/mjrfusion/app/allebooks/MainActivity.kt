package com.mjrfusion.app.allebooks

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.view.Menu
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mjrfusion.app.allebooks.core.activity.BaseActivity
import com.mjrfusion.app.allebooks.databinding.ActivityMainBinding
import com.mjrfusion.app.allebooks.documents_manager.DocumentViewModel
import com.mjrfusion.app.allebooks.documents_manager.api28.DocumentManagerPie.Companion.READ_STORAGE_PERMISSION_CODE
import com.mjrfusion.app.allebooks.documents_manager.api28.DocumentViewModelPie
import com.mjrfusion.app.allebooks.documents_manager.api29.DocumentViewModelQ

class MainActivity : BaseActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
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
        setNavigation()
    }

    private fun initializeDocumentViewModel() {
        documentsViewModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            ViewModelProvider(this)[DocumentViewModelQ::class.java]
        else
            ViewModelProvider(this)[DocumentViewModelPie::class.java]
    }

    private fun setNavigation() {
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_reading, R.id.nav_read
            ), binding.drawerLayout
        )
        navHostFragment = supportFragmentManager.findFragmentById(
            com.mjrfusion.app.allebooks.core.R.id.nav_host_fragment_content_main) as  NavHostFragment
        navController = navHostFragment.navController
        navController.graph = navController.navInflater.inflate(R.navigation.mobile_navigation)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkForReadPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
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
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_STORAGE_PERMISSION_CODE)
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                onPermissionGranted()
    }

    private fun onPermissionGranted() {
        (documentsViewModel as DocumentViewModelPie).onPermissionGranted()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(
            com.mjrfusion.app.allebooks.core.R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}