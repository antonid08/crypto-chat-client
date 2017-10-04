package com.antonid.chatclient.api.service;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class AddCookiesInterceptor implements Interceptor {

    private CookiesManager cookiesManager;

    AddCookiesInterceptor(CookiesManager cookiesManager) {
        this.cookiesManager = cookiesManager;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Cookie", cookiesManager.load());

        return chain.proceed(builder.build());
    }
}
