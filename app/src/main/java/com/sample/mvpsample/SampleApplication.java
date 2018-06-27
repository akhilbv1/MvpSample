package com.sample.mvpsample;

import android.app.Application;

import com.sample.mvpsample.Login.DaggerLoginComponent;
import com.sample.mvpsample.Login.LoginComponent;
import com.sample.mvpsample.Login.LoginModule;
import com.sample.mvpsample.Login.LoginMvpView;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by akhil on 10/4/18.
 */

public class SampleApplication extends Application {

    private  LoginComponent loginComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }

    public  LoginComponent getLoginComponent(LoginMvpView loginMvpView) {
        loginComponent = DaggerLoginComponent.builder().loginModule(new LoginModule(loginMvpView)).build();
        return loginComponent;
    }


}
