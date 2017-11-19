package com.android.chatclient.tests;

import com.antonid.chatclient.crypto.DesCipher;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DesCipherTest {
    @Test
    public void testEncryptWithFullBlock() {
        DesCipher cipher = new DesCipher();
        assertEquals("Fail on valid value",
                "©<¦wO\u00AD\u0016\u0093Ò\u0085]\u0099Ùr2\u0089\fçs¹;0+\u0014",
                cipher.encrypt("12345678hello123", "ABCDdcab"));
    }

    @Test
    public void testDecryptWithFullBlock() {
        DesCipher cipher = new DesCipher();
        assertEquals("Fail on valid value", "12345678hello123",
                cipher.decrypt(
                        "©<¦wO\u00AD\u0016\u0093Ò\u0085]\u0099Ùr2\u0089\fçs¹;0+\u0014", "ABCDdcab"));
    }

    @Test
    public void testEncryptWithBlockAppend() {
        DesCipher cipher = new DesCipher();
        assertEquals("Fail on valid value", "\u0095\u0002Q\u0004\u008C\u008Dô\u0093", cipher.encrypt("12345", "ABCD5678"));
    }

    @Test
    public void testDecryptWithBlockAppend() {
        DesCipher cipher = new DesCipher();
        assertEquals("Fail on valid value", "12345", cipher.decrypt("\u0095\u0002Q\u0004\u008C\u008Dô\u0093", "ABCD5678"));
    }
}
