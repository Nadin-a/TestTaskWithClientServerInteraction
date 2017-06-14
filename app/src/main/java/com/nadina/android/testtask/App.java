package com.nadina.android.testtask;

import android.app.Application;

import com.nadina.android.testtask.server.ServerApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nadina on 27.05.2017.
 * Connection with service.
 */

public class App extends Application {

    private static ServerApi sServerApi;
    private Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://174.138.54.52/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sServerApi = mRetrofit.create(ServerApi.class);
    }

    public static ServerApi getServerApi() {
        return sServerApi;
    }
}
