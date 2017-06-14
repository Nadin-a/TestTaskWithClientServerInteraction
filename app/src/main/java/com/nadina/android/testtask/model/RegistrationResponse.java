package com.nadina.android.testtask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nadina on 27.05.2017.
 */

public class RegistrationResponse {
    @SerializedName("token")
    @Expose
    public String token;

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "RegistrationResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
