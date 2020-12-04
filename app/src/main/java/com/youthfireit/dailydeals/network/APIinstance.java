package com.youthfireit.dailydeals.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIinstance
{
    public static Retrofit retroInstace()
    {
        String url = "http://arabianpure.com/api/";

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
