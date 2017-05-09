package com.example.observer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView mListView1,mListView2;
    ArrayList<String> mList;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        String[] array = getResources().getStringArray(R.array.apps);
        List<String> list = Arrays.asList(array);
        mList = new ArrayList<>(list);
    }

    private void initView() {
        mAdapter = new MyAdapter(this, mList);
        mListView1 = (ListView) findViewById(R.id.lv1);
        mListView1.setAdapter(mAdapter);

        mListView2 = (ListView) findViewById(R.id.lv2);
        mListView2.setAdapter(mAdapter);
    }

    class MyAdapter extends BaseAdapter {
        Context context;
        ArrayList<String> list;

        public MyAdapter(Context context, ArrayList<String> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.item_listview, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tv);
            tv.setText(list.get(position));
            return convertView;
        }
    }
}
