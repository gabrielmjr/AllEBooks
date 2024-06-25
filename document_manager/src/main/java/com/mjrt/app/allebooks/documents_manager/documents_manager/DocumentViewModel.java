package com.mjrt.app.allebooks.documents_manager.documents_manager;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class DocumentViewModel extends AndroidViewModel {
    public DocumentViewModel(Application application) {
        super(application);
    }

    public abstract LiveData<List<Document>> getAllDocuments();

    public void insert(Document document) {}

    public void delete(Document document) {}

    public void update(Document document) {}
}
