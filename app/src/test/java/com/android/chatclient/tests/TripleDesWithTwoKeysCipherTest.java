package com.android.chatclient.tests;

import com.antonid.chatclient.crypto.TripleDesWithTwoKeysCipher;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TripleDesWithTwoKeysCipherTest {

    @Test
    public void testEncryptWithFullBlock() {
        TripleDesWithTwoKeysCipher cipher = new TripleDesWithTwoKeysCipher();
        assertEquals("Fail on valid value", "úCY\u009CÞZ]5\u0083úÏ\u0082\u000BÅ\u009F>[\u0005Ô1<}Ì¾\u0000\u0005\u0085&\u0087zy\u001D\fçs¹;0+\u0014",
                cipher.encrypt("12345678hello122", "ABCDdcab12349876"));
    }

    @Test
    public void testDecryptWithFullBlock() {
        TripleDesWithTwoKeysCipher cipher = new TripleDesWithTwoKeysCipher();
        assertEquals("Fail on valid value", "12345678hello122",
                cipher.decrypt(
                        "úCY\u009CÞZ]5\u0083úÏ\u0082\u000BÅ\u009F>[\u0005Ô1<}Ì¾\u0000\u0005\u0085&\u0087zy\u001D\fçs¹;0+\u0014",
                        "ABCDdcab12349876"));
    }

    @Test
    public void testEncryptWithBlockAppend() {
        TripleDesWithTwoKeysCipher cipher = new TripleDesWithTwoKeysCipher();
        assertEquals("Fail on valid value",
                "ûOºÆ+g¥Û[¾ÕG'¸@+ö<V?7&Æª",
                cipher.encrypt("123456", "ABCD567811111111"));
    }

    @Test
    public void testDecryptWithBlockAppend() {
        TripleDesWithTwoKeysCipher cipher = new TripleDesWithTwoKeysCipher();
        assertEquals("Fail on valid value", "123456",
                cipher.decrypt("ûOºÆ+g¥Û[¾ÕG'¸@+ö<V?7&Æª",
                        "ABCD567811111111"));
    }
}
