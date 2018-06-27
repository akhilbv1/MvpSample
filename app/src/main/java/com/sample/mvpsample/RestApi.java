package com.sample.mvpsample;

import com.sample.mvpsample.Request.LoginBodyModel;
import com.sample.mvpsample.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by akhil on 10/4/18.
 */

public interface RestApi {

    @POST("verifyPassword?key=AIzaSyCden39_px6Pjc6EYEO8GNcMpv-SY7RJqI")
    Call<LoginResponse> loginUser(@Body LoginBodyModel user);
}
