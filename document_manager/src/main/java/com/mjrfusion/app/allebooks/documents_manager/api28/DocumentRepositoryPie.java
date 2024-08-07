package com.mjrfusion.app.allebooks.documents_manager.api28;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.mjrfusion.app.allebooks.documents_manager.model.Document;

import java.util.List;

public class DocumentRepositoryPie {
    private final DocumentManagerPie documentManagerPie;

    public DocumentRepositoryPie(Context context) {
        documentManagerPie = new DocumentManagerPie(context);
    }

    public void onPermissionGranted() {
        documentManagerPie.onPermissionGranted();
    }

    public MutableLiveData<List<Document>> getAllDocuments() {
        return documentManagerPie.getDocumentsLiveData();
    }
}
