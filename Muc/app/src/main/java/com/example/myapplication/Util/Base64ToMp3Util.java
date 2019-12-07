package com.example.myapplication.Util;

import android.telephony.mbms.FileInfo;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Base64ToMp3Util {
	/**
	 * 将base64字符解码保存文件
	 * @param base64Code
	 * @throws Exception
	 */

	public static void decoderBase64File(String base64Code)
			throws Exception {
		byte[] buffer = Base64Util.decode(base64Code);
//		FileOutputStream out = new FileOutputStream(targetPath+"/1.wav");
		FileOutputStream out = new FileOutputStream("tempSongFile.mp3");
		out.write(buffer);
		out.close();

	}
}
