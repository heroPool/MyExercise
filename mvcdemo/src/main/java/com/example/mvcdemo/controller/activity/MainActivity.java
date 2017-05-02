package com.example.mvcdemo.controller.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mvcdemo.R;
import com.example.mvcdemo.controller.fragment.DownLoadAvatar;
import com.example.mvcdemo.controller.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.btn_login:

                fragmentTransaction.replace(R.id.layoutContact, new LoginFragment()).commit();
                break;
            case R.id.btndownloadAvatar:
                fragmentTransaction.replace(R.id.layoutContact, new DownLoadAvatar()).commit();
                break;
            case R.id.btnContact:

                break;
        }
    }
}
