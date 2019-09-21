package com.app.runners.service.api.implementation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.app.runners.callbacks.RetrofitCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiService {
    /**
     * Enqueues a Retrofit call if there is internet connection.
     * This way we don't have to add the check everywhere we make an API call.
     * @param context current app context to check if there's internet.
     * @param call Retrofit call to execute.
     * @param manager the BaseActivity, BaseFragment, or BaseModel making the api call.
     * @param callback The API callback for if the call succeeded, failed, or was offline.
     */
    public static void enqueue(Context context, Call call, final PendingCallManager manager, final RetrofitCallback callback) {
        if (hasInternet(context)) {
            if (manager != null) {
                manager.addPendingCall(call);
            }
            
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (manager != null) {
                        manager.removePendingCall(call);
                    }
                    
                    callback.onResponse(call, response);
                }
                
                @Override
                public void onFailure(Call call, Throwable t) {
                    if (t.getMessage() != null) {
                        Log.e("Retrofit onFailure", t.getMessage());
                    }
                    
                    t.printStackTrace();
                    
                    if (manager != null) {
                        manager.removePendingCall(call);
                    }
                    if (!call.isCanceled()) {
                        callback.onFailure(call, t);
                    }
                }
            });
        } else {
            if (manager != null) {
                manager.removePendingCall(call);
            }
            
            callback.onOffline();
        }
    }
    
    public static boolean hasInternet(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
