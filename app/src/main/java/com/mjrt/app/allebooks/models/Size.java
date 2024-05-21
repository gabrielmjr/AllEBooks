package com.mjrt.app.allebooks.models;

import androidx.annotation.NonNull;

public class Size {
    private final double value;
    private final Unit unit;

    public Size(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    @NonNull
    @Override
    public String toString() {
        return value + unit.toString();
    }

    public enum Unit {
        B,
        KB,
        MB,
        GB
    }
}
