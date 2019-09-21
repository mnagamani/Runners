package com.app.runners.service.api.implementation;

import android.content.Context;

import com.app.runners.app.CoreApplication;
import com.app.runners.callbacks.ObjectCallback;
import com.app.runners.callbacks.RetrofitCallback;
import com.app.runners.exceptions.OfflineException;
import com.app.runners.model.Race;
import com.app.runners.service.api.interfaces.IRaceApiService;
import com.app.runners.service.api.interfaces.IRaceWebService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;

public class RaceWebService implements IRaceWebService, PendingCallManager {

    @Inject IRaceApiService mRaceWebService;
    private List<Call> mPendingCalls;

    public RaceWebService() {
        CoreApplication.getComponent().inject(this);
    }
    

    @Override
    public void addPendingCall(Call call) {
        if (mPendingCalls == null) {
            mPendingCalls = new ArrayList<>();
        }

        mPendingCalls.add(call);
    }

    @Override
    public void removePendingCall(Call call) {
        if (mPendingCalls != null) {
            mPendingCalls.remove(call);
        }
    }

    @Override
    public void cancelPendingCalls() {
        if (mPendingCalls != null) {
            for (Call call : mPendingCalls) {
                call.cancel();
            }
        }
    }
    
    @Override
    public void getRaces(Context context, ObjectCallback<Race> successCallback, ObjectCallback<Throwable> errorCallback) {
        Call<Race> call = mRaceWebService.getRace();
    
        ApiService.enqueue(context, call, this, new RetrofitCallback<Race>() {
            @Override
            public void onOffline() {
                onFailure(call, new OfflineException());
            }
        
            @Override
            public void onResponse(Call<Race> call, Response<Race> response) {
                if (response != null && response.isSuccessful()) {
                
                    if (response.body()!= null) {
                        successCallback.onCallback(response.body());
                    } else {
                        onFailure(call, new Exception("schema is null"));
                    }
                } else {
                    onFailure(call, new Exception("call failed"));
                }
            }
        
            @Override
            public void onFailure(Call call, Throwable t) {
                if (errorCallback != null) {
                    errorCallback.onCallback(t);
                }
            }
        });
    }
    
    @Override
    public void cancelApiCalls() {
        //cancel web calls here as necessary
        cancelPendingCalls();
    }
}
