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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.fastjson.JSON;
import com.example.myapplication.R;
import com.example.myapplication.Util.HttpUtil;
import com.example.myapplication.bean_new.User;

public class PasswordActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private EditText newpassword;
    private EditText newpassword2;
    private EditText oldpassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Toolbar toolbar = findViewById(R.id.tool_bar_password);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sp = getSharedPreferences("test", Context.MODE_PRIVATE);
        oldpassword = (EditText) findViewById(R.id.oldpasswprd);
        newpassword = (EditText) findViewById(R.id.newpassword1);
        newpassword2 = (EditText) findViewById(R.id.newpassword2);


        Button modify = (Button) findViewById(R.id.password_modify);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!oldpassword.getText().toString().equals(sp.getString("password",""))){
                    Toast.makeText(PasswordActivity.this,"原密码输入错误",Toast.LENGTH_SHORT).show();
                    oldpassword.setText("");
                    newpassword.setText("");
                    newpassword2.setText("");
                }else if(newpassword.getText().toString().length()<8 || newpassword2.getText().toString().length()<8){
                    Toast.makeText(PasswordActivity.this,"密码长度不足",Toast.LENGTH_SHORT).show();
                    oldpassword.setText("");
                    newpassword.setText("");
                    newpassword2.setText("");
                } else if(!newpassword.getText().toString().equals(newpassword2.getText().toString())){
                    Toast.makeText(PasswordActivity.this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                    oldpassword.setText("");
                    newpassword.setText("");
                    newpassword2.setText("");
                }else{
                    try{
                        ThreadPassword thread1 = new ThreadPassword();
                        thread1.start();
                        thread1.join();
                    }catch (Exception e){
                    }
                    Toast.makeText(PasswordActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PasswordActivity.this,AccountFragment.class);
                    startActivity(intent);
                }
            }
        });
        Button cancel = (Button) findViewById(R.id.password_cancel);
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
                    Log.d("ModifyPassword", "handleMessage: " );
                    break;
                default:
                    break;
            }
        }
    };
    private class  ThreadPassword extends Thread {
        @Override
        public void run() {
            sp = getSharedPreferences("test",Context.MODE_PRIVATE);
            User user=new User();
            user.setId_User(sp.getString("name",""));
            user.setPassword_User(newpassword.getText().toString());
            String body= JSON.toJSONString(user);
            HttpUtil.sendPostUrl("http://47.97.202.142:8082/user/update",body,"UTF-8");
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    }
}
