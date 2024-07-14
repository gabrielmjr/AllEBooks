package com.mjrfusion.app.allebooks.documents_manager.api29;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mjrfusion.app.allebooks.documents_manager.model.Document;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DocumentViewModel extends AndroidViewModel {
    private final DocumentRepository documentRepository;

    public DocumentViewModel(@NonNull Application application) {
        super(application);
        documentRepository = new DocumentRepository(application);
    }

    public LiveData<List<Document>> getAllDocuments() {
        return documentRepository.getAllDocuments();
    }

    public LiveData<Boolean> existsByUri(Uri uri) {
        return documentRepository.existsByUri(uri);
    }

    public void insert(Document document) {
        documentRepository.insert(document);
    }

    public void delete(Document document) {
        documentRepository.delete(document);
    }

    public void update(Document document) {
        documentRepository.update(document);
    }
}
