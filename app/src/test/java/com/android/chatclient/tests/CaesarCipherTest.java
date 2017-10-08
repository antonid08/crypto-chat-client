package com.android.chatclient.tests;

import com.antonid.chatclient.crypto.CaesarCipher;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;


public class CaesarCipherTest {
    @Test
    public void testEncrypt() {
        CaesarCipher cipher = new CaesarCipher();
        assertEquals("Fail on valid value", "]z\u0081\u0081\u0084", cipher.encrypt("Hello", 21));
    }

    @Test
    public void testDecrypt() {
        CaesarCipher cipher = new CaesarCipher();
        assertEquals("Fail on valid value", "Hello", cipher.decrypt("]z\u0081\u0081\u0084", 21));
    }
}
