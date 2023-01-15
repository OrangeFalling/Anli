package com.example.anli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import view.MyDialog;

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
        findViewById(R.id.AnLi_3).setOnClickListener(this);
        findViewById(R.id.AnLi_4).setOnClickListener(this);
        findViewById(R.id.AnLi_5).setOnClickListener(this);
        findViewById(R.id.AnLi_6).setOnClickListener(this);
        findViewById(R.id.AnLi_7).setOnClickListener(this);
        findViewById(R.id.AnLi_8).setOnClickListener(this);
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
            case R.id.AnLi_3:
                startActivity(new Intent(this,PushOutDialogActivity.class));
                break;
            case R.id.AnLi_4:
                startActivity(new Intent(this,NotificationActivity.class));
                break;
            case R.id.AnLi_5:
                OnExitAccount();
                break;
            case R.id.AnLi_6:
                startActivity(new Intent(this,SlideDelteActivity.class));
                break;
            case R.id.AnLi_7:
                startActivity(new Intent(this,FlowLayoutActivity.class));
                break;
            case R.id.AnLi_8:
                startActivity(new Intent(this,KeyTextActivity.class));
                break;
        }
    }

    /**
     * 设置退出账号按钮弹出对话框
     */
    private void OnExitAccount(){
        final MyDialog myDialog = new MyDialog(this);
        myDialog.setOnExitListener(view -> {
            if (myDialog.isShowing()){
                myDialog.dismiss();
                finish();
            }
        });
        myDialog.setOnCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myDialog!=null && myDialog.isShowing()){
                    myDialog.dismiss();
                }
            }
        });
        myDialog.show();
    }
}