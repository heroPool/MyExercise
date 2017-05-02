package com.example.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.startService)
    Button startService;
    @BindView(R.id.stopservice)
    Button stopservice;
    @BindView(R.id.bindservice)
    Button bindservice;
    @BindView(R.id.unbindService)
    Button unbindService;

    MyService.MyBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private static final String TAG = "MainActivity";
    ServiceConnection connect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.MyBinder) service;
            Log.e(TAG, "service:" + service.toString() + "ComponentName:" + name.toString());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //断开与service的连接时，APP崩溃时
        }
    };

    @OnClick({R.id.startService, R.id.stopservice, R.id.bindservice, R.id.unbindService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.startService:
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.stopservice:
                stopService(new Intent(this, MyService.class));

                break;
            case R.id.bindservice:

                bindService(new Intent(this, MyService.class), connect, BIND_AUTO_CREATE);
//                Log.e(TAG, "count" + binder.getCount());
                break;
            case R.id.unbindService:
                unbindService(connect);
                break;
        }
    }
}
