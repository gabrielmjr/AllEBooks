package com.mjrfusion.app.allebooks.documents_manager.api28;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mjrfusion.app.allebooks.documents_manager.model.Document;
import com.mjrfusion.app.allebooks.documents_manager.DocumentViewModel;

import java.util.List;

public class DocumentViewModelPie extends DocumentViewModel {
    private final DocumentRepositoryPie documentRepositoryPie;

    public DocumentViewModelPie(Application application) {
        super(application);
        documentRepositoryPie = new DocumentRepositoryPie(application);
    }

    @Override
    public LiveData<List<Document>> getAllDocuments() {
        return documentRepositoryPie.getAllDocuments();
    }

    public void onPermissionGranted() {
        documentRepositoryPie.onPermissionGranted();
    }
}
