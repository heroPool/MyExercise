package com.example.fragmentitem;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fragmentitem.fragment.fenleiItem;
import com.example.fragmentitem.fragment.gouwucheItem;
import com.example.fragmentitem.fragment.jingpinItem;
import com.example.fragmentitem.fragment.lianxirenItem;
import com.example.fragmentitem.fragment.xinpinItem;

import java.util.HashMap;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    Fragment[] fragmentsArr = new Fragment[5];
    TextView[] textViewsArr = new TextView[5];


    int[] selectedId = new int[]{R.drawable.menu_item_new_goods_selected, R.drawable.menu_item_boutique_selected,
            R.drawable.menu_item_category_selected, R.drawable.menu_item_category_selected,
            R.drawable.menu_item_cart_selected, R.drawable.menu_item_contact_selected};
    int[] nomalId = new int[]{
            R.drawable.menu_item_new_goods_normal, R.drawable.menu_item_boutique_normal,
            R.drawable.menu_item_category_normal,
            R.drawable.menu_item_cart_normal, R.drawable.menu_item_contact_normal
    };
    int[] textviewId = new int[]{R.id.textviewXinpin, R.id.textviewJingxuan, R.id.textviewFenlei, R.id.textviewGouwuche, R.id.textviewLianxiren};
    HashMap<Integer, Integer> Id = new HashMap<Integer, Integer>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFragments();
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentsArr);
        viewPager.setAdapter(viewPagerAdapter);

        for(int i=0;i<textViewsArr.length;i++) {
            textViewsArr[i] = (TextView) findViewById(textviewId[i]);
            Id.put(textviewId[i], i);
        }
    }

    private void initFragments() {
        fragmentsArr[0] = new xinpinItem();
        fragmentsArr[1] = new jingpinItem();
        fragmentsArr[2] = new fenleiItem();
        fragmentsArr[3] = new gouwucheItem();
        fragmentsArr[4] = new lianxirenItem();
    }

    int mPreId;

    @Override
    public void onClick(View v) {
        Integer integer = Id.get(v.getId());
        viewPager.setCurrentItem(integer);
        setItemState(integer);
    }

    private void setItemState(Integer integer) {
        //将之前被选择的菜单项设置为未选择状态
        setDrawable(textViewsArr[mPreId],nomalId[mPreId], Color.WHITE);
        //将当前被选择的列表项设置为选择状态
        setDrawable(textViewsArr[integer], selectedId[integer], Color.rgb(0xff, 0x66, 0xff));

        mPreId=integer;//记下当前被选择的下标
    }


    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        Fragment[] fragments;

        public ViewPagerAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }


    private void setDrawable(TextView textView, int drawableId, int textColor) {
        textView.setTextColor(textColor);
        //通过图像ID获取图片
        Drawable drawable = ContextCompat.getDrawable(this, drawableId);
        //设置drawable尺寸

        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //设置textView为上图下字
        textView.setCompoundDrawables(null, drawable, null, null);
    }
}
