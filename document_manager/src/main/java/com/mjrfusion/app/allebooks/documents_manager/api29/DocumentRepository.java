package com.mjrfusion.app.allebooks.documents_manager.api29;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;

import com.mjrfusion.app.allebooks.documents_manager.model.Document;

import java.util.List;

public class DocumentRepository {
    private final DocumentDao documentDao;

    public DocumentRepository(Application application) {
        DocumentRoomDatabase database = DocumentRoomDatabase.getDatabase(application);
        documentDao = database.documentDao();
    }

    public LiveData<List<Document>> getAllDocuments() {
        return documentDao.getAllDocuments();
    }

    public LiveData<Boolean> existsByUri(Uri uri) {
        return documentDao.existsByUri(uri);
    }

    public void insert(Document document) {
        DocumentRoomDatabase.databaseWriterExecutor.execute(() -> documentDao.insert(document));
    }

    public void delete(Document document) {
        DocumentRoomDatabase.databaseWriterExecutor.execute(() -> documentDao.remove(document));
    }

    public void update(Document document) {
        DocumentRoomDatabase.databaseWriterExecutor.execute(() -> documentDao.updateDocument(document));
    }
}
