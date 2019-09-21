package com.app.runners.screen.base;

public class BasePresenter implements Presenter {
    private BaseView mView;
    private BaseModel mModel;
    
    public BasePresenter(BaseView view, BaseModel model) {
        mView = view;
        mModel = model;
    }
    
    @Override
    public void onCreated() {
    }
    
    @Override
    public void onStart() {
        
    }
    
    @Override
    public void onResume() {
        
    }
    
    @Override
    public void onPause() {
        
    }
    
    @Override
    public void onStop() {
        
    }
    
    @Override
    public void onDestroy() {
        
        if (mModel != null) {
            mModel.onDestroy();
        }
    }
}
