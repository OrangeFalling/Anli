package com.example.anli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * author: created by song
 * description: 使用SharedPreference轻量级数据库，实现保存用户信息功能。
 */
public class SharedPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
    }
}