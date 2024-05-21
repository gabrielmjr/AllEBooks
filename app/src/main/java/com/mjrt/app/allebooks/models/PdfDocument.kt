package com.mjrt.app.allebooks.models

import android.graphics.Bitmap
import android.net.Uri
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
