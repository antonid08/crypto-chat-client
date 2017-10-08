package com.antonid.chatclient.crypto;

import android.support.annotation.NonNull;

public interface Cipher<Key> {

    String encrypt(String input, @NonNull Key key);

    String decrypt(String input, @NonNull Key key);
}
