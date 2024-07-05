package com.mjrfusion.app.allebooks.documents_manager.documents_manager.documents_manager.api29;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mjrfusion.app.allebooks.documents_manager.documents_manager.documents_manager.Document;

import java.util.List;

public class DocumentRepositoryQ {
    private final DocumentDaoQ documentDaoQ;

    public DocumentRepositoryQ(Application application) {
        DocumentRoomDatabase database = DocumentRoomDatabase.getDatabase(application);
        documentDaoQ = database.documentDao();
    }

    public LiveData<List<Document>> getAllDocuments() {
        return documentDaoQ.getAllDocuments();
    }

    public void insert(Document document) {
        DocumentRoomDatabase.databaseWriterExecutor.execute(() -> documentDaoQ.insert(document));
    }

    public void delete(Document document) {
        DocumentRoomDatabase.databaseWriterExecutor.execute(() -> documentDaoQ.remove(document));
    }

    public void update(Document document) {
        DocumentRoomDatabase.databaseWriterExecutor.execute(() -> documentDaoQ.updateDocument(document));
    }
}
