package com.sample.mvpsample.Login;

import android.content.Context;
import android.util.Log;

import com.sample.mvpsample.Datamanager;
import com.sample.mvpsample.Response.LoginResponse;
import com.sample.mvpsample.base.BaseMVPPresenter;
import com.sample.mvpsample.base.BasePresenter;
import com.sample.mvpsample.base.BaseView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LoginPresenter extends BasePresenter<LoginMvpView> {

    private Datamanager datamanager;
    private Context context;

     LoginMvpView mvpView;


  LoginPresenter(LoginMvpView mvpView){
        this.mvpView = mvpView;
  }


    void LoginFirebaseAPi(String email, String password){
        datamanager = new Datamanager();

        Call<LoginResponse> call = datamanager.loginManager(email,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code()==200)
                {
                    mvpView.loginSuccess(response.body());
                    mvpView.loginResponseCode(response.code());
                }
                else
                {
                    mvpView.loginResponseCode(response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mvpView.loginFailure(t);
            }
        });

    }


}
