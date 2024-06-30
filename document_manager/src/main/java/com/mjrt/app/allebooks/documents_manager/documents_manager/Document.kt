package com.mjrt.app.allebooks.documents_manager.documents_manager

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mjrt.app.allebooks.utils.size.Size
import java.util.Date

@Entity(tableName = "documents")
class Document {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var uri: Uri? = null
    var mimeType: String? = null
    var displayName: String? = null
    var lastModifiedTime: Date? = null
    var size: Size? = null
}
