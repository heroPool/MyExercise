package com.example.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button btnsendBroadCast;
    TextView textView;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        CartBroadcast cartBroadcast = new CartBroadcast();
        IntentFilter intentFilter = new IntentFilter("addcart");
        registerReceiver(cartBroadcast, intentFilter);
        btnsendBroadCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                sendBroadcast(new Intent("addcart").putExtra("count", count));
            }
        });
    }

    private void initView() {
        btnsendBroadCast = (Button) findViewById(R.id.sendBroadcast);
        textView = (TextView) findViewById(R.id.textView);
    }

    class CartBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int count = intent.getIntExtra("count", 0);
            textView.setText(count + "");
            showNotify();
        }
    }

    private void showNotify() {
        //创建通知构建者
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("正在下载")
//                .setContentText("点击进入详情界面")
                .setSmallIcon(R.mipmap.ic_launcher);
//        Intent intent = new Intent(this, GoodsDetailsActivity.class);
//        PendingIntent pi = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pi);
        final Notification build = builder.build();

        final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    builder.setProgress(100, i, false);
                    manager.notify(1, build);
                    Log.e("main", i + "");
                    SystemClock.sleep(50);
                }
            }
        }).start();

    }
}

