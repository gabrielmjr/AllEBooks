package com.mjrt.app.allebooks.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;

public class ParcelableUtil {
    public static Uri getUri(Intent intent, String key) {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)?
                intent.getParcelableExtra(key, Uri.class)
                : intent.getParcelableExtra(key);
    }
}
