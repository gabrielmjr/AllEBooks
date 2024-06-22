package com.mjrt.app.allebooks.documents_manager.documents_manager

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.mjrt.app.allebooks.thumbnail_manager.thumbnail_manager.ThumbnailManager
import com.mjrt.app.allebooks.utils.SizeParser
import java.util.Date

class DocumentManager(context: Context) {
    private val tag = javaClass.getName()
    private val projection = arrayOf(
        MediaStore.Files.FileColumns._ID,
        MediaStore.Files.FileColumns.MIME_TYPE,
        MediaStore.Files.FileColumns.DATE_ADDED,
        MediaStore.Files.FileColumns.DATE_MODIFIED,
        MediaStore.Files.FileColumns.DISPLAY_NAME,
        MediaStore.Files.FileColumns.TITLE,
        MediaStore.Files.FileColumns.SIZE
    )
    private val MIME_TYPE = "application/pdf"
    private val whereClause: String
    private val orderBy: String
    private val externalUri: Uri
    private val cursor: Cursor
    private val thumbnailManager: ThumbnailManager
    private var idColumn = 0
    private var mimeColumn = 0
    private var addedTimeColumn = 0
    private var lastModifiedTimeColumn = 0
    private var displayNameColumn = 0
    private var titleColumn = 0
    private var sizeColumn = 0

    var pdfDocuments: ArrayList<PdfDocument>
        get() {
            return loadAllDocuments()
        }

    init {
        Log.d(tag, "DocumentManager: Instance created")
        pdfDocuments = ArrayList()
        whereClause = MediaStore.Files.FileColumns.MIME_TYPE + " IN ('" + MIME_TYPE + "')"
        orderBy = MediaStore.Files.FileColumns.SIZE + " DESC"
        externalUri = MediaStore.Files.getContentUri("external")
        cursor = context.contentResolver.query(externalUri, projection, whereClause, null, orderBy)!!
        setupColumnIndexes()
        thumbnailManager = ThumbnailManager.getInstance(context)
    }

    private fun setupColumnIndexes() {
        idColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
        mimeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)
        addedTimeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED)
        lastModifiedTimeColumn =
            cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)
        displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
        titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.TITLE)
        sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)
    }

    private fun loadAllDocuments():ArrayList<PdfDocument> {
        val temp = ArrayList<PdfDocument>()
        Log.d(tag, "loadAllDocuments: found ${cursor.count} documents ")
        if (cursor.moveToFirst()) do {
            temp.add(
                PdfDocument().apply {
                    id = cursor.getString(idColumn)
                    uri = Uri.withAppendedPath(externalUri, cursor.getString(idColumn))
                    displayName = cursor.getString(displayNameColumn)
                    mimeType = cursor.getString(mimeColumn)
                    title = cursor.getString(titleColumn)
                    size = SizeParser.parse(cursor.getLong(sizeColumn).toDouble())
                    addedTime = Date(cursor.getLong(addedTimeColumn))
                    lastModifiedTime = Date(cursor.getLong(lastModifiedTimeColumn))
                    //thumbnail = thumbnailManager.create(uri!!)
                }
            )
            Log.d(tag, "loadAllDocuments: File: " + cursor.getString(titleColumn))
        } while (cursor.moveToNext())
        return temp
    }

    companion object {
        const val READ_STORAGE_PERMISSION_CODE = 0
    }
}
