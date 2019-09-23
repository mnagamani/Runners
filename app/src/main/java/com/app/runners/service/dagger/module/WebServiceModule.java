package com.app.runners.service.dagger.module;

import android.content.Context;

import com.app.runners.exceptions.InternalServerErrorException;
import com.app.runners.exceptions.PageNotFoundException;
import com.app.runners.service.api.implementation.RaceWebService;
import com.app.runners.service.api.interfaces.IRaceApiService;
import com.app.runners.service.api.interfaces.IRaceWebService;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WebServiceModule {
    
    private static OkHttpClient sOkHttpClient;
    private final Context mContext;
    
    public WebServiceModule(Context context) {
        mContext = context;
    }
    
    private String getBaseUrl() {
        return "http://849fairmount.com/mobile/";
    }

    @Provides
    @Singleton
    public IRaceApiService provideIRaceApiService() {
        return new RetrofitBuilder<>(IRaceApiService.class).create(getOkHttpClient(), getBaseUrl());
    }
    
    @Provides
    @Singleton
    public IRaceWebService provideIRaceWebService(){
        return new RaceWebService();
    }
    
    /**
     * Adds interceptor for catching unauthorized calls and handles globally.
     * May need to check if this affects API calls being handled in the current
     * context when we are going back to the login activity.
     *
     * @param chain the chain of current interceptors added to the HttpClient
     * @return client's response
     * @throws IOException
     */
    private Response handleHttpStatusCodes(Interceptor.Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        
        // Add status codes here that we want to globally handle.
        switch (response.code()) {
            case 401:
            case 403:
                break;
            case 404:
                throw new PageNotFoundException();
            case 500:
                throw new InternalServerErrorException();
        }
        
        return response;
    }
    
    @NonNull
    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(interceptor);
            builder.addInterceptor(this::handleHttpStatusCodes);
            sOkHttpClient = builder.build();
        }
        
        return sOkHttpClient;
    }
    
    private class RetrofitBuilder <T> {
        private Class<T> service;
        
        public RetrofitBuilder(Class<T> service) {
            this.service = service;
        }
        
        T create(OkHttpClient httpClient, String baseUrl) {
            Retrofit retrofit = getRetrofit(httpClient, baseUrl);
            return retrofit.create(service);
        }
        
        @NonNull
        private Retrofit getRetrofit(OkHttpClient client, String baseUrl) {
            return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build();
        }
    }
}
