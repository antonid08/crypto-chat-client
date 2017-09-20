package com.antonid.chatclient.api;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class SharedPreferencesCookiesManager implements CookiesManager {

    private static final String COOKIES_PREFERENCES = "COOKIES_PREFERENCES";
    private static final String COOKIES_KEY = "COOKIES_KEY";

    private Context context;

    public SharedPreferencesCookiesManager(Context context) {
        this.context = context;
    }


    @Override
    public void save(String cookie) {
        SharedPreferences.Editor editor = context.getSharedPreferences(COOKIES_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putString(COOKIES_KEY, cookie);
        editor.apply();
    }

    @NonNull
    @Override
    public String load() {
        return context.getSharedPreferences(COOKIES_PREFERENCES, Context.MODE_PRIVATE).getString(COOKIES_KEY, "");
    }
}
