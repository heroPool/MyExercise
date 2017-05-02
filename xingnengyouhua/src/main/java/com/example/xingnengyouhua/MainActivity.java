package com.example.xingnengyouhua;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnviewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnviewStub = (Button) findViewById(R.id.button3);
    }

    public void string(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                long l = System.currentTimeMillis();
                String str = "";
                for (int i = 0; i < 10000; i++) {
                    str = str + 1;
                }
                long l2 = System.currentTimeMillis();
                Log.e("string", "time==" + (l2 - l));
            }
        }, "string").start();


    }

    public void stringbuffer(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                long l = System.currentTimeMillis();
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < 10000; i++) {
                    stringBuffer.append(1);
                }
                long l2 = System.currentTimeMillis();
                Log.e("string", "time==" + (l2 - l));
            }
        }, "stringbuffer").start();

    }

    public void viewStub(View view) {
        findViewById(R.id.viewStub).setVisibility(View.VISIBLE);
        btnviewStub.setEnabled(false);

    }
}
