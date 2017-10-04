package com.antonid.chatclient.models;

public class User {

    private String username;

    private Encryption encryption;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);

    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    public User(String username, Encryption encryption) {
        if (username == null) {
            throw new IllegalArgumentException("Username must be not null.");
        }
        this.username = username;
        this.encryption = encryption;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }
}
