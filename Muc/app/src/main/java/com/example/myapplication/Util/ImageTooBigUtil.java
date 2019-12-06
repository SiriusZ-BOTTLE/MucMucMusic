package com.example.myapplication.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

public class ImageTooBigUtil {
	public static Bitmap set(Bitmap bitmap){
		DisplayMetrics dm = new DisplayMetrics();
//		MagetWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth=dm.widthPixels;
		if(bitmap.getWidth()<=screenWidth){
			return bitmap;
		}else{
			return Bitmap.createScaledBitmap(bitmap, screenWidth, bitmap.getHeight()*screenWidth/bitmap.getWidth(), true);
		}

	}
}
