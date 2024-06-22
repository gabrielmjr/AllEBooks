package com.mjrt.app.allebooks.documents_manager.documents_manager

import android.graphics.Bitmap
import android.net.Uri
import com.mjrt.app.allebooks.utils.Size
import java.util.Date

class PdfDocument {
    var id: String? = null
    var uri: Uri? = null
    var mimeType: String? = null
    var displayName: String? = null
    var title: String? = null
    var lastModifiedTime: Date? = null
    var addedTime: Date? = null
    var size: Size? = null
    var thumbnail: Bitmap? = null
}
