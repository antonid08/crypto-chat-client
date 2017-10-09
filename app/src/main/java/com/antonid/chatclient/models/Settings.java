package com.antonid.chatclient.models;

public class Settings {

    public static Settings getDefault() {
        return new Settings(null);
    }

    private User loggedUser;

    public Settings(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User user) {
        this.loggedUser = user;
    }

}
