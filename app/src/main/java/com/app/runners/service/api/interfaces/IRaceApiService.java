package com.app.runners.service.api.interfaces;

import com.app.runners.model.Race;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IRaceApiService {
    
    @GET("runners.json")
    Call<Race> getRace();
}
