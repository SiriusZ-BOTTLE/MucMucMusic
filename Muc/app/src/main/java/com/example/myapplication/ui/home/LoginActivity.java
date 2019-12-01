package com.example.myapplication.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.bean.User;


import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private List<User> user=new ArrayList<>();
    private EditText UserId,Password;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserId=findViewById(R.id.userid);
        Password=findViewById(R.id.password);
        Button btnl = findViewById(R.id.login);
        sp = getSharedPreferences("test",Context.MODE_PRIVATE);
        editor =  sp.edit();
        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid = UserId.getText().toString();
                String password = Password.getText().toString();
                if (userid.equals("123456") && password.equals("lqz123")) {
                    Intent it = new Intent();
                    it.setClass(LoginActivity.this, MainActivity.class);
                    editor.putString("name",UserId.getText().toString());
                    editor.putString("password",Password.getText().toString());
                    editor.putBoolean("flag",true);
                    editor.commit();
                    startActivity(it);
                } else {
                    Toast.makeText(LoginActivity.this, "用户名密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btn2 = findViewById(R.id.sign);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(LoginActivity.this, SignActivity.class);
                startActivity(it);
            }
        });
    }
}