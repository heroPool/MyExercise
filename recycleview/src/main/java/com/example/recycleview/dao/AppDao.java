package com.example.recycleview.dao;

import android.content.Context;
import android.content.res.Resources;

import com.example.recycleview.R;
import com.example.recycleview.bean.AppBean;

import java.util.ArrayList;



public class AppDao {
    public static ArrayList<AppBean> getData(Context context) {
        int[] photoIs = {
                R.drawable.tencent_safe, R.drawable.baidu_safe,
                R.drawable.kingsoft_safe, R.drawable.an_doctor,
                R.drawable.ruixing_safe, R.drawable.wangqin_safe,
                R.drawable.lbe_safe, R.drawable.bigspider_safe,
                R.drawable.avg_safe,
                R.drawable.lbe_safe, R.drawable.mobile_an_safe
        };
        Resources res = context.getResources();
        String[] names = res.getStringArray(R.array.names);
        String[] versions = res.getStringArray(R.array.version);
        int[] fileSize = res.getIntArray(R.array.file_size);
        String[] intros = res.getStringArray(R.array.intro);
        ArrayList<AppBean> appList = new ArrayList<>();
        for(int i=0;i<names.length;i++) {
            AppBean app = new AppBean(names[i], versions[i], photoIs[i], fileSize[i], intros[i]);
            appList.add(app);
        }
        return appList;
    }
}
