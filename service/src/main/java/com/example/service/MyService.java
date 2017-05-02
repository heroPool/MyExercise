package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        MyBinder myBinder = new MyBinder();
        Log.e(TAG, "myBinder" + myBinder.toString());
        return myBinder;
    }

    @Override
    public void onCreate() {
        //服务创建时调用
        super.onCreate();
        Log.e(TAG, "onCtreate()执行");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //服务被启动调用
        Log.e(TAG, "onStartCommand()执行");
        Intent intent1 = new Intent(this, Main2Activity.class);
        startActivity(intent1);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //服务被销毁时创建
        super.onDestroy();
        Log.e(TAG, "onDestroy()执行");
    }

    private int count = 1;

    public class MyBinder extends Binder {
        public int getCount() {
            return count++;
        }
    }
}
