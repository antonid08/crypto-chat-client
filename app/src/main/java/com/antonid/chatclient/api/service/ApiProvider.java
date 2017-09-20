package com.antonid.chatclient.api.service;

import com.antonid.chatclient.ChatApplication;
import com.antonid.chatclient.api.AuthApi;
import com.antonid.chatclient.api.SharedPreferencesCookiesManager;

import android.support.annotation.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {

    private static final String BASE_URL = "http://192.168.10.83:8080/";

    @NonNull
    public static AuthApi getChatApi() {
        return getRetrofit().create(AuthApi.class);
    }

    @NonNull
    private static Retrofit getRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new AddCookiesInterceptor(new SharedPreferencesCookiesManager(ChatApplication.getInstance())))
            .addInterceptor(new ReceivedCookiesInterceptor(new SharedPreferencesCookiesManager(ChatApplication.getInstance())))
            .build();

        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    }
}
