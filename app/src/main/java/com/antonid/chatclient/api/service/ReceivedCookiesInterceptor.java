package com.antonid.chatclient.api.service;

import java.io.IOException;

import com.antonid.chatclient.api.CookiesManager;

import okhttp3.Interceptor;
import okhttp3.Response;

class ReceivedCookiesInterceptor implements Interceptor {

    private CookiesManager cookiesManager;

    ReceivedCookiesInterceptor(CookiesManager cookiesManager) {
        this.cookiesManager = cookiesManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            cookiesManager.save(originalResponse.headers("Set-Cookie").get(0));
        }

        return originalResponse;
    }
}
