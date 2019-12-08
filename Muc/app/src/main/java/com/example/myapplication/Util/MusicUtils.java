package com.example.myapplication.Util;


import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.myapplication.bean_new.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicUtils {
    public static List<Song> list = new ArrayList<>();


    /**
     * 扫描系统里面的音频文件，返回一个list集合
     * 变更为保存播放列表的歌曲
     */
//    public static List<Song> getMusicData(Context context) {
//        List<Song> list = new ArrayList<>();
//        Cursor cursor = context.getContentResolver().query(
//                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
//                MediaStore.Audio.AudioColumns.IS_MUSIC);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                Song song = new Song();
//                song.setName_Song( cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
//                song.setSinger_Song( cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
//                song.setFile_Song(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
//                song.setDuration( cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
//                song.setSize( cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));
//                if (song.getSize() > 1000 * 800) {//过滤掉短音频
//                    // 分离出歌曲名和歌手
//                    if (song.getName_Song().contains("-")) {
//                        String[] str = song.getName_Song().split("-");
//                        song.setSinger_Song( str[0]);
//                        song.setName_Song( str[1]);
//                    }
//                    list.add(song);
//                }
//            }
//            // 释放资源
//            cursor.close();
//        }
//        return list;
//    }





    //格式化时间
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            return time / 1000 / 60 + ":0" + time / 1000 % 60;
        } else {
            return time / 1000 / 60 + ":" + time / 1000 % 60;
        }
    }
}

