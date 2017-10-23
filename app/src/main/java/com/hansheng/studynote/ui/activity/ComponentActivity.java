package com.hansheng.studynote.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.hansheng.studynote.R;
import com.hansheng.studynote.customview.TestButton;

/**
 * Created by hansheng on 16-9-20.
 */

public class ComponentActivity extends AppCompatActivity {
    //
    private TestButton testButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.btn_layout);
        testButton= (TestButton) findViewById(R.id.testBtn);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个ComponentName对象
                // 方式一
                // ComponentName comp=new
                // ComponentName(MainActivity.this,SecondActivity.class);
                // 方式二：第一个参数为当前应用的包名。第二个参数为跳转组件的类路径
                String arr[]=getResources().getStringArray(R.array.city);
                Log.e("component=",arr[0]);
                ComponentName comp = new ComponentName("com.hansheng.studynote.Activity",
                        "com.hansheng.studynote.Activity.SecondActivity");
                Intent intent = new Intent();
                // 为Intent设置Component属性
                intent.setComponent(comp);
                startActivity(intent);
            }
        });




    }
}
