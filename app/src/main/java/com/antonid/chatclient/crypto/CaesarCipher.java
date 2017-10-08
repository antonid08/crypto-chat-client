package com.antonid.chatclient.crypto;

import android.support.annotation.NonNull;

public class CaesarCipher implements Cipher<Integer> {

    public String encrypt(String input, @NonNull Integer key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be not null.");
        }

        StringBuilder result = new StringBuilder();
        int len = input.length();
        for (int x = 0; x < len; x++) {
            char c = (char) (input.charAt(x) + key);
            result.append(c);
        }
        return result.toString();
    }

    public String decrypt(String encrypted, @NonNull Integer key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be not null.");
        }

        StringBuilder result = new StringBuilder();
        int len = encrypted.length();
        for (int x = 0; x < len; x++) {
            char c = (char) (encrypted.charAt(x) - key);
            result.append(c);
        }
        return result.toString();
    }

}
