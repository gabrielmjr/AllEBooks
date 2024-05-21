package com.mjrt.app.allebooks

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.view.Menu
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mjrt.app.allebooks.activities.BaseActivity
import com.mjrt.app.allebooks.adapters.PdfDocumentsAdapter
import com.mjrt.app.allebooks.databinding.ActivityMainBinding
import com.mjrt.app.allebooks.document_manager.DocumentManager
import com.mjrt.app.allebooks.document_manager.DocumentManager.Companion.READ_STORAGE_PERMISSION_CODE

class MainActivity : BaseActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    lateinit var documentManager: DocumentManager
    lateinit var pdfDocumentsAdapter: PdfDocumentsAdapter

    override fun initializeActivity(): Boolean {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        return true
    }

    private fun setNavigation() {
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_reading, R.id.nav_read
            ), binding.drawerLayout
        )
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as  NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_reading, R.id.nav_read
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    private fun checkForReadPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
                if (ContextCompat.checkSelfPermission(
                        applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        READ_STORAGE_PERMISSION_CODE
                    )
                } else {
                    onPermissionsLegalizer()
                }
            } else {
                onPermissionsLegalizer()
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
                onPermissionsLegalizer()
    }

    private fun onPermissionsLegalizer() {
        documentManager = DocumentManager.getInstance(applicationContext)!!
        pdfDocumentsAdapter = PdfDocumentsAdapter(applicationContext, documentManager.pdfDocuments)
    }

    override fun initializeAttributes() {
        checkForReadPermission()
        setNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}