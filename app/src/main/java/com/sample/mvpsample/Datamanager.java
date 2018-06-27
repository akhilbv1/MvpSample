package com.sample.mvpsample;

import com.sample.mvpsample.Request.LoginBodyModel;
import com.sample.mvpsample.Response.LoginResponse;

import retrofit2.Call;



public class Datamanager {
    RestClient restClient = new RestClient();

    public Call<LoginResponse> loginManager(String email, String password){
        LoginBodyModel user = new LoginBodyModel();
        user.setEmail(email);
        user.setPassword(password);
        user.setReturnSecureToken(true);
        return  restClient.getLoginApiService().loginUser(user);
    }
}
