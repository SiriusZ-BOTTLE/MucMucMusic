package com.example.myapplication.ui.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.fastjson.JSON;
import com.example.myapplication.R;
import com.example.myapplication.Util.HttpUtil;
import com.example.myapplication.bean_new.User;

public class ModifyActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private EditText nickname;
    private EditText idiograph;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Toolbar toolbar = findViewById(R.id.tool_bar_modify);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sp = getSharedPreferences("test", Context.MODE_PRIVATE);
        nickname = (EditText) findViewById(R.id.usernickname);
        idiograph = (EditText) findViewById(R.id.useridiograph);
        nickname.setText(sp.getString("nickname",""));
        idiograph.setText(sp.getString("idiograph",""));

        Button modify = (Button) findViewById(R.id.modify);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ThreadModify thread1 = new ThreadModify();
                    thread1.start();
                    thread1.join();
                }catch (Exception e){

                }
                Intent intent = new Intent(ModifyActivity.this,AccountFragment.class);
                startActivity(intent);
            }
        });
        Button cancel = (Button) findViewById(R.id.modify_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("ModifyUser", "handleMessage: " );
                    break;
                default:
                    break;
            }
        }
    };
    private class  ThreadModify extends Thread {
        @Override
        public void run() {
            sp = getSharedPreferences("test",Context.MODE_PRIVATE);
            editor =  sp.edit();
            User user=new User();
            String name = nickname.getText().toString();
            String graph = idiograph.getText().toString();
            user.setId_User(sp.getString("name",""));
            user.setNickname_User(name);
            user.setIdiograph_User(graph);
            editor.putString("nickname",name);
            editor.putString("idiograph",graph);
            editor.commit();
            String body= JSON.toJSONString(user);
            HttpUtil.sendPostUrl("http://47.97.202.142:8082/user/update",body,"UTF-8");
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }

    }
}
