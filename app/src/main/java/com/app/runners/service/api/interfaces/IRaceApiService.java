package com.app.runners.service.api.interfaces;

import com.app.runners.model.Race;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IRaceApiService {

    /**
     * api call to return the race
     * @GET
     * @return
     */
    @GET("runners.json")
    Call<Race> getRace();
}
