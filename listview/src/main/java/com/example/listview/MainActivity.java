package com.example.listview;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.listview.bean.AppBean;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView mlvApp;
    ArrayList mAppList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {


    }

    private void initData() {
        mAppList = new ArrayList<>();
        int[] picId = {
                R.drawable.tencent_safe, R.drawable.baidu_safe, R.drawable.kingsoft_safe,
                R.drawable.an_doctor, R.drawable.ruixing_safe, R.drawable.wangqin_safe,
                R.drawable.lost_safe, R.drawable.bigspider_safe, R.drawable.avg_safe,
                R.drawable.lbe_safe, R.drawable.mobile_an_safe
        };
        Resources res = getResources();
        String[] names = res.getStringArray(R.array.names);
        String[] versions = res.getStringArray(R.array.version);
        int[] fileSizes = res.getIntArray(R.array.file_size);
        for (int i = 0; i < names.length; i++) {
            AppBean app = new AppBean(picId[i], names[i], versions[i], fileSizes[i]);
            mAppList.add(app);
        }
    }
    class AppAdapter extends BaseAdapter{

        Context context;
        ArrayList<AppBean> appList;

        public AppAdapter(Context context, ArrayList<AppBean> appList) {
            this.context = context;
            this.appList = appList;
        }

        @Override
        public int getCount() {          //决定listview显示多少行
            return appList.size();
        }

        @Override
        public Object getItem(int position) {       //根据listview所在位置返回view
            return appList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView.inflate(context, R.layout.app_item, null);


            return null;
        }
    }

}
