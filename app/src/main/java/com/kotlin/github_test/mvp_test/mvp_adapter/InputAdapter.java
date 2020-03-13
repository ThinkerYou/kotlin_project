package com.kotlin.github_test.mvp_test.mvp_adapter;

import com.kotlin.github_test.mvp_test.mvp_bean.InputBean;
import com.kotlin.github_test.mvp_test.mvp_view.InputListItemView;

import java.util.ArrayList;
import java.util.List;

public class InputAdapter extends BasePresenterAdapter<InputListItemView>{
    List<String> titles = new ArrayList<>(InputBean.VALUES_MAP.keySet());
    @Override
    public void bindListViewItem(int position) {
        String title = titles.get(position);
        vu.setTitle(title);
    }

    @Override
    public Class<InputListItemView> getVuClase() {
        return InputListItemView.class;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    public String getTitle(int position){
        return (String) getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
