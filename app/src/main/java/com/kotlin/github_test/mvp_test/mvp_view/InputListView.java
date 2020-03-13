package com.kotlin.github_test.mvp_test.mvp_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.kotlin.github_test.R;
import com.kotlin.github_test.mvp_test.mvp_activity.VuCallBack;

public class InputListView implements Vu {

    public View view;
    public ListView listView;
    public VuCallBack<Integer> vuSelectCallBack;

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.list_view, container, false);
        listView = (ListView) view.findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(vuSelectCallBack !=null){
                    vuSelectCallBack.execute(position);
                }
            }
        });
    }

    @Override
    public View getView() {
        return view;
    }

    public void  setAdapter(ListAdapter adapter){
       listView.setAdapter(adapter);
    }

    public void setSelectCallBack(VuCallBack<Integer> selectCallBack) {
       vuSelectCallBack = selectCallBack;
    }
}
