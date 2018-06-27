package com.sample.mvpsample.Login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sample.mvpsample.R;
import com.sample.mvpsample.Response.LoginResponse;
import com.sample.mvpsample.SampleApplication;

import javax.inject.Inject;

/**
 * Created by akhil on 10/4/18.
 */

public class LoginFragment extends Fragment implements View.OnClickListener,LoginMvpView {

    TextInputEditText etEmail,etPassword;
    LoginPresenter loginPresenter;

    @Inject LoginPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragmentlogin,container,false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
       setupToolbar(toolbar);
        Button login = view.findViewById(R.id.btnLogin);
        login.setOnClickListener(this);

        etEmail =view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
       // loginPresenter = new LoginPresenter(this);
        presenter.attach(this);
        SampleApplication app =   (SampleApplication) getActivity().getApplication();
        app.getLoginComponent(this).inject(this);
        Log.i("presenter-withDI",""+presenter);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    private void setupToolbar(Toolbar toolbar){
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Login");
    }

    @SuppressLint("NewApi")
    public void validateLogin(){
        if(TextUtils.isEmpty(etEmail.getText().toString().trim()))
        {
            Toast.makeText(getActivity(),"please enter email",Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString().trim()).matches()){
            Toast.makeText(getActivity(),"please enter Valid email",Toast.LENGTH_SHORT).show();

        }

        else if(TextUtils.isEmpty(etPassword.getText().toString().trim()))
        {
            Toast.makeText(getActivity(),"please enter Password",Toast.LENGTH_SHORT).show();

        }

        else {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            presenter.LoginFirebaseAPi(email,password);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnLogin)
        validateLogin();
    }



    @Override
    public void loginSuccess(LoginResponse response) {
        Toast.makeText(getActivity(),"login success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailure(Throwable throwable) {
        Toast.makeText(getActivity(),"login failure",Toast.LENGTH_SHORT).show();
        Log.i("message",""+throwable.getMessage());
    }

    @Override
    public void loginResponseCode(int code) {
        Log.i("response",""+code);
        if(code==400)
        {
            Toast.makeText(getActivity(),"login failure",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }


}
