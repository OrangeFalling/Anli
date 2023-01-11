package com.example.anli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewClick();
    }

    //初始化单击监听事件
    protected void initViewClick(){
        findViewById(R.id.AnLi_1).setOnClickListener(this);
        findViewById(R.id.AnLi_2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.AnLi_1:
                startActivity(new Intent(this,DrawerActivity.class));
                break;
            case R.id.AnLi_2:
                startActivity(new Intent(this,qqMenuActivity.class));
                break;
        }
    }
}