package com.antonid.chatclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.antonid.chatclient.models.Settings;
import com.google.gson.Gson;

/**
 * Created by antonid on 04.10.17.
 */

class SharedPreferencesSettingsService implements SettingsService {

    private static final String PREFERENCES_NAME = "app_prefs";

    private static final String SETTINGS_KEY = "SETTINGS_KEY";

    private Context context;

    SharedPreferencesSettingsService(@NonNull Context context) {
        this.context = context;
    }


    @Override
    public Settings load() {
        String settingsString = getSharedPreferences().getString(SETTINGS_KEY, "");

        if (!settingsString.isEmpty()) {
            return new Gson().fromJson(settingsString, Settings.class);
        } else {
            return Settings.getDefault();
        }
    }

    @Override
    public void save(Settings settings) {
        getSharedPreferencesEditor().putString(SETTINGS_KEY, new Gson().toJson(settings)).apply();
    }

    private SharedPreferences.Editor getSharedPreferencesEditor() {
        return getSharedPreferences().edit();
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}

