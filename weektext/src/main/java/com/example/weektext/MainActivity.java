package com.example.weektext;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.weektext.fragment.fenlei;
import com.example.weektext.fragment.gouwuche;
import com.example.weektext.fragment.jingxuan;
import com.example.weektext.fragment.lianxiren;
import com.example.weektext.fragment.xinpin;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Fragment[] fragmentsArr;
    ViewPager viewPager;
    TextView textXinpin, textJingpin, textFenlei, textGouwuche, textLianxiren;

    viewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initView();

        setListener();


    }

    private void setListener() {
        textXinpin.setOnClickListener(this);
        textJingpin.setOnClickListener(this);
        textFenlei.setOnClickListener(this);
        textGouwuche.setOnClickListener(this);
        textLianxiren.setOnClickListener(this);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initContact();
                switch (position) {
                    case 0:
                        setDrawable(textXinpin, R.drawable.menu_item_new_goods_normal, Color.parseColor("#ff66ff"));

                        break;
                    case 1:
                        setDrawable(textJingpin, R.drawable.menu_item_collect_selected, Color.parseColor("#ff66ff"));

                        break;
                    case 2:
                        setDrawable(textFenlei, R.drawable.menu_item_category_selected, Color.parseColor("#ff66ff"));

                        break;
                    case 3:
                        setDrawable(textGouwuche, R.drawable.menu_item_cart_selected, Color.parseColor("#ff66ff"));

                        break;
                    case 4:
                        setDrawable(textLianxiren, R.drawable.menu_item_contact_selected, Color.parseColor("#ff66ff"));

                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFragment() {
        fragmentsArr = new Fragment[5];
        fragmentsArr[0] = new xinpin();
        fragmentsArr[1] = new jingxuan();
        fragmentsArr[2] = new fenlei();
        fragmentsArr[3] = new gouwuche();
        fragmentsArr[4] = new lianxiren();

    }

    private void initView() {
        viewPagerAdapter = new viewPagerAdapter(getSupportFragmentManager(), fragmentsArr);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerAdapter);

        textXinpin = (TextView) findViewById(R.id.textXinpin);
        textJingpin = (TextView) findViewById(R.id.textJingpin);
        textFenlei = (TextView) findViewById(R.id.textFenlei);
        textGouwuche = (TextView) findViewById(R.id.textGoueuche);
        textLianxiren = (TextView) findViewById(R.id.textLianxiren);

    }

    @Override
    public void onClick(View v) {

        initContact();
        switch (v.getId()) {
            case R.id.textXinpin:

                viewPager.setCurrentItem(0);
                setDrawable(textXinpin, R.drawable.menu_item_new_goods_normal, Color.parseColor("#ff66ff"));

                break;
            case R.id.textJingpin:
                viewPager.setCurrentItem(1);
                setDrawable(textJingpin, R.drawable.menu_item_collect_selected, Color.parseColor("#ff66ff"));

                break;
            case R.id.textFenlei:
                viewPager.setCurrentItem(2);
                setDrawable(textFenlei, R.drawable.menu_item_category_selected, Color.parseColor("#ff66ff"));

                break;

            case R.id.textGoueuche:
                viewPager.setCurrentItem(3);
                setDrawable(textGouwuche, R.drawable.menu_item_cart_selected, Color.parseColor("#ff66ff"));

                break;
            case R.id.textLianxiren:
                viewPager.setCurrentItem(4);
                setDrawable(textLianxiren, R.drawable.menu_item_contact_selected, Color.parseColor("#ff66ff"));

                break;
        }
    }

    private void initContact() {
        setDrawable(textXinpin, R.drawable.menu_item_new_goods_normal, Color.parseColor("#969595"));
        setDrawable(textJingpin, R.drawable.menu_item_collect_normal, Color.parseColor("#969595"));
        setDrawable(textFenlei, R.drawable.menu_item_category_normal, Color.parseColor("#969595"));
        setDrawable(textGouwuche, R.drawable.menu_item_cart_normal, Color.parseColor("#969595"));
        setDrawable(textLianxiren, R.drawable.menu_item_contact_normal, Color.parseColor("#969595"));
    }

    class viewPagerAdapter extends FragmentPagerAdapter {

        Fragment[] fragments;

        public viewPagerAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        public viewPagerAdapter(FragmentManager fm) {
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

    public void setDrawable(TextView textView, int drawableId, int textColor) {
        textView.setTextColor(textColor);
        Drawable drawable = ContextCompat.getDrawable(this, drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }

}


