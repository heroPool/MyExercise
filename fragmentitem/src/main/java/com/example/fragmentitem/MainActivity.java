package com.example.fragmentitem;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fragmentitem.fragment.fenleiItem;
import com.example.fragmentitem.fragment.gouwucheItem;
import com.example.fragmentitem.fragment.lianxirenItem;
import com.example.fragmentitem.fragment.xinpinItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager viewPager;

    Fragment[] mfragments;

    TextView textviewXinpin, textViewJingxuan, textViewFenlei, textViewgoGouwuche, textViewLianxiren;
    ViewpagerAdapter viewpagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initView();


        setListener();
    }

    private void setListener() {
        textviewXinpin.setOnClickListener(this);
        textViewLianxiren.setOnClickListener(this);
        textViewgoGouwuche.setOnClickListener(this);
        textViewFenlei.setOnClickListener(this);
        textViewJingxuan.setOnClickListener(this);
        viewPagerListener();
    }

    private void viewPagerListener() {


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initDrawables();
                switch (position) {
                    case 0:
                        setDrawable(textviewXinpin, R.drawable.menu_item_new_goods_selected, Color.parseColor("#ff66ff"));
                        break;
                    case 1:
                        setDrawable(textViewJingxuan, R.drawable.menu_item_boutique_selected, Color.parseColor("#ff66ff"));
                        break;
                    case 2:
                        setDrawable(textViewFenlei, R.drawable.menu_item_category_selected, Color.parseColor("#ff66ff"));
                        break;
                    case 3:
                        setDrawable(textViewgoGouwuche, R.drawable.menu_item_cart_selected, Color.parseColor("#ff66ff"));
                        break;
                    case 4:
                        setDrawable(textViewLianxiren, R.drawable.menu_item_contact_selected, Color.parseColor("#ff66ff"));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initDrawables() {
        setDrawable(textviewXinpin, R.drawable.menu_item_new_goods_normal, Color.BLACK);
        setDrawable(textViewJingxuan, R.drawable.menu_item_boutique_normal, Color.BLACK);
        setDrawable(textViewFenlei, R.drawable.menu_item_category_normal, Color.BLACK);
        setDrawable(textViewgoGouwuche, R.drawable.menu_item_cart_normal, Color.BLACK);
        setDrawable(textViewLianxiren, R.drawable.menu_item_contact_normal, Color.BLACK);
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), mfragments);
        viewPager.setAdapter(viewpagerAdapter);

        textviewXinpin = (TextView) findViewById(R.id.textviewXinpin);
        textViewJingxuan = (TextView) findViewById(R.id.textviewJingxuan);
        textViewFenlei = (TextView) findViewById(R.id.textviewFenlei);
        textViewgoGouwuche = (TextView) findViewById(R.id.textviewGouwuche);
        textViewLianxiren = (TextView) findViewById(R.id.textviewLianxiren);

    }

    private void initFragment() {
        mfragments = new Fragment[5];
        mfragments[0] = new xinpinItem();
        mfragments[1] = new xinpinItem();
        mfragments[2] = new fenleiItem();
        mfragments[3] = new gouwucheItem();
        mfragments[4] = new lianxirenItem();
    }

    @Override
    public void onClick(View v) {
        initDrawables();
        switch (v.getId()) {

            case R.id.textviewXinpin:
                setDrawable(textviewXinpin, R.drawable.menu_item_new_goods_selected, Color.parseColor("#ff66ff"));
                viewPager.setCurrentItem(0);
                break;
            case R.id.textviewJingxuan:
                setDrawable(textViewJingxuan, R.drawable.menu_item_boutique_selected, Color.parseColor("#ff66ff"));
                viewPager.setCurrentItem(1);
                break;
            case R.id.textviewFenlei:
                setDrawable(textViewFenlei, R.drawable.menu_item_category_selected, Color.parseColor("#ff66ff"));
                viewPager.setCurrentItem(2);
                break;
            case R.id.textviewGouwuche:
                setDrawable(textViewgoGouwuche, R.drawable.menu_item_cart_selected, Color.parseColor("#ff66ff"));
                viewPager.setCurrentItem(3);
                break;
            case R.id.textviewLianxiren:
                setDrawable(textViewLianxiren, R.drawable.menu_item_contact_selected, Color.parseColor("#ff66ff"));
                viewPager.setCurrentItem(4);
                break;
        }
    }

    class ViewpagerAdapter extends FragmentPagerAdapter {
        Fragment[] fragments;

        public ViewpagerAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        public ViewpagerAdapter(FragmentManager fm) {
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
        Drawable drawable = ContextCompat.getDrawable(this, drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }

}
