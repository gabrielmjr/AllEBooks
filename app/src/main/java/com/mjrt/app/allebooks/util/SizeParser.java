package com.mjrt.app.allebooks.util;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mjrt.app.allebooks.models.Size;

import java.text.DecimalFormat;

public class SizeParser {
    private static final String TAG = "SizeParser";
    private static final DecimalFormat DEC_FORMAT = new DecimalFormat("0.00");

    @NonNull
    public static Size parse(double value) {
        Log.d(TAG, "parse: " + value);
        Size.Unit unit = Size.Unit.B;
        if (value >= 921) {
            value = value / 1024;
            unit = Size.Unit.KB;
        }
        if (value >= 921) {
            value = value / 1024;
            unit = Size.Unit.MB;
        }
        if (value >= 921) {
            value = value / 1024;
            unit = Size.Unit.GB;
        }
        return new Size(Double.parseDouble(DEC_FORMAT.format(value)), unit);
    }
}