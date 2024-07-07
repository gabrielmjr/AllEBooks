package com.mjrfusion.app.allebooks.utils.converter;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

public class UriConverter {
    @TypeConverter
    public static Uri fromString(String string) {
        return Uri.parse(string);
    }

    @NonNull
    @TypeConverter
    public static String fromUri(@NonNull Uri uri) {
        return uri.toString();
    }
}
