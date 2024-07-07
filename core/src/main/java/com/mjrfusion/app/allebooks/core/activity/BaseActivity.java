package com.mjrfusion.app.allebooks.core.activity;

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


    public void replaceFragmentBy(
            @IdRes int fragmentContainer,
            @NonNull Class<? extends Fragment> fragment,
            String tag,
            Bundle args,
            boolean addToBackStack) {
        var supportFragmentTransaction = getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainer, fragment, args, tag);
        if (addToBackStack)
            supportFragmentTransaction.addToBackStack(tag);
        supportFragmentTransaction.commit();
    }

    protected abstract void initializeAttributes();
}