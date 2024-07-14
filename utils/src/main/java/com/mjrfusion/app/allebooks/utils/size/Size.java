package com.mjrfusion.app.allebooks.utils.size;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

public class Size implements Parcelable {
    private final double value;
    private final Unit unit;

    public Size(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Size(@NonNull String stringSize) {
        var length = stringSize.length();
        if (String.valueOf(stringSize.charAt(length - 2)).matches("[K,M,G]")) {
            unit = Unit.valueOf(stringSize.charAt(length - 2) + ""
                    + stringSize.charAt(length - 1));
        } else {
            unit = Unit.valueOf(String.valueOf(stringSize.charAt(length - 1)));
        }
        value = Double.parseDouble(stringSize.replaceAll("[^0-9,.]", ""));
    }

    public Size(@NonNull Parcel in) {
        value = in.readDouble();
        unit = (Unit) in.readValue(Unit.class.getClassLoader());
    }

    public static final Creator<Size> CREATOR = new Creator<>() {
        @NonNull
        @Contract("_ -> new")
        @Override
        public Size createFromParcel(Parcel in) {
            return new Size(in);
        }

        @NonNull
        @Contract(value = "_ -> new", pure = true)
        @Override
        public Size[] newArray(int size) {
            return new Size[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeDouble(value);
        dest.writeValue(unit);
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
