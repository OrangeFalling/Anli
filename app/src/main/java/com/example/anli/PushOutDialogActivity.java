package com.example.anli;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class PushOutDialogActivity extends AppCompatActivity {
    private Button btn_confirm,btn_cancel;   //定义对话框按钮
    private AlertDialog dlg;                 //定义对话框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_out_dialog);
    }

    /**
     * 单击手机返回按钮，启动对话框
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断如果单击了返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK){
            //创建对话框
            dlg = new AlertDialog.Builder(this).create();
            dlg.show();              //显示对话框
            Window window = dlg.getWindow();       //获取对话框窗口
            window.setGravity(Gravity.CENTER);
            window.setWindowAnimations(R.style.myStyleDialog); //设置对话框样式
            window.setContentView(R.layout.dialog_push_out_layout); //设置对话框布局
            btn_confirm = window.findViewById(R.id.btn_confirm); //获取确认按钮
            btn_cancel = window.findViewById(R.id.btn_cancel);   //获取取消按钮
            initEvent();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 处理对话框中的按钮事件
     */
    private void initEvent() {
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();   //设置对话框消失
                finish();        //关闭当前界面
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();   //设置对话框消失
            }
        });
    }
}