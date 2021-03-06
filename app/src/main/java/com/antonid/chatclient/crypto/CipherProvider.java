package com.antonid.chatclient.crypto;


import com.antonid.chatclient.models.Encryption;

import java.util.HashMap;
import java.util.Map;

public class CipherProvider {

    private static Map<Encryption.Type, Cipher> MAPPING = new HashMap<>();

    static {
        MAPPING.put(Encryption.Type.CAESAR, new CaesarCipher());
    }

    public Cipher getCipher(Encryption.Type type) {
        return MAPPING.get(type);
    }
}
