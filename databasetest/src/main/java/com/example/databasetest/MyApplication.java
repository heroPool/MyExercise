package com.example.databasetest;

import android.app.Application;

/**
 * Created by Administrator on 2017/4/16.
 */

public class MyApplication extends Application {
    public static MyApplication instance;

    public static MyApplication getInstance() {

        return new MyApplication();
    }
}
