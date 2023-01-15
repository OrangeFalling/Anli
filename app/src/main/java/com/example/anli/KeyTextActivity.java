package com.example.anli;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.widget.TextView;

import utils.KeyWordUtils;

public class KeyTextActivity extends AppCompatActivity {
    private String text = "你真的懂唯一 的定义\n" +
            "并不简单如呼吸\n" +
            "你真的希望你能厘清\n" +
            "若没交心怎么说明\n" +
            "我真的爱你\n" +
            "句句不轻易\n" +
            "眼神中飘移\n" +
            "总是在关键时刻清楚洞悉\n" +
            "你的不坚定\n" +
            "配合我颠沛流离\n" +
            "死去中清醒\n" +
            "明白你背着我聪明\n" +
            "你真的懂唯一的定义\n" +
            "不只是如影随形\n" +
            "你真的希望你能厘清\n" +
            "闭上眼睛 用心看清\n" +
            "我真的爱你\n" +
            "没人能比拟\n" +
            "眼神没肯定\n" +
            "总是在关键时刻清楚洞悉\n" +
            "你的不坚定\n" +
            "配合我颠沛流离\n" +
            "死去中清醒\n" +
            "明白你背着我聪明\n" +
            "爱本质无异\n" +
            "是因为人多得拥挤\n" +
            "你不想证明 证明我是你唯一\n" +
            "证明我是你唯一";
    private TextView tv;
    private String[] key = {"唯一", "爱你"};  //关键字

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_text);
        SpannableString spString = KeyWordUtils.matcherSearchTitle(Color.RED, text, key);
        tv = findViewById(R.id.tv_key);
        tv.setText(spString);
    }
}