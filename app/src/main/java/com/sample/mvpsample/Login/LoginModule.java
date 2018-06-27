package com.sample.mvpsample.Login;

import com.sample.mvpsample.SampleApplication;

import dagger.Module;
import dagger.Provides;


@Module
public class LoginModule {

    private LoginMvpView mvpView;

    public LoginModule(LoginMvpView loginMvpView){
        mvpView = loginMvpView;
    }


    @Provides
    LoginPresenter getLoginPresenter() {
        return new LoginPresenter(mvpView);
    }

}
