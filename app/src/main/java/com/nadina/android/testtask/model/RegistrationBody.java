package com.nadina.android.testtask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nadina on 27.05.2017.
 * {
 "username": "string",
 "password": "string",
 "email": "string"

 }
 */

public class RegistrationBody {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;


    public RegistrationBody(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
