package com.kotlin.github_test.mvp_test.mvp_activity;

import com.kotlin.github_test.mvp_test.mvp_base.BasePresenterActivity;
import com.kotlin.github_test.mvp_test.mvp_fragment.InputFragment;
import com.kotlin.github_test.mvp_test.mvp_view.MainVu;

public class MainActivity extends BasePresenterActivity<MainVu> {
    @Override
    protected void onBindVu() {
        super.onBindVu();
        fragmentManager.beginTransaction().replace(vu.getContentId(), InputFragment.newInstance()).commit();
    }

    @Override
    protected Class<MainVu> getVuClass() {
        return MainVu.class;
    }

    @Override
    public void afterOnResume() {
        super.afterOnResume();
    }

    @Override
    public void beforeOnPause() {
        super.beforeOnPause();
    }
}
