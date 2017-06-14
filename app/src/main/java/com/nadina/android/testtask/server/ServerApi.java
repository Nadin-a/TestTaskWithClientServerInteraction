package com.nadina.android.testtask.server;

import com.nadina.android.testtask.model.LoginBody;
import com.nadina.android.testtask.model.RegistrationBody;
import com.nadina.android.testtask.model.RegistrationResponse;
import com.nadina.android.testtask.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Nadina on 27.05.2017.
 */

public interface ServerApi {
    @GET("/authorization/allUsers")
    Call<List<UserModel>> getData();

    @POST("/authorization/registration")
    Call<RegistrationResponse> registration(@Body RegistrationBody registrationBody);

    @POST("/authorization/login/")
    Call<RegistrationResponse> login(@Body LoginBody loginBody);

    @GET("/authorization/logout/")
    Call<Object> LogOut();
}
