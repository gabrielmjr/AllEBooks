package com.mjrfusion.app.allebooks.model;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class Main_Option {
    @DrawableRes
    private int animatedIcon;

    @StringRes
    private int label;

    public Main_Option(@DrawableRes int animatedIcon, @StringRes int label) {
        this.animatedIcon = animatedIcon;
        this.label = label;
    }

    public int getAnimatedIcon() {
        return animatedIcon;
    }

    public void setAnimatedIcon(@DrawableRes int animatedIcon) {
        this.animatedIcon = animatedIcon;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(@StringRes int label) {
        this.label = label;
    }
}