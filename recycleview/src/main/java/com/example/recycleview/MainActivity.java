package com.example.recycleview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycleview.bean.AppBean;
import com.example.recycleview.dao.AppDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<AppBean> appList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myAdapter = new MyAdapter(this, appList);
        recyclerView.setAdapter(myAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);//线性布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);//4列网格布局
        //瀑布流布局
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);

    }

    private void initData() {
        appList = AppDao.getData(this);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.ivAppIcon);
            textView = (TextView) itemView.findViewById(R.id.tvName);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        Context context;
        ArrayList<AppBean> Applist;

        public MyAdapter(Context context, ArrayList<AppBean> applist) {
            this.context = context;
            Applist = applist;
        }

        @Override

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//            LayoutInflater from = LayoutInflater.from(context);
//            View inflate1 = from.inflate(R.layout.item_app, null);

            View inflate = View.inflate(context, R.layout.item_app, null);
            MyViewHolder viewHolder = new MyViewHolder(inflate);


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            AppBean appBean = Applist.get(position);
            holder.imageView.setImageResource(appBean.getPhotoId());
            holder.textView.setText(appBean.getName());

        }

        @Override
        public int getItemCount() {
            return Applist.size();
        }
    }


}
