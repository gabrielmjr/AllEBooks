package com.mjrt.app.allebooks.utils.size;

import androidx.annotation.NonNull;

public class Size {
    private final double value;
    private final Unit unit;

    public Size(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Size(@NonNull String stringSize) {
        var lenght = stringSize.length();
        if (String.valueOf(stringSize.charAt(lenght - 2)).matches("KMG")) {
            unit = Unit.valueOf(stringSize.charAt(lenght - 2) + ""
                    + stringSize.charAt(lenght - 1));
        } else {
            unit = Unit.valueOf(String.valueOf(stringSize.charAt(lenght - 1)));
        }
        value = Double.parseDouble(stringSize.replaceAll("[^0-9]", ""));
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
