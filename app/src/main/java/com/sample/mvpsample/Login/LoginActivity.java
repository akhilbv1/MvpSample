package com.sample.mvpsample.Login;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sample.mvpsample.Response.LoginResponse;
import com.sample.mvpsample.R;
import com.sample.mvpsample.SampleApplication;

import java.security.Permission;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        /*Button checkPerm = findViewById(R.id.btnPermission);
        checkPerm.setOnClickListener(this);*/
      // loadLoginFragment();

    }

    private void loadLoginFragment(){
        LoginFragment fragment = new LoginFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
           transaction.add(R.id.container,fragment);
           transaction.commit();
    }


    @Override
    public void onClick(View v) {

        checkForPermissions();
        Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void checkForPermissions(){
        if(ActivityCompat
                .checkSelfPermission
                        (LoginActivity.this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


               if(ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                       Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                   AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                                builder.setTitle("Request Permission")
                                                        .setMessage("This App needs Storage permission")
                                                        .setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                        dialog.cancel();
                                                                        ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},EXTERNAL_STORAGE_PERMISSION_CONSTANT);

                                                            }
                                                        })
                                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.cancel();
                                                            }
                                                        });
                                                builder.show();

               }

               else {
                   ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},EXTERNAL_STORAGE_PERMISSION_CONSTANT);
               }
        }
        else {
            Toast.makeText(this,"Granted",Toast.LENGTH_SHORT).show();
        }
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == EXTERNAL_STORAGE_PERMISSION_CONSTANT) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(LoginActivity.this, "WE HAVE GOT YOU!!", Toast.LENGTH_SHORT).show();
            } else {

                if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Request Permission")
                            .setMessage("This App needs Storage permission")
                            .setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);

                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    builder.show();

                }
            }
        }
    }
}
