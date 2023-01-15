package com.example.anli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.Random;

import view.DrawCircle;

/**
 * author: created by song
 * description: 模拟实现手机校正功能。
 */
public class CatchMeActivity extends AppCompatActivity {
    private int width, height;//屏幕的宽高
    private DrawCircle drawCircle;
    private RelativeLayout layout;  //加载DrawCircle视图类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_me);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //获取桌面服务
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        width = windowManager.getDefaultDisplay().getWidth();
        height = windowManager.getDefaultDisplay().getHeight();
        layout = findViewById(R.id.activity_catch_me);
        drawCircle = new DrawCircle(this);
        //设置DrawCircle视图控件的最小宽高
        drawCircle.setMinimumHeight(height);
        drawCircle.setMinimumWidth(width);
        drawCircle.invalidate();//刷新视图
        layout.addView(drawCircle); //添加自定义画圆视图到布局
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //当手指点下去
        if (MotionEvent.ACTION_DOWN == event.getAction()){
            float x = event.getX();
            float y = event.getY();
            //如果x, y 位于之前圆的范围内，则
            //随机生成一个除圆心范围以外的坐标
            if (drawCircle.getOldValue() [0] +80 > x && x > drawCircle.getOldValue()[0] - 80
                && drawCircle.getOldValue()[1] +80 > y && y > drawCircle.getOldValue()[1] - 80){
                Random random = new Random();
                int sendX, sendY;
                do{
                    sendX = random.nextInt(width-80);
                    sendY = random.nextInt(height-80);
                }while (sendX < 80 && sendY < 200 && drawCircle.getOldValue()[1] + 80 >sendY && drawCircle.getOldValue()[0] +80 >sendX);
                drawCircle.setXY(sendX,sendY);
            }
        }
        return super.onTouchEvent(event);
    }
}