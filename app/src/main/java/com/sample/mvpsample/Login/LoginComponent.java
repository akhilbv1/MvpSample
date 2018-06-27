package com.sample.mvpsample.Login;

import dagger.Component;

/**
 * Created by akhil on 11/4/18.
 */

@Component(modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

    void inject(LoginFragment fragment);
}
