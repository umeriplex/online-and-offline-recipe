package com.example.mrcook.retrofot;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {
    private static String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static Retrofit retrofit;

    public ApiClint(){
        initRetrofit();
    }

    private void initRetrofit(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public Retrofit getRetrofit(){
        return retrofit;
    }

}
