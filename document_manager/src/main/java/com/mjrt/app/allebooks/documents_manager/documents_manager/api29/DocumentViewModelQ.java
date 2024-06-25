package com.mjrt.app.allebooks.documents_manager.documents_manager.api29;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.mjrt.app.allebooks.documents_manager.documents_manager.Document;
import com.mjrt.app.allebooks.documents_manager.documents_manager.DocumentViewModel;

import java.util.List;

public class DocumentViewModelQ extends DocumentViewModel {
    private final DocumentRepositoryQ documentRepositoryQ;

    public DocumentViewModelQ(@NonNull Application application) {
        super(application);
        documentRepositoryQ = new DocumentRepositoryQ(application);
    }

    @Override
    public LiveData<List<Document>> getAllDocuments() {
        return documentRepositoryQ.getAllDocuments();
    }

    @Override
    public void insert(Document document) {
        documentRepositoryQ.insert(document);
    }

    @Override
    public void delete(Document document) {
        documentRepositoryQ.delete(document);
    }

    @Override
    public void update(Document document) {
        documentRepositoryQ.update(document);
    }
}
