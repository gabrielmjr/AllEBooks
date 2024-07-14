package com.mjrfusion.app.allebooks.documents_manager.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mjrfusion.app.allebooks.utils.size.Size
import java.util.Date
import kotlin.properties.Delegates

@Entity(tableName = "documents")
class Document() : Parcelable {
    @PrimaryKey(autoGenerate = false)
    lateinit var uri: Uri
    lateinit var mimeType: String
    lateinit var displayName: String
    lateinit var lastModifiedTime: Date
    lateinit var size: Size
    var isFavourite = false
    lateinit var docStatus: DocumentStatus

    constructor(parcel: Parcel) : this() {
        uri = parcel.readValue(Uri::class.java.classLoader) as Uri
        mimeType = parcel.readString()!!
        displayName = parcel.readString()!!
        isFavourite = (parcel.readValue(Boolean::class.java.classLoader) as Boolean?)!!
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(uri)
        dest.writeString(mimeType)
        dest.writeString(displayName)
        dest.writeValue(isFavourite)
    }

    companion object CREATOR : Parcelable.Creator<Document> {
        override fun createFromParcel(parcel: Parcel): Document {
            return Document(parcel)
        }

        override fun newArray(size: Int): Array<Document?> {
            return arrayOfNulls(size)
        }
    }

    enum class DocumentStatus {
        NEVER_OPENED,
        READING,
        READ
    }
}
