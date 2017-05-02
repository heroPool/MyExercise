package com.example.listview03;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView mlvApp;
    ArrayList<AppBean> mAppList;
    AppAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        setListener();
    }

    private void setListener() {
        setItemClickListener();
        setItemLongClickListener();
    }

    private void setItemLongClickListener() {
        mlvApp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "列表项被长按", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void setItemClickListener() {
        mlvApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppBean app = (AppBean) parent.getItemAtPosition(position);
                Log.i("main", app.toString());
            }
        });
    }

    /*
    <item>百度手机卫士</item>
            <item>金山毒霸</item>
            <item>安医生</item>
            <item>瑞星手机安全软件</item>
            <item>网秦安全</item>
            <item>防盗卫士</item>
            <item>大蜘蛛反病毒</item>
            <item>AVG</item>
            <item>LBE安全大师</item>
            <item>摩安卫士</item>
     */
    private void initData() {
        mAppList = new ArrayList<>();
        int[] picId={
                R.drawable.tencent_safe, R.drawable.baidu_safe,R.drawable.kingsoft_safe,
                R.drawable.an_doctor,R.drawable.ruixing_safe,R.drawable.wangqin_safe,
                R.drawable.lost_safe,R.drawable.bigspider_safe,R.drawable.avg_safe,
                R.drawable.lbe_safe,R.drawable.mobile_an_safe
        };
        Resources res = getResources();
        String[] names = res.getStringArray(R.array.names);
        String[] versions = res.getStringArray(R.array.version);
        int[] fileSizes = res.getIntArray(R.array.file_size);
        for(int i=0;i<names.length;i++) {
            AppBean app=new AppBean(picId[i],names[i],versions[i],fileSizes[i]);
            mAppList.add(app);
        }
    }

    private void initView() {
        mlvApp = (ListView) findViewById(R.id.lvApp);
        mAdapter = new AppAdapter(this, mAppList);
        mlvApp.setAdapter(mAdapter);
    }

    class AppAdapter extends BaseAdapter {
        Context context;
        ArrayList<AppBean> appList;

        public AppAdapter(Context context, ArrayList<AppBean> appList) {
            this.context = context;
            this.appList = appList;
        }

        @Override
        public int getCount() {
            return appList.size();
        }

        @Override
        public AppBean getItem(int position) {
            return appList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //将app_item.xml转换为一个Java对象
            View layout = View.inflate(context, R.layout.app_item, null);
            ImageView ivThumb = (ImageView) layout.findViewById(R.id.ivThumb);
            TextView tvName = (TextView) layout.findViewById(R.id.tvAppName);
            TextView tvVersion = (TextView) layout.findViewById(R.id.tvAppVersion);
            TextView tvFileSize = (TextView) layout.findViewById(R.id.tvAppFileSize);

            AppBean app = getItem(position);
            ivThumb.setImageResource(app.getPicId());
            tvName.setText(app.getName());
            tvVersion.setText(app.getVersion());
            tvFileSize.setText(app.getFileSize()+"kb");
            return layout;
        }
    }

}
