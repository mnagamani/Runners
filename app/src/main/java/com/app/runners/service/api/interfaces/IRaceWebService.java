package com.app.runners.service.api.interfaces;

import android.content.Context;

import com.app.runners.callbacks.ObjectCallback;
import com.app.runners.model.Race;

public interface IRaceWebService extends WebService{
    
     void getRaces(Context context, ObjectCallback<Race> successCallback, ObjectCallback<Throwable> errorCallback);
}
