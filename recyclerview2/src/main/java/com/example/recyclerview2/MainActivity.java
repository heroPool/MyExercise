package com.example.recyclerview2;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerview2.bean.AppBean;
import com.example.recyclerview2.dao.AppDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<AppBean> appList;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDate();
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter(this, appList);
        recyclerView.setAdapter(myAdapter);

    }

    private void initDate() {
        appList = AppDao.getData(this);
    }

    class FooterHolder extends RecyclerView.ViewHolder {
        TextView textviewtFooter;

        public FooterHolder(View itemView) {
            super(itemView);
            textviewtFooter = (TextView) itemView.findViewById(R.id.textviewFooter);
        }
    }

    class AppItemHolder extends RecyclerView.ViewHolder {
        ImageView imageViewAppicon, imageViewAppDelete, imageViewAppUpdate;
        TextView textViewAppname, textViewAppversion, textViewAppSizee;

        public AppItemHolder(View itemView) {
            super(itemView);
            imageViewAppicon = (ImageView) itemView.findViewById(R.id.imageviewIcon);
            imageViewAppDelete = (ImageView) itemView.findViewById(R.id.imageviewDelete);
            imageViewAppUpdate = (ImageView) itemView.findViewById(R.id.imageviewUpdate);

            textViewAppname = (TextView) itemView.findViewById(R.id.textviewAppname);
            textViewAppSizee = (TextView) itemView.findViewById(R.id.textviewAppsize);
            textViewAppversion = (TextView) itemView.findViewById(R.id.textviewAppversion);


        }

    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        static final int TYPE_FOOTER = 1;
        static final int TYPE_APPITEM = 2;
        Context context;
        ArrayList<AppBean> appList;

        public MyAdapter(Context context, ArrayList<AppBean> appList) {
            this.context = context;
            this.appList = appList;
        }

        @Override

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_APPITEM) {
                View inflate = View.inflate(context, R.layout.item_app, null);
                AppItemHolder appItemHolder = new AppItemHolder(inflate);
                return appItemHolder;
            } else if (viewType == TYPE_FOOTER) {
                View inflate = View.inflate(context, R.layout.item_footer, null);
                FooterHolder footerHolder = new FooterHolder(inflate);
                return footerHolder;

            }

            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == TYPE_APPITEM) {
                AppItemHolder itemHolder = (AppItemHolder) holder;
                AppBean appBean = appList.get(position);
                itemHolder.textViewAppversion.setText(appBean.getVersion());
                itemHolder.textViewAppname.setText(appBean.getName());
                itemHolder.textViewAppSizee.setText(appBean.getFileSize() + "k");
                itemHolder.imageViewAppicon.setImageResource(appBean.getPhotoId());

            } else {
                FooterHolder footerHolder = (FooterHolder) holder;
                footerHolder.textviewtFooter.setVisibility(View.VISIBLE);
            }


        }

        @Override
        public int getItemCount() {
            return appList.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1) {
                return TYPE_FOOTER;
            }
            return TYPE_APPITEM;

        }
    }
}
