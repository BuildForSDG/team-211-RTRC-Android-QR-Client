package com.andela.buildsdgs.rtrc.revcollector.models;

public class User {
    private String is_active;
    private String is_staff;
    private String phone;
    private String is_collector;
    private String name;
    private String pk;
    private String date_joined;
    private String is_user;
    private String email;
    private String username;
    private String password;
    private String password1;
    private String password2;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(String is_staff) {
        this.is_staff = is_staff;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIs_collector() {
        return is_collector;
    }

    public void setIs_collector(String is_collector) {
        this.is_collector = is_collector;
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

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }

    public String getIs_user() {
        return is_user;
    }

    public void setIs_user(String is_user) {
        this.is_user = is_user;
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

    @Override
    public String toString() {
        return "User{" +
                "is_active='" + is_active + '\'' +
                ", is_staff='" + is_staff + '\'' +
                ", phone='" + phone + '\'' +
                ", is_collector='" + is_collector + '\'' +
                ", name='" + name + '\'' +
                ", pk='" + pk + '\'' +
                ", date_joined='" + date_joined + '\'' +
                ", is_user='" + is_user + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }
}
