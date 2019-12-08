package com.example.myapplication.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Util.HttpUtil;
import com.example.myapplication.Util.MessageBox;
import com.example.myapplication.bean_new.InteractionEntity.ResultEntity;
import com.example.myapplication.bean_new.Song;
import com.example.myapplication.bean_new.User;


import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private List<User> user=new ArrayList<>();
    private EditText UserId,Password;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private ResultEntity result;

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
                new Thread(new Runnable(){
                    @Override
                    public void run() {

                        String userid = UserId.getText().toString();
                        String password = Password.getText().toString();
                        User user=new User();
                        user.setId_User(userid);
                        user.setPassword_User(password);
                        String body= JSON.toJSONString(user);
//                        Looper.prepare();
//                        Toast.makeText(LoginActivity.this,body, Toast.LENGTH_SHORT).show();
//                        Looper.loop();
//                        System.out.println(body);
                        String res=new String();
                        res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/user/login",body,"UTF-8");
                        result = JSON.parseObject(res, ResultEntity.class);

//                        System.out.println(res);
                        try{
                            if(result.getState()==true){
                                Intent it = new Intent();
                                it.setClass(LoginActivity.this, MainActivity.class);
                                editor.putString("name",UserId.getText().toString());
                                editor.putString("password",Password.getText().toString());
                                editor.putString("nickname",((JSONObject)result.getObject()).toJavaObject(User.class).getNickname_User());
                                editor.putString("gender",((JSONObject)result.getObject()).toJavaObject(User.class).getGender_User());
                                editor.putString("level",((JSONObject)result.getObject()).toJavaObject(User.class).getLevel_User());
                                editor.putString("state",((JSONObject)(result.getObject())).toJavaObject(User.class).getState_User());
                                editor.putString("idiograph",((JSONObject)(result.getObject())).toJavaObject(User.class).getIdiograph_User());
								String music=((JSONObject)(result.getObject())).toJavaObject(User.class).getIconFile_User();
								music=music.substring(music.indexOf(',')+1);
//								music.setIconFile_Song(music.getIconFile_Song().substring(music.getIconFile_Song().indexOf(',')+1));
								editor.putString("iconFile_User",music);
                                editor.putBoolean("flag",true);
//                                final Resources res = context.getResources();
//                                final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.album_appwidget);
//                                views.setViewVisibility(R.id.title, View.GONE);
//                                views.setTextViewText(R.id.artist, res.getText(R.string.emptyplaylist));
//                                Handler mainHandler = new Handler(Looper.getMainLooper());
//                                mainHandler.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        //已在主线程中，可以更新UI
////                                        View account = View.inflate(LoginActivity.this, R.layout.fragment_account, null);
////                                        ((TextView)(account.findViewById(R.id.user_name))).setText(((JSONObject)result.getObject()).toJavaObject(User.class).getNickname_User());
////                                        ((TextView)(account.findViewById(R.id.user_val))).setText(((JSONObject)result.getObject()).toJavaObject(User.class).getLevel_User());
//                                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
                                it.putExtra("nickname",((JSONObject)result.getObject()).toJavaObject(User.class).getNickname_User());
                                it.putExtra("level",((JSONObject)result.getObject()).toJavaObject(User.class).getLevel_User());
                                String res2=new String();
                                String res3=new String();
                                try{
                                    String body2= JSON.toJSONString(1);

                                    res2 = HttpUtil.sendPostUrl("http://47.97.202.142:8082/song/getRandom","5","UTF-8");
                                    ResultEntity result2 = JSON.parseObject(res2, ResultEntity.class);
                                    editor.putString("HomeFragmentSong",res2);

                                    String body3 = JSON.toJSONString(1);
                                    res3 = HttpUtil.sendPostUrl("http://47.97.202.142:8082/songList/getRandom","2","UTF-8");

                                    editor.putString("HomeFragment",res2);
                                    editor.putString("Home_SongList",res3);
//                                    MessageBox.sendMessage(LoginActivity.this,res2);
                                }catch (Exception e){
                                    Looper.prepare();
                                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
//                                String str = getString(R.string.qrcode_out_of_date_sec,String.format("<font color=\"#d40000\">%s</font>", 100);
//                                textview.setText(str);
//                                invalidate();
                                editor.commit();
                                startActivity(it);

                                Looper.prepare();
                                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                            else{
                                Looper.prepare();
                                Toast.makeText(LoginActivity.this,"登录失败", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }catch(Exception e){
                            Looper.prepare();
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                }).start();
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
    public static void main(String [] args)
    {

    }
}