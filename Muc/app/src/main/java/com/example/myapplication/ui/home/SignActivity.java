package com.example.myapplication.ui.home;

import android.content.Context;
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
    private EditText UserId,Password,PasswordAgain,Nickname,IoGraph;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        UserId=findViewById(R.id.SignInId);
        Password=findViewById(R.id.SignInPwd);
        PasswordAgain=findViewById(R.id.SignInPwdAgain);
        Nickname=findViewById(R.id.SignInNickname);
        IoGraph =findViewById(R.id.SignIniograph);
        Button btnl = findViewById(R.id.finish);
        sp = getSharedPreferences("test", Context.MODE_PRIVATE);
        editor =  sp.edit();
        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try{
                            String userid = UserId.getText().toString();
                            String password = Password.getText().toString();
                            String pwdAgain=PasswordAgain.getText().toString();
                            String nickname=Nickname.getText().toString();
                            String iograph=IoGraph.getText().toString();
//                            if(userid.isEmpty()||password.isEmpty()||pwdAgain.isEmpty()||nickname.isEmpty()){
//                                throw new Exception("输入有空");
//                            }
                            if(password.equals(pwdAgain)==true){
                                User user=new User();
                                user.setId_User(userid);
                                user.setPassword_User(password);
                                user.setNickname_User(nickname);
                                user.setGender_User("M");
                                user.setIdiograph_User(iograph);
                                String body= JSON.toJSONString(user);
                                String res=new String();
                                res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/user/register",body,"UTF-8");
                                ResultEntity result = JSON.parseObject(res, ResultEntity.class);

                                if(result.getState()==true){
                                    Intent it = new Intent(SignActivity.this,LoginActivity.class);
                                    startActivity(it);
                                    finish();
                                    Looper.prepare();
                                    Toast.makeText(SignActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    Looper.loop();

//                                    onBackPressed();

                                }
                                else{
                                    Looper.prepare();
                                    Toast.makeText(SignActivity.this,"res:"+res+" body"+body, Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
//
                            }
                            else{
                                Looper.prepare();
                                Toast.makeText(SignActivity.this, "密码输入不一致", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }

                        }catch(Exception e){
                            Looper.prepare();
                            Toast.makeText(SignActivity.this, "输入不正确"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                    }
                }).start();
            }
        });
    }
}
