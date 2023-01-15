package com.example.anli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import adapter.MyListViewAdapter;

public class FriendCircleActivity extends AppCompatActivity {
    private ListView listView;
    private MyListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_circle);
        listView = findViewById(R.id.list_view);
        adapter =new MyListViewAdapter(this);
        listView.setAdapter(adapter);
    }
}