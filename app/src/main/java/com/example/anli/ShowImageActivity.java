package com.example.anli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.MyPageAdapter;
import utils.Utils;

public class ShowImageActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> listView;
    private int index = 0;
    private MyPageAdapter myPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        init();
    }

    //初始化
    private void init(){
        final Intent intent = getIntent();
        viewPager = findViewById(R.id.show_view_pager);
        listView = new ArrayList<>();
        for (int i = 0; i < intent.getIntArrayExtra("image").length; i++) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.view_pager_item, null);
            //动态设置图片容器的大小，适应屏幕宽度
            RelativeLayout rl = view.findViewById(R.id.pager_bg);
            ViewGroup.LayoutParams lp = rl.getLayoutParams();
            lp.width = Utils.getScreenWidth(this);
            lp.height = Utils.getScreenWidth(this);
            rl.setLayoutParams(lp);
            TextView tv_page_num = view.findViewById(R.id.view_page_num);
            String tv_num = String.valueOf(index);
            tv_page_num.setText(tv_num);
            ImageView iv = view.findViewById(R.id.view_image);
            iv.setBackgroundResource(intent.getIntArrayExtra("image")[i]);
            listView.add(view);
        }
        myPageAdapter = new MyPageAdapter(listView);
        viewPager.setAdapter(myPageAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                index = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setCurrentItem(intent.getIntExtra("id",0));
    }
}