package com.kotlin.github_test.mvp_test.mvp_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 主要用来初始化view和返回当前的view
 */
public interface Vu {
    //初始化view
    public void init(LayoutInflater inflater, ViewGroup container);
    //返回一个根的view
    public View getView();
}
