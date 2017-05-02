package com.example.administrator.exercise;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressbar;
    static final int DOWNLOAD_START = 1;

    Handler mWorkHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        createWorkHandler();


    }

    private void createWorkHandler() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                mWorkHandler = new Handler() {
                    int i;

                    @Override
                    public void handleMessage(Message msg) {

                        switch (msg.what) {
                            case DOWNLOAD_START:
                                for (i = 1; i <= 100; i++) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressbar.setProgress(i);

                                        }
                                    });
                                    SystemClock.sleep(30);

                                }
                                break;
                        }

                    }
                };
                Looper.loop();
            }
        }).start();
    }


    public void onClick(View view) {
        mWorkHandler.sendEmptyMessage(DOWNLOAD_START);
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

                //断开时
            }
        }, BIND_AUTO_CREATE);
    }
}
