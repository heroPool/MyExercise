package com.example.mvcdemo.model.net;

import android.content.Context;

import com.example.mvcdemo.bean.UserBean;
import com.example.mvcdemo.model.utils.OkUtils;

/**
 * Created by Administrator on 2017/3/9.
 */

public interface IModelLogin extends IModelBase {
    void login(Context context, String userName, String password, OkUtils.OnCompleteListener<UserBean> listener);
}
