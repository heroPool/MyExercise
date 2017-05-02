package com.example.mvcdemo.model.net;

import com.example.mvcdemo.model.utils.OkUtils;

/**
 * Created by Administrator on 2017/3/9.
 */

public class ModelBase implements IModelBase {
    @Override
    public void release() {
        OkUtils.release();

    }
}
