package com.example.mvcdemo.model.net;

import android.content.Context;

import com.example.mvcdemo.I;
import com.example.mvcdemo.bean.UserBean;
import com.example.mvcdemo.model.utils.OkUtils;

/**
 * Created by Administrator on 2017/3/9.
 */

public class ModelLogin extends ModelBase implements IModelLogin {
    @Override
    public void login(Context context, String userName, String password, OkUtils.OnCompleteListener<UserBean> listener) {
        OkUtils<UserBean> userBeanOkUtils = new OkUtils<>(context);
        userBeanOkUtils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME, userName)
                .addParam(I.User.PASSWORD, password)
                .targetClass(UserBean.class)
                .execute(listener);
    }
}
