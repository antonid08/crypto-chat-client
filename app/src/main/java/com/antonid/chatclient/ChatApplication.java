package com.antonid.chatclient;

import android.app.Application;

/**
 * Created by antonenkoid on 11.9.17.
 */

public class ChatApplication extends Application {

    private static ChatApplication instance;

    public static ChatApplication getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Application isn't initialized yet!");
        }
        return instance;
    }

    public void onCreate() {
        super.onCreate();

        instance = this;
    }

}
