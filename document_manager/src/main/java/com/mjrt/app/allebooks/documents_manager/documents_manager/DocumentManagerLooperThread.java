package com.mjrt.app.allebooks.documents_manager.documents_manager;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

public class DocumentManagerLooperThread extends Thread {
    private final String TAG = getClass().getName();
    private final Context context;
    private DocumentManagerHandler handler;

    public DocumentManagerLooperThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.d(TAG, "run: Thread " + getName() + " with run called");
        Looper.prepare();
        handler = new DocumentManagerHandler(context, Looper.myLooper());
        Looper.loop();
    }

    public DocumentManagerHandler getHandler() {
        return handler;
    }
}
