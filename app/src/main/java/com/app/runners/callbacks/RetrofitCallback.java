package com.app.runners.callbacks;

import retrofit2.Callback;

public interface RetrofitCallback<T> extends Callback<T> {
    void onOffline();
}
