package com.app.runners.screen.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment implements BaseView {
    
    private BaseActivity mBaseActivity;
    private Presenter mPresenter;
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        
        if (context instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) context;
        } else {
            throw new RuntimeException(String.format("A BaseFragment must attach to %s", BaseActivity.class.getName()));
        }
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        mBaseActivity = null;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
    
    protected void setPresenter(Presenter presenter) {
        this.mPresenter = presenter;
    }
    
    @Override
    public void setTitle(String title) {
        mBaseActivity.setTitle(title);
    }

    @Override
    public void showDialog(String title, String message) {

    }

    @Override
    public void showToast(String message) {

    }

}
