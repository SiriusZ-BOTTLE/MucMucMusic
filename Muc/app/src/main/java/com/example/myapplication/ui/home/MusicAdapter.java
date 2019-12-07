package com.example.myapplication.ui.home;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Util.Base64Util;
import com.example.myapplication.Util.MessageBox;
import com.example.myapplication.Util.MusicUtils;
import com.example.myapplication.bean_new.Song;
import com.example.myapplication.ui.music.play.ListplayActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MusicAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private List<Song> mMusicList;
    private Context mContext;
    static Map<Integer,Integer> MusicIdtoImage=new HashMap<Integer, Integer>();
    private MusicAdapter.OnRecycleItemClickListener onRecycleItemClickListener =null;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public static Song songtolist=new Song();//点击将要加入播放列表的歌曲

    static class  MusicViewHolder extends RecyclerView.ViewHolder{
        ImageView musicImage;
        TextView musicName;
        View musicView;

        MusicViewHolder(View view){
            super(view);
            musicView = view;
            musicImage = (ImageView) view.findViewById(R.id.music_image);
            musicName = (TextView) view.findViewById(R.id.music_name);

        }
    }

    public MusicAdapter(List<Song> MusicList, Context mContext){
        this.mContext = mContext;
        this.mMusicList = MusicList;
    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent ,false);
        final RecyclerView.ViewHolder holder = new MusicViewHolder(view);
        view.setOnClickListener(this);
        holder.itemView.setOnClickListener(new View.OnClickListener() {//点击歌曲进入播放页面
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Song music = mMusicList.get(position);
                Intent intent = new Intent(v.getContext(), ListplayActivity.class);
                songtolist.setName_Song(music.getName_Song());
                songtolist.setFile_Song(music.getFile_Song());
                songtolist.setSinger_Song(music.getSinger_Song());
                songtolist.setIconFile_Song(music.getIconFile_Song());
                songtolist.setId_Song(music.getId_Song());
                songtolist.setReleaseDate_Song(music.getReleaseDate_Song());
                songtolist.setScore(music.getScore());
                MusicUtils.list.add(songtolist);
                MusicUtils.list=new ArrayList<Song>(new LinkedHashSet<Song>(MusicUtils.list));

                v.getContext().startActivity(intent);
                Toast.makeText(v.getContext(),"you clicked image "+music.getName_Song(),Toast.LENGTH_SHORT).show();
            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position){
        MusicViewHolder musicViewHolder = (MusicViewHolder) holder;
        Song music = mMusicList.get(position);
        music.setIconFile_Song(music.getIconFile_Song().substring(music.getIconFile_Song().indexOf(',')+1));

        byte[] b= Base64Util.decode(music.getIconFile_Song());
        musicViewHolder.musicImage.setImageBitmap(BitmapFactory.decodeByteArray(b, 0, b.length));
//        holder.musicImage.setImageResource(music.getId_Song());
        musicViewHolder.musicName.setText(music.getName_Song());
    }
	private static Bitmap createBitmapFromByteData(byte[] data , BitmapFactory.Options options){
    	Bitmap bitmap = null;
		if(options == null){
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		}else{
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
		}
		return bitmap;
	}

    @Override
    public int getItemCount(){
        return mMusicList.size();
    }
//    /**
//     * 输入ID获取图片ID
//     * @param image
//     * @return int
//     */
//    public static int getImage(String image){
//        int res_ID=0;
//        Class drawable = R.drawable.class;
//        Field field = null;
//        try{
//            field = drawable.getField(image);
//            res_ID = field.getInt(field.getName());
//        }catch (Exception e){
//
//        }
//        return res_ID;
//    }
    public void OnRecycleItemClickListener(MusicAdapter.OnRecycleItemClickListener v) {
        onRecycleItemClickListener = v;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface OnRecycleItemClickListener {
        void OnRecycleItemClickListener(View view,int position);
    }

    @Override
    public void onClick(View v) {
        if (onRecycleItemClickListener != null)
            onRecycleItemClickListener.OnRecycleItemClickListener(v, (Integer) v.getTag());
    }
}

