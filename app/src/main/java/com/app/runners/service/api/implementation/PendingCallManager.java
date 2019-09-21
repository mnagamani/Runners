package com.app.runners.service.api.implementation;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Keeps track of all pending API web calls being made in this class instance.
 * This allows us to terminate calls being made when the app loses context
 * and prevents crashes when the API callbacks are handled with bad context.
 */
public interface PendingCallManager {
    /**
     * Used to keep track of all pending API web calls being made in our models (per instance).
     *
     * @param call the {@link Retrofit} call being made.
     */
    void addPendingCall(Call call);
    
    /**
     * Should be used when the call has completed. Note that this should be called
     * when either the call has finished with success, failure, or offline.
     * This will remove the call from the queue of calls to cancel.
     *
     * @param call the {@link Retrofit} call being made.
     */
    void removePendingCall(Call call);
    
    /**
     * This implementation should iterate over the queue and cancel each call.
     * This is so that when each pending call completes, it will not perform
     * its callback task(s) on a null or bad Context and crash the application.
     */
    void cancelPendingCalls();
}
