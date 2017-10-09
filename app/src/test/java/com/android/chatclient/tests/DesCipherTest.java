package com.android.chatclient.tests;

import com.antonid.chatclient.crypto.DesCipher;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DesCipherTest {
    @Test
    public void testEncrypt() {
        DesCipher cipher = new DesCipher();
        assertEquals("Fail on valid value", "C0B7A8D05F3A829C", cipher.encrypt("123456ABCD132536", "AABB09182736CCDD"));
    }

    @Test
    public void testDecrypt() {
        DesCipher cipher = new DesCipher();
        assertEquals("Fail on valid value", "123456ABCD132536", cipher.decrypt("C0B7A8D05F3A829C", "AABB09182736CCDD"));
    }
}
