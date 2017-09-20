package com.antonid.chatclient.api;

import android.support.annotation.NonNull;

public interface CookiesManager {
    void save(String cookies);

    @NonNull
    String load();
}
