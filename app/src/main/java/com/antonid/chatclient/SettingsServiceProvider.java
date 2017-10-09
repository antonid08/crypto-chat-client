package com.antonid.chatclient;

import android.content.Context;

public class SettingsServiceProvider {

    public static SettingsService getSettingsService(Context context) {
        return new SharedPreferencesSettingsService(context);
    }
}
