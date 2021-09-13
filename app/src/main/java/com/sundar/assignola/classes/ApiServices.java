package com.sundar.assignola.classes;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServices {
    public static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit retrofit = null;
    private static OkHttpClient.Builder httpClientBuilder = null;

    /*For HTTP base URL*/
    public static Retrofit apiService(Context context) {
        httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                   .baseUrl(Constants.BASE_URL_API)
                    .client(httpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static Retrofit apiService(Context context, int connectionTimeOut, int readTimeOut, int writeTimeOut) {
        httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(connectionTimeOut, TimeUnit.MINUTES)
                .readTimeout(readTimeOut, TimeUnit.MINUTES)
                .writeTimeout(writeTimeOut, TimeUnit.MINUTES);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL_API)
                    .client(httpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
