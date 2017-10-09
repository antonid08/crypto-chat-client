package com.antonid.chatclient.crypto;

import android.support.annotation.NonNull;

public interface Cipher {

    String encrypt(String input, @NonNull String key);

    String decrypt(String input, @NonNull String key);
}
