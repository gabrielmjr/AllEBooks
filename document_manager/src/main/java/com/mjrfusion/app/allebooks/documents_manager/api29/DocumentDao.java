package com.mjrfusion.app.allebooks.documents_manager.api29;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mjrfusion.app.allebooks.documents_manager.model.Document;

import java.util.List;

@Dao
public interface DocumentDao {
    @Query("SELECT * FROM documents")
    LiveData<List<Document>> getAllDocuments();

    @Query("SELECT EXISTS(SELECT * FROM documents WHERE uri = :uri)")
    LiveData<Boolean> existsByUri(Uri uri);

    @Insert
    void insert (Document document);

    @Delete
    void remove(Document document);

    @Update
    void updateDocument(Document document);
}
