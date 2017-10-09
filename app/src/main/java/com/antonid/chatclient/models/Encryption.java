package com.antonid.chatclient.models;

public class Encryption {

    private Type type;
    private Key key;

    public Encryption(Type type, Key key) {
        this.type = type;
        this.key = key;
    }

    public Type getType() {
        return type;
    }

    public Key getKey() {
        return key;
    }

    public static class Key {

        private String privateKey;
        private String publicKey;

        public Key(String privateKey, String publicKey) {
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }

        public Key(String singleKey) {
            this.privateKey = singleKey;
            this.publicKey = singleKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }
    }

    public enum Type {
        CAESAR
    }
}
