package com.android.chatclient.tests;


import com.antonid.chatclient.crypto.TripleDesCipher;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TripleDesCipherTest {
    @Test
    public void testEncryptWithFullBlock() {
        TripleDesCipher cipher = new TripleDesCipher();
        assertEquals("Fail on valid value", "\rÀ´q1\u001C\u0004Òç£\u0095\u008Du&\u000Eg©Zå\u009F\u0017P<A\u0095b5ajú¯\u008B\u0096Ä\u0097\u0014¹E\u0011Ú",
                cipher.encrypt("12345678hello123", "ABCDdcab12349876jjjjaaaa"));
    }

    @Test
    public void testDecryptWithFullBlock() {
        TripleDesCipher cipher = new TripleDesCipher();
        assertEquals("Fail on valid value", "12345678hello123",
                cipher.decrypt(
                        "\rÀ´q1\u001C\u0004Òç£\u0095\u008Du&\u000Eg©Zå\u009F\u0017P<A\u0095b5ajú¯\u008B\u0096Ä\u0097\u0014¹E\u0011Ú",
                        "ABCDdcab12349876jjjjaaaa"));
    }

    @Test
    public void testEncryptWithBlockAppend() {
        TripleDesCipher cipher = new TripleDesCipher();
        assertEquals("Fail on valid value",
                "|\u0010FÆG¡Í\u008CÒ\u0095Qh<\u0089\u00AD_qU0þ\u0017õ\u0083\u0081",
                cipher.encrypt("12345", "ABCD567811111111';lkjhgf"));
    }

    @Test
    public void testDecryptWithBlockAppend() {
        TripleDesCipher cipher = new TripleDesCipher();
        assertEquals("Fail on valid value", "12345",
                cipher.decrypt("|\u0010FÆG¡Í\u008CÒ\u0095Qh<\u0089\u00AD_qU0þ\u0017õ\u0083\u0081",
                        "ABCD567811111111';lkjhgf"));
    }
}
