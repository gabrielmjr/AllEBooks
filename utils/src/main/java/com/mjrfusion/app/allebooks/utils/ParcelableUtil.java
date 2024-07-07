package com.mjrfusion.app.allebooks.utils;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class ParcelableUtil {
    public static <T>T getParcelable(Intent intent, String key, Class<T> clazz) {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)?
                intent.getParcelableExtra(key, clazz)
                : intent.getParcelableExtra(key);
    }

    public static <T>T getParcelable(Bundle data, String key, Class<T> clazz) {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)?
                data.getParcelable(key, clazz)
                : data.getParcelable(key);
    }
}
