package com.mjrt.app.allebooks.documents_manager.documents_manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import kotlin.reflect.KFunction;

public class DocumentManagerHandler extends Handler {
    public static final int LOAD_PDF = 0x000000;
    private final DocumentManager documentManager;

    public DocumentManagerHandler(Context context, Looper looper) {
        super(looper);
        documentManager = new DocumentManager(context);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            case LOAD_PDF -> {
                KFunction<ArrayList<PdfDocument>> callback = (KFunction<ArrayList<PdfDocument>>) msg.obj;
                callback.call(documentManager.getPdfDocuments());
                break;
            }
        }
    }
}
