package com.mjrt.app.allebooks.documents_manager.documents_manager.api28

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mjrt.app.allebooks.documents_manager.documents_manager.Document
import com.mjrt.app.allebooks.thumbnail_manager.thumbnail_manager.ThumbnailManager
import com.mjrt.app.allebooks.utils.size.SizeParser
import java.util.Date

class DocumentManagerPie(private val context: Context) {
    private val tag = javaClass.getName()
    private val projection = arrayOf(
        MediaStore.Files.FileColumns._ID,
        MediaStore.Files.FileColumns.MIME_TYPE,
        MediaStore.Files.FileColumns.DATE_MODIFIED,
        MediaStore.Files.FileColumns.TITLE,
        MediaStore.Files.FileColumns.SIZE
    )
    private val whereClause: String
    private val orderBy: String
    private val externalUri: Uri
    private lateinit var cursor: Cursor
    private lateinit var thumbnailManager: ThumbnailManager
    private var idColumn = 0
    private var mimeColumn = 0
    private var lastModifiedTimeColumn = 0
    private var displayNameColumn = 0
    private var sizeColumn = 0

    var documentsLiveData: MutableLiveData<List<Document>> = MutableLiveData()

    init {
        documentsLiveData.value = ArrayList()
        Log.d(tag, "DocumentManager: Instance created")
        whereClause = MediaStore.Files.FileColumns.MIME_TYPE + " IN ('" + MIME_TYPE + "')"
        orderBy = MediaStore.Files.FileColumns.SIZE + " DESC"
        externalUri = MediaStore.Files.getContentUri("external")
    }

    fun onPermissionGranted() {
        cursor = context.contentResolver.query(externalUri, projection, whereClause, null, orderBy)!!
        setupColumnIndexes()
        thumbnailManager = ThumbnailManager.getInstance(context)
        loadAllDocuments()
    }

    private fun setupColumnIndexes() {
        idColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
        mimeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)
        lastModifiedTimeColumn =
            cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)
        displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.TITLE)
        sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)
    }

    private fun loadAllDocuments() {
        val temp = ArrayList<Document>()
        Log.d(tag, "loadAllDocuments: found ${cursor.count} documents ")
        if (cursor.moveToFirst()) do {
            temp.add(
                Document().apply {
                    uri = Uri.withAppendedPath(externalUri, cursor.getString(idColumn))
                    displayName = cursor.getString(displayNameColumn)
                    mimeType = cursor.getString(mimeColumn)
                    size = SizeParser.parse(cursor.getLong(sizeColumn).toDouble())
                    lastModifiedTime = Date(cursor.getLong(lastModifiedTimeColumn))
                    Log.d(tag, "loadAllDocuments: File: $displayName")
                }
            )
        } while (cursor.moveToNext())
        documentsLiveData.postValue(temp)
    }

    companion object {
        const val READ_STORAGE_PERMISSION_CODE = 0x000000
        private const val MIME_TYPE = "application/pdf"
    }
}
