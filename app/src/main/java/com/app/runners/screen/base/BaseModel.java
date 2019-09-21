package com.app.runners.screen.base;

import android.content.Context;

import androidx.annotation.CallSuper;

public class BaseModel {
    protected Context mContext;
    
    public BaseModel(Context context) {
        mContext = context;
    }

    @CallSuper
    public void onDestroy() {
        
    }
}
