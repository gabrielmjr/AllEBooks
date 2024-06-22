package com.mjrt.app.allebooks.documents_manager.documents_manager;

import static com.mjrt.app.allebooks.documents_manager.documents_manager.DocumentManagerHandler.LOAD_PDF;

import android.content.Context;
import android.os.Message;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.reflect.KFunction;
import lombok.Getter;

public class DocumentRepository {
    @Getter
    private static DocumentRepository instance;
    private final DocumentManagerLooperThread looperThread;

    private DocumentRepository(Context context) {
        looperThread = new DocumentManagerLooperThread(context);
        looperThread.start();
    }

    public void loadDocuments(@NotNull KFunction<Unit> callback) {
        var msg = Message.obtain();
        msg.what = LOAD_PDF;
        msg.obj = callback;
        looperThread.getHandler().sendMessage(msg);
    }

    public static DocumentRepository getInstance(Context context) {
        if (instance == null)
            instance = new DocumentRepository(context);
        return instance;
    }
}
