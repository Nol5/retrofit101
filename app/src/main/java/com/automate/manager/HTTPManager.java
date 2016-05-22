package com.automate.manager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HTTPManager {

    private static HTTPManager instance;
    final private Retrofit retrofit;

    public static HTTPManager getInstance() {
        if (instance == null)
            instance = new HTTPManager();
        return instance;
    }

    private HTTPManager(){
          retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.168:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}