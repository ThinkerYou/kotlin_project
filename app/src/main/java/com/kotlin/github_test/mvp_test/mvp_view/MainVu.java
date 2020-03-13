package com.kotlin.github_test.mvp_test.mvp_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.kotlin.github_test.R;

public class MainVu implements Vu {

    public View view;
    public FrameLayout content;

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.activity_main, container,false);
        content = (FrameLayout) view.findViewById(R.id.content);
    }

    public int getContentId(){
        return R.id.content;
    }

    @Override
    public View getView() {
        return view;
    }
}
