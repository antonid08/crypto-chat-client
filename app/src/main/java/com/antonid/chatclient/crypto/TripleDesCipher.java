package com.antonid.chatclient.crypto;

import android.support.annotation.NonNull;

public class TripleDesCipher implements Cipher {

    /**
     * Ключ является составным из трех ключей по 8 символов.
     */
    @Override
    public String encrypt(String input, @NonNull String key) {
        Cipher desCipher = new DesCipher();

        String result = desCipher.encrypt(input, key.substring(0, 8));
        result = desCipher.encrypt(result, key.substring(8, 16));
        result = desCipher.encrypt(result, key.substring(16, 24));

        return result;
    }

    /**
     * Передается ключ в таком же виде как и при зашифровании.
     */
    @Override
    public String decrypt(String input, @NonNull String key) {
        Cipher desCipher = new DesCipher();

        String result = desCipher.decrypt(input, key.substring(16, 24));
        result = desCipher.decrypt(result, key.substring(8, 16));
        result = desCipher.decrypt(result, key.substring(0, 8));

        return result;
    }


}
