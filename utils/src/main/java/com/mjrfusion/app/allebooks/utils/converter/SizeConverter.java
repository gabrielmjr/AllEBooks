package com.mjrfusion.app.allebooks.utils.converter;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import com.mjrfusion.app.allebooks.utils.size.Size;

public class SizeConverter {
    @NonNull
    @TypeConverter
    public static String fromSize(@NonNull Size size) {
        return size.toString();
    }

    @NonNull
    @TypeConverter
    public static Size fromString(@NonNull String stringSize) {
        return new Size(stringSize);
    }
}
