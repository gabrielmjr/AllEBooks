package com.mjrfusion.app.allebooks.documents_manager.api28;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mjrfusion.app.allebooks.documents_manager.model.Document;

import java.util.List;

public class DocumentViewModelPie extends AndroidViewModel {
    private final DocumentRepositoryPie documentRepositoryPie;

    public DocumentViewModelPie(Application application) {
        super(application);
        documentRepositoryPie = new DocumentRepositoryPie(application);
    }

    public LiveData<List<Document>> getAllDocuments() {
        return documentRepositoryPie.getAllDocuments();
    }

    public void onPermissionGranted() {
        documentRepositoryPie.onPermissionGranted();
    }
}
