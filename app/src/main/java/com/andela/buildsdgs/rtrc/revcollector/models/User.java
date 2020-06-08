package com.andela.buildsdgs.rtrc.revcollector.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("is_active")
    private String isActive;
    @SerializedName("is_staff")
    private String isStaff;
    private String phone;
    @SerializedName("is_collector")
    private String isCollector;
    private String name;
    private String pk;
    @SerializedName("date_joined")
    private String dateJoined;
    @SerializedName("is_user")
    private String isUser;
    private String email;
    private String username;
    private String password;
    private String password1;
    private String password2;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(String isStaff) {
        this.isStaff = isStaff;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsCollector() {
        return isCollector;
    }

    public void setIsCollector(String isCollector) {
        this.isCollector = isCollector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getIsUser() {
        return isUser;
    }

    public void setIsUser(String isUser) {
        this.isUser = isUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
