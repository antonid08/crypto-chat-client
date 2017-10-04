package com.antonid.chatclient;


import com.antonid.chatclient.models.Settings;

public interface SettingsService {
    void save(Settings settings);

    Settings load();
}
