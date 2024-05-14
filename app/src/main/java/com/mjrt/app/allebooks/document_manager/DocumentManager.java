package com.mjrt.app.allebooks.document_manager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;

public class DocumentManager {
    public static final int READ_STORAGE_PERMISSION_CODE = 0b000000;
    private static DocumentManager instance;
    private final String TAG = getClass().getName();

    private final String[] projection = {
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.MIME_TYPE,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.DATE_MODIFIED,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.TITLE,
            MediaStore.Files.FileColumns.SIZE
    };
    private final String MIME_TYPE = "application/pdf";
    private final String whereClause;
    private final String orderBy;
    private final Uri externalUri;
    private final Cursor cursor;

    private int idColumn;
    private int mimeColumn;
    private int addedTimeColumn;
    private int lastModifiedTimeColumn;
    private int displayNameColumn;
    private int titleColumn;
    private int sizeColumn;

    private DocumentManager(@NonNull Context context) {
        Log.d(TAG, "DocumentManager: Instance created");
        whereClause = MediaStore.Files.FileColumns.MIME_TYPE + " IN ('" + MIME_TYPE + "')";
        orderBy = MediaStore.Files.FileColumns.SIZE + " DESC";
        externalUri = MediaStore.Files.getContentUri("external");
        cursor = context.getContentResolver().query(externalUri, projection, whereClause, null, orderBy);
        setupColumnIndexes();
    }

    private void setupColumnIndexes() {
        idColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID);
        mimeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE);
        addedTimeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED);
        lastModifiedTimeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED);
        displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME);
        titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.TITLE);
        sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE);
    }

    public void loadAllDocuments() {
        Log.d(TAG, "loadAllDoccuments: found documents " + cursor.getCount());
        if (cursor.moveToFirst())
            do {
                Uri fileUri = Uri.withAppendedPath(externalUri, cursor.getString(idColumn));
                Log.d(TAG, "loadAllDoccuments: File: " + cursor.getString(titleColumn));
            } while (cursor.moveToNext());
    }

    public static DocumentManager getInstance(Context context) {
        if (instance == null)
            instance = new DocumentManager(context);
        return instance;
    }
}
