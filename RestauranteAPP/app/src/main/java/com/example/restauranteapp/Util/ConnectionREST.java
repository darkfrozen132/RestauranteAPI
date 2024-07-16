package com.example.restauranteapp.Util;


import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ConnectionREST {
    private static final String URL = "http://accountsmart-001-site1.htempurl.com/api/";
    private static Retrofit retrofit = null;
    private static final String USERNAME = "11186292";
    private static final String PASSWORD = "60-dayfreetrial";
    public static Retrofit getConnection() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    okhttp3.Request original = chain.request();
                    okhttp3.Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", Credentials.basic(USERNAME, PASSWORD))
                            .method(original.method(), original.body());
                    okhttp3.Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            OkHttpClient client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}