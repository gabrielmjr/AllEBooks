package com.mjrt.app.allebooks.documents_manager.documents_manager

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mjrt.app.allebooks.utils.size.Size
import java.util.Date
import java.util.UUID

@Entity(tableName = "documents")
class Document {
    @PrimaryKey
    lateinit var id: UUID
    var uri: Uri? = null
    var mimeType: String? = null
    var displayName: String? = null
    var title: String? = null
    var lastModifiedTime: Date? = null
    var addedTime: Date? = null
    var size: Size? = null
}
