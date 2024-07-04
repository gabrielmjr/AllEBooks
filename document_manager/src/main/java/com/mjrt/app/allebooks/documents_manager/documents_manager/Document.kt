package com.mjrt.app.allebooks.documents_manager.documents_manager

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mjrt.app.allebooks.utils.size.Size
import java.util.Date

@Entity(tableName = "documents")
class Document() : Parcelable {
    @PrimaryKey(autoGenerate = false)
    lateinit var uri: Uri
    var mimeType: String? = null
    var displayName: String? = null
    var lastModifiedTime: Date? = null
    var size: Size? = null

    constructor(parcel: Parcel) : this() {
        uri = parcel.readValue(Uri::class.java.classLoader) as Uri
        mimeType = parcel.readString()
        displayName = parcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(uri)
        dest.writeString(mimeType)
        dest.writeString(displayName)
    }

    companion object CREATOR : Parcelable.Creator<Document> {
        override fun createFromParcel(parcel: Parcel): Document {
            return Document(parcel)
        }

        override fun newArray(size: Int): Array<Document?> {
            return arrayOfNulls(size)
        }
    }
}
