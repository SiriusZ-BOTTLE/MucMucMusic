package com.example.myapplication.Util;

import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
public class Base64Util {
	public static String  encode(byte[] encrypted){

		return Base64.encodeBase64String(encrypted);
	}

	public static byte[] decode(String text){
		//解密
		byte[] encrypted1 =Base64.decodeBase64(text);
		return  encrypted1;
	}
}
