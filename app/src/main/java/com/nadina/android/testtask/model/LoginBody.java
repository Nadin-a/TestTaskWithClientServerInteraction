package com.nadina.android.testtask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nadina on 28.05.2017.
 * {
 "username": "string",
 "email": "string",
 "password": "string"
 }
 */

public class LoginBody {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;



    public LoginBody(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    @Override
    public String toString() {
        return "RegistrationBody{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
