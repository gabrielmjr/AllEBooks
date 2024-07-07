package com.mjrfusion.app.allebooks.thumbnail_manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;

import java.io.IOException;

public class ThumbnailManager {
    @SuppressLint("StaticFieldLeak")
    private static ThumbnailManager instance;
    private Context context;

    private ThumbnailManager(Context context) {
        this.context = context;
    }

    private ThumbnailManager() {
    }

    public Bitmap create(Uri uri) {
        try {
            var fileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            assert fileDescriptor != null;
            var pdfRenderer = new PdfRenderer(fileDescriptor).openPage(0);
            var bitmap = Bitmap.createBitmap(pdfRenderer.getWidth(), pdfRenderer.getHeight(),
                    Bitmap.Config.ARGB_8888);
            pdfRenderer.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            pdfRenderer.close();
            fileDescriptor.close();
            return bitmap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ThumbnailManager getInstance(Context context) {
        if (instance == null)
            instance = new ThumbnailManager(context);
        return instance;
    }
}
