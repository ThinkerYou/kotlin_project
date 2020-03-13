package com.kotlin.github_test.mvp_test.mvp_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kotlin.github_test.mvp_test.mvp_view.Vu;

public abstract class BasePresenterFragment<V extends Vu> extends Fragment {
    public V vu;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        try {
            vu = (V) getVuClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        vu.init(inflater,container);
        onBindVu();
        view = vu.getView();
        return view;
    }

    @Override
    public void onDestroyView() {
        onDestroyVu();
        vu = null;
        super.onDestroyView();
    }

    protected void onDestroyVu(){

    }

    protected void onBindVu(){

    }

    protected abstract Class getVuClass();
}
