package com.example.myapplication.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Looper;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.bean_new.InteractionEntity.ResultEntity;
import com.example.myapplication.bean_new.Song;
import com.example.myapplication.bean_new.SongList;
import com.example.myapplication.bean_new.User;
import com.example.myapplication.ui.home.LoginActivity;

public class MessageBox {
	private static SharedPreferences sp;
	private static SharedPreferences.Editor editor;
	public static void sendMessage(final Context x, String data, final SongList musiclist){
		AlertDialog.Builder builder = new AlertDialog.Builder(x);
		builder.setTitle("标题");
		builder.setMessage(data);
		builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				new Thread(new Runnable(){
					@Override
					public void run() {
						String body= JSON.toJSONString(musiclist);
						String res=HttpUtil.sendPostUrl("http://47.97.202.142:8082/songList/delete",body,"UTF-8");
						ResultEntity result = JSON.parseObject(res, ResultEntity.class);
						try{
							if(result.getState()==true){
								sp = x.getSharedPreferences("test",Context.MODE_PRIVATE);
								editor =  sp.edit();
								User user=new User();
								user.setId_User(sp.getString("name",""));
								String body4=JSON.toJSONString(user);
								String res4 = HttpUtil.sendPostUrl("http://47.97.202.142:8082/songList/getUserSongList",body4,"UTF-8");
								editor.putString("MySongList",res4);
								editor.commit();
								Looper.prepare();
								Toast.makeText(x, "删除歌单成功", Toast.LENGTH_SHORT).show();
								Looper.loop();
							}
							else{
								Looper.prepare();
								Toast.makeText(x, "删除歌单失败", Toast.LENGTH_SHORT).show();
								Looper.loop();
							}
						}catch(Exception e){
							Looper.prepare();
							Toast.makeText(x, "连接失败", Toast.LENGTH_SHORT).show();
							Looper.loop();
						}

					}
				}).start();

			}
		});

		builder.show();
	}
}
