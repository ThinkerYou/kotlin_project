package com.kotlin.github_test.mvp_test.mvp_fragment;

import android.widget.Toast;

import com.kotlin.github_test.mvp_test.mvp_activity.VuCallBack;
import com.kotlin.github_test.mvp_test.mvp_adapter.InputAdapter;
import com.kotlin.github_test.mvp_test.mvp_view.InputListView;

public class InputFragment extends BasePresenterFragment<InputListView> {
    InputAdapter inputAdapter = new InputAdapter();
    VuCallBack<Integer> selectCallBack  = new VuCallBack<Integer>() {
        @Override
        public void execute(Integer result) {
            Toast.makeText(getActivity(),""+result,Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected Class<InputListView> getVuClass() {
        return InputListView.class;
    }

    public static InputFragment newInstance(){
        return new InputFragment();
    }

    @Override
    protected void onBindVu() {
        super.onBindVu();
        vu.setAdapter(inputAdapter);
        vu.setSelectCallBack(selectCallBack);
    }

}
