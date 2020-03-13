package com.kotlin.github_test.mvp_test.mvp_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kotlin.github_test.R;

public class InputListItemView implements Vu{

    private TextView title;
    private View view;

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.list_item, container, false);
        title = (TextView) view.findViewById(R.id.title);

    }

    @Override
    public View getView() {
        return view;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

}
