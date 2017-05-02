package com.example.mvcdemo.controller.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvcdemo.R;
import com.example.mvcdemo.bean.UserBean;
import com.example.mvcdemo.model.net.IModelLogin;
import com.example.mvcdemo.model.net.ModelLogin;
import com.example.mvcdemo.model.utils.OkUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    IModelLogin iModelLogin;
    EditText metusername,metPassword;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflate = inflater.inflate(R.layout.fragment_login, container, false);
        initView(inflate);
        iModelLogin = new ModelLogin();
        setListener(inflate);
        return inflate;
    }

    private void setListener(View inflate) {
        inflate.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = metusername.getText().toString();
                String password = metPassword.getText().toString();
                
                iModelLogin.login(getActivity(), username, password, new OkUtils.OnCompleteListener<UserBean>() {
                    @Override
                    public void onSuccess(UserBean result) {
                        Toast.makeText(getActivity(),"登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initView(View inflate) {
        metPassword = (EditText) inflate.findViewById(R.id.etPassword);
        metusername = (EditText) inflate.findViewById(R.id.etPassword);
        
        
    }

    
}
