package com.example.listview02;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.listview02.bean.AppBean;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    MyAdapter myAdapter;
    ArrayList<AppBean> appBeanArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        setListener();
    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppBean appBean = (AppBean) parent.getItemAtPosition(position);
                Log.i("main", appBean.getName());

            }
        });
        
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        myAdapter = new MyAdapter(this, appBeanArrayList);
        listView.setAdapter(myAdapter);


    }

    private void initData() {
        appBeanArrayList = new ArrayList<>();
        int[] picId={
                R.drawable.tencent_safe, R.drawable.baidu_safe,R.drawable.kingsoft_safe,
                R.drawable.an_doctor,R.drawable.ruixing_safe,R.drawable.wangqin_safe,
                R.drawable.lost_safe,R.drawable.bigspider_safe,R.drawable.avg_safe,
                R.drawable.lbe_safe,R.drawable.mobile_an_safe
        };
        Resources res = getResources();
        String[] names = res.getStringArray(R.array.names);
        Log.i("main", Arrays.toString(names));
        String[] versions = res.getStringArray(R.array.version);
        for(int i=0;i<names.length;i++) {
            AppBean app=new AppBean(picId[i],names[i],versions[i]);
            Log.i("main", app.toString());
            appBeanArrayList.add(app);
        }

    }

    class MyAdapter extends BaseAdapter {
        Context context;
        ArrayList<AppBean> Applist;

        public MyAdapter(Context context, ArrayList<AppBean> applist) {
            this.context = context;
            Applist = applist;
        }


        @Override
        public int getCount() {
            return Applist.size();
        }

        @Override
        public Object getItem(int position) {
            return Applist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder=null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(context, R.layout.item_app, null);

                holder.imageViewIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
                holder.texeviewAppname = (TextView) convertView.findViewById(R.id.tvAppname);
                holder.textviewAppjianjie = (TextView) convertView.findViewById(R.id.tvAppjianjie);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }



            AppBean appBean = (AppBean) getItem(position);
            holder.imageViewIcon.setImageResource(appBean.getPicId());
            holder.texeviewAppname.setText(appBean.getName());
            holder.textviewAppjianjie.setText(appBean.getVersion());

            return convertView;
        }

    }
    class ViewHolder{
        ImageView imageViewIcon;
        TextView texeviewAppname;
        TextView textviewAppjianjie;
    }
}
