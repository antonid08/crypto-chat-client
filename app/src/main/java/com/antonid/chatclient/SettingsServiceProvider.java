package com.antonid.chatclient;

import android.content.Context;

/**
 * Created by antonid on 04.10.17.
 */

public class SettingsServiceProvider {

    public static SettingsService getSettingsService(Context context) {
        return new SharedPreferencesSettingsService(context);
    }
}
