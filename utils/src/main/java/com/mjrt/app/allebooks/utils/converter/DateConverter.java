package com.mjrt.app.allebooks.utils.converter;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import org.jetbrains.annotations.Contract;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static long timestampFormDate(@NonNull Date date) {
        return date.getTime();
    }

    @NonNull
    @Contract("_ -> new")
    @TypeConverter
    public static Date dateFromTimestamp(long value) {
        return new Date(value);
    }
}
