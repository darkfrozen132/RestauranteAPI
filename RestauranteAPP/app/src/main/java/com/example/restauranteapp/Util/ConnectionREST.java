package com.example.restauranteapp.Util;


import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

public class ConnectionREST {
    private static final String BASE_URL = "https://restauranteprueba1.azurewebsites.net/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getConnection() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
