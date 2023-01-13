package com.example.anli;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        //进行系统版本检查，判断是否大于8.0版本，即大于21。
        //设置并建立通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId = "chat";             //渠道ID
            String channelName = "聊天消息";         //渠道名称
            int importance = NotificationManager.IMPORTANCE_HIGH;      //设置渠道重要程度
            //创建聊天消息渠道
            createNotificationChannel(channelId,channelName,importance);

            channelId = "subscribe";              //渠道2ID
            channelName = "订阅消息";               //渠道2名称
            importance =NotificationManager.IMPORTANCE_DEFAULT;   //渠道2重要程度
            //创建订阅消息渠道
            createNotificationChannel(channelId,channelName,importance);
        }
    }

    /**
     * 创建通知渠道
     * 创建通知渠道的代码只会在首次执行时才会创建，之后执行时若发现
     * 该通知渠道已存在，就不会再重复创建，因此不会影响效率。
     * @param channelId
     * @param channelName
     * @param importance
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance){
        NotificationChannel channel = new NotificationChannel(channelId,channelName,importance);
        channel.setShowBadge(false);   //设置不允许这个渠道下的通知显示角标
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * 发送聊天消息通知
     * @param view
     */
    public void sendChatMsg(View view){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //若用户将通知渠道关闭了，提供打开渠道的提示，以及跳转到相应界面。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = manager.getNotificationChannel("chat");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE){
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE,getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID,channel.getId());
                startActivity(intent);
                Toast.makeText(this,"请手动将通知打开",Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent p1 = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new NotificationCompat.Builder(this,"chat")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("收到一条新消息")
                .setContentText("哈哈哈，今天我很开心。")
                .setContentIntent(p1)            //设置通知栏单击跳转
                .build();
        manager.notify(1,notification);
    }

    /**
     * 发送订阅消息通知
     * @param view
     */
    public void sendSubscribeMsg(View view){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //若用户将通知渠道关闭了，提供打开渠道的提示，以及跳转到相应界面。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = manager.getNotificationChannel("subscribe");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE){
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE,getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID,channel.getId());
                startActivity(intent);
                Toast.makeText(this,"请手动将通知打开",Toast.LENGTH_SHORT).show();
            }
        }
        Notification notification = new NotificationCompat.Builder(this,"subscribe")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("收到一条订阅消息")
                .setContentText("地铁三号线正式开通，30万商铺火热抢购中！")
                .build();
        manager.notify(2,notification);
    }

    /**
     * 删除通知渠道
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void deleteChatMsgChannel(View view){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.deleteNotificationChannel("chat");
    }

}