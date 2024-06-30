package com.mjrt.app.allebooks.documents_manager.documents_manager;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public abstract class DocumentViewModel extends AndroidViewModel {
    public DocumentViewModel(Application application) {
        super(application);
    }

    public abstract LiveData<List<Document>> getAllDocuments();

    public void insert(Document document) {}

    public void delete(Document document) {}

    public void update(Document document) {}
}
