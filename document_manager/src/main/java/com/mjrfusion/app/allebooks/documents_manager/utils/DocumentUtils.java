package com.mjrfusion.app.allebooks.documents_manager.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;

import androidx.annotation.NonNull;

import com.mjrfusion.app.allebooks.documents_manager.model.Document;
import com.mjrfusion.app.allebooks.utils.size.Size;
import com.mjrfusion.app.allebooks.utils.size.SizeParser;

import org.jetbrains.annotations.Contract;

import java.util.Date;
import java.util.Objects;

public class DocumentUtils {
    private static final String[] projection = new String[]{
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.DATE_MODIFIED,
            MediaStore.Files.FileColumns.SIZE
    };

    private static int lastModifiedTimeColumn = 0;

    public static Document getDocument(
            Uri data, @NonNull ContentResolver contentResolver) {
        var cursor = contentResolver.query(
                data, projection, null, null, null
        );
        Document document = null;
        if (cursor != null && cursor.moveToFirst()) {
            setupColumnIndexes(cursor);
            document = getDocument(cursor);
            document.setUri(data);
            Log.d("DocumentUtils", "getDocument: " + contentResolver.getType(data));
            document.setMimeType(Objects.requireNonNull(contentResolver.getType(data)));
            cursor.close();
        }
        return document;
    }

    private static void setupColumnIndexes(@NonNull Cursor cursor) {
        lastModifiedTimeColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED);
    }

    @NonNull
    private static Document getDocument(Cursor cursor) {
        var document = new Document();
        var displayName = getDisplayName(cursor);
        document.setDisplayName(getDisplayNameWithoutExtension(displayName));
        document.setExtension(getExtension(displayName));
        document.setLastModifiedTime(getLastModifiedTime(cursor));
        document.setSize(getDocumentSize(cursor));
        return document;
    }

    @NonNull
    private static String getDisplayName(@NonNull Cursor cursor) {
        var displayNameIndex = cursor.getColumnIndex((OpenableColumns.DISPLAY_NAME));
        return cursor.getString(displayNameIndex);
    }

    @NonNull
    public static String getDisplayNameWithoutExtension(String displayName) {
        return displayName.substring(0, displayName.lastIndexOf('.'));
    }

    public static String getExtension(String displayName) {
        return displayName.substring(displayName.lastIndexOf('.') + 1);
    }

    @NonNull
    @Contract("_ -> new")
    private static Date getLastModifiedTime(@NonNull Cursor cursor) {
        return new Date(cursor.getLong(lastModifiedTimeColumn));
    }

    @NonNull
    private static Size getDocumentSize(@NonNull Cursor cursor) {
        var sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
        return SizeParser.parse(cursor.getDouble(sizeIndex));
    }
}