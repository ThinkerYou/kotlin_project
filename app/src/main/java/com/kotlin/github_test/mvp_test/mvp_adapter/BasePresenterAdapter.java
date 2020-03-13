package com.kotlin.github_test.mvp_test.mvp_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kotlin.github_test.mvp_test.mvp_view.Vu;

public abstract class BasePresenterAdapter<V extends Vu> extends BaseAdapter {
    public V vu;
    private V vuClass;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            try {
                vu = (V) getVuClase().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            vu.init(inflater,parent);
            convertView =vu.getView();
            convertView.setTag(vu);
        }else {
            vu = (V)convertView.getTag();
        }
        if(convertView!=null){
            bindListViewItem(position);
        }
        return convertView;
    }

    public abstract void bindListViewItem(int position);
    public abstract Class<V> getVuClase();
}
