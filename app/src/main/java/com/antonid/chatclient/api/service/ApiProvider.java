package com.antonid.chatclient.api.service;

import com.antonid.chatclient.ChatApplication;
import com.antonid.chatclient.api.AuthApi;
import com.antonid.chatclient.api.ChatApi;
import com.antonid.chatclient.api.CryptoApi;

import android.support.annotation.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {

    private static final String BASE_URL = "http://192.168.0.105:8080/";

    @NonNull
    public static AuthApi getAuthApi() {
        return getRetrofit().create(AuthApi.class);
    }

    @NonNull
    public static ChatApi getChatApi() {
        return getRetrofit().create(ChatApi.class);
    }

    @NonNull
    public static CryptoApi getCryptoApi() {
        return getRetrofit().create(CryptoApi.class);
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
