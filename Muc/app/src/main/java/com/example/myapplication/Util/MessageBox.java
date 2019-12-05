package com.example.myapplication.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class MessageBox {
	public static void sendMessage(Context x,String data){
		AlertDialog.Builder builder = new AlertDialog.Builder(x);
		builder.setTitle("标题");
		builder.setMessage(data);
		builder.setNeutralButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		builder.show();
	}
}
