package com.example.anli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import adapter.MyAdapter;

public class SlideDelteActivity extends AppCompatActivity {
    private RecyclerView lRecyclerView;    //列表控件
    private MyAdapter lMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_delte);
        initView();
        setAdapter();
    }
    //初始化控件
    private void initView(){
        lRecyclerView = findViewById(R.id.rv);

    }
    //设置适配器方法
    private void setAdapter(){
        lRecyclerView.setLayoutManager(new LinearLayoutManager(this));   //设置列表布局管理器
        lRecyclerView.setAdapter(lMyAdapter = new MyAdapter(this));   //设置适配器
        lRecyclerView.setItemAnimator(new DefaultItemAnimator());  //设置列表中子项的动画
    }
}