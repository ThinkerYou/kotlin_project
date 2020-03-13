package com.kotlin.github_test.mvp_test.mvp_base;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kotlin.github_test.mvp_test.mvp_view.MainVu;
import com.kotlin.github_test.mvp_test.mvp_view.Vu;

import org.greenrobot.eventbus.EventBus;

public abstract class BasePresenterActivity<V extends Vu> extends Activity {
    protected V vu;
    protected FragmentManager fragmentManager;
    protected EventBus eventBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
        eventBus = EventBus.getDefault();
        try {
            vu = (V) getVuClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        vu.init(getLayoutInflater(),null);
       setContentView(vu.getView());
       onBindVu();
    }

    @Override
    protected final void onResume() {
        afterOnResume();
        super.onResume();
    }

    public void afterOnResume() {
        
    }

    @Override
    protected void onPause() {
        beforeOnPause();
        super.onPause();
    }

    public void beforeOnPause() {

    }

    /**
     * 绑定数据
     */
    protected void onBindVu() {

    }

    /**
     * 返回当前的View对象
     *
     * @return
     */
    protected abstract Class<MainVu> getVuClass();
}
