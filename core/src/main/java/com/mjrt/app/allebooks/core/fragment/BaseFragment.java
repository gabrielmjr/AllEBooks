package com.mjrt.app.allebooks.core.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mjrt.app.allebooks.core.activity.BaseActivity;

public abstract class BaseFragment extends Fragment {
    protected BaseActivity baseActivity;
    protected Handler handler;

    public BaseFragment(@LayoutRes int layoutRes) {
        super(layoutRes);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initializeThisAttributes();
        initializeFragment(view);
        initializeAttributes();
    }

    private void initializeThisAttributes() {
        baseActivity = (BaseActivity) requireActivity();
        handler = baseActivity.handler;
    }

    protected abstract void initializeFragment(View view);

    protected abstract void initializeAttributes();
}
