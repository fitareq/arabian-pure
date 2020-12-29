package com.youthfireit.dailydeals.network;

import com.youthfireit.dailydeals.utils.Constt;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIinstance
{
    public static Retrofit retroInstace()
    {

        return new Retrofit.Builder()
                .baseUrl(Constt.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
