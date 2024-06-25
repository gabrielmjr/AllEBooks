package com.mjrt.app.allebooks.documents_manager.documents_manager.api29;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mjrt.app.allebooks.documents_manager.documents_manager.Document;

import java.util.List;

@Dao
public interface DocumentDaoQ {
    @Query("SELECT * FROM documents")
    LiveData<List<Document>> getAllDocuments();

    @Insert
    void insert (Document document);

    @Delete
    void remove(Document document);

    @Update
    void updateDocument(Document document);
}
