package com.learner.androidpaginationexample.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private String BASE_URL = "http://192.168.1.104/restapi/public/";
    private static ApiClient mInstance;
    private Retrofit retrofit;

    public ApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiClient getInstance() {
        if (mInstance == null)
            mInstance = new ApiClient();
        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
