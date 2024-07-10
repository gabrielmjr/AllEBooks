package com.mjrfusion.app.allebooks.model;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class MainOption {
    @DrawableRes
    private int icon;

    @StringRes
    private int label;

    public MainOption(@DrawableRes int icon, @StringRes int label) {
        this.icon = icon;
        this.label = label;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(@DrawableRes int icon) {
        this.icon = icon;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(@StringRes int label) {
        this.label = label;
    }
}