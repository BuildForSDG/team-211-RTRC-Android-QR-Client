package com.andela.buildsdgs.rtrc.revcollector.models;

public class UserDetail {
    private String token;
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}
