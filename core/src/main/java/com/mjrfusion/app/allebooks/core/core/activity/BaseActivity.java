package com.mjrfusion.app.allebooks.core.core.activity;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.mjrfusion.app.allebooks.core.R;

public abstract class BaseActivity extends AppCompatActivity {
    public Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeThisAttributes();
        if (initializeActivity())
            setDefaultToolbar();
        initializeAttributes();
    }

    private void initializeThisAttributes() {
        handler = new Handler(getMainLooper());
    }

    protected abstract boolean initializeActivity();

    private void setDefaultToolbar() {
        setToolbar(R.id.toolbar);
    }

    private void setToolbar(@IdRes int toolbar) {
        setSupportActionBar(findViewById(toolbar));
    }


    public void replaceFragmentBy(@IdRes int fragmentContainer, @NonNull Fragment fragment, Bundle args) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(fragment.getTag())
                .replace(fragmentContainer, fragment.getClass(), args, fragment.getTag())
                .commit();
    }

    protected abstract void initializeAttributes();
}