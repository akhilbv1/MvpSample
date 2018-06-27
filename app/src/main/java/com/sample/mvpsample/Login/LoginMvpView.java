package com.sample.mvpsample.Login;

import com.sample.mvpsample.Response.LoginResponse;
import com.sample.mvpsample.base.BaseView;

/**
 * Created by akhil on 10/4/18.
 */

public  interface LoginMvpView  extends BaseView {

    void loginSuccess(LoginResponse response);

    void loginFailure(Throwable throwable);

    void loginResponseCode(int code);
}
