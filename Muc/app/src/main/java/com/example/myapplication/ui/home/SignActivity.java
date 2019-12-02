package com.example.myapplication.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Util.HttpUtil;
import com.example.myapplication.bean_new.InteractionEntity.ResultEntity;
import com.example.myapplication.bean_new.User;

import org.json.JSONObject;

public class SignActivity extends AppCompatActivity {
    private EditText UserId,Password,PasswordAgain,Nickname,Place;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        UserId=findViewById(R.id.userid);
        Password=findViewById(R.id.password);
//        Button btnl = findViewById(R.id.finish);
//        btnl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(new Runnable(){
//                    @Override
//                    public void run() {
//                        String userid = UserId.getText().toString();
//                        String password = Password.getText().toString();
//                        User user=new User();
//                        user.setId_User(userid);
//                        user.setPassword_User(password);
//                        String body= JSON.toJSONString(user);
//                        String res=new String();
//                        res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/user/login",body,"UTF-8");
//                        ResultEntity result = JSON.parseObject(res, ResultEntity.class);
//                        try{
//                            if(result.getState()==true){
//                                Intent it = new Intent();
//                                it.setClass(LoginActivity.this, MainActivity.class);
//                                editor.putString("name",UserId.getText().toString());
//                                editor.putString("password",Password.getText().toString());
//                                editor.putBoolean("flag",true);
//                                editor.commit();
//                                startActivity(it);
//                                Looper.prepare();
//                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                                Looper.loop();
//                            }
//                            else{
//                                Looper.prepare();
//                                Toast.makeText(LoginActivity.this,result.getState()+" res:"+res, Toast.LENGTH_SHORT).show();
//                                Looper.loop();
//                            }
//                        }catch(Exception e){
//                            Looper.prepare();
//                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                            Looper.loop();
//                        }
//                    }
//                }).start();
//            }
//        });
    }
}
