package com.example.myapplication.Util;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;

public class GetDurationUtil {//获得歌曲时长
	private static SharedPreferences sp;
	private static SharedPreferences.Editor editor;
	public static void getduration(final String URL,Context c){
		sp = c.getSharedPreferences("test", Context.MODE_PRIVATE);
		editor =  sp.edit();
		new Thread(new Runnable(){
			@Override
			public void run() {
				java.net.URL urlfile = null;
				try {
					int time=-1;

					urlfile = new URL(URL);
					URLConnection con = null;
					con = urlfile.openConnection();
					int b = con.getContentLength();
					BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
					Bitstream bt = new Bitstream(bis);
					Header h = null;
					try {
						h = bt.readFrame();
					} catch (BitstreamException e) {
						e.printStackTrace();
					}
					time = (int) h.total_ms(b);
					editor.putInt("time",time);
					editor.commit();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}).start();


	}
}
