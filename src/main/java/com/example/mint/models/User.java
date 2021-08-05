package com.example.mint.models;


import com.example.mint.models.enums.PhoneCode;

public class User {

    private String login;
    private String password;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private PhoneCode phoneCode;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }



    public String getLogin() {
        if(login == null) return "";
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        if(password == null) return "";
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public User setPhoneCode(PhoneCode phoneCode) {
        this.phoneCode = phoneCode;
        return this;
    }



}
