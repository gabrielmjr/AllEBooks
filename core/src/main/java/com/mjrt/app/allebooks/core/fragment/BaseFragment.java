package com.mjrt.app.allebooks.core.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    public BaseFragment(@LayoutRes int layoutRes) {
        super(layoutRes);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initializeFragment(view);
        initializeAttributes();
    }

    protected abstract void initializeFragment(View view);

    protected abstract void initializeAttributes();
}
