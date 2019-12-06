package com.example.myapplication.ui.home;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.myapplication.bean.Music;
import com.example.myapplication.bean_new.Song;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private List<Song> mMusicList;
    static Map<Integer,Integer> MusicIdtoImage=new HashMap<Integer, Integer>();

    static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView musicImage;
        TextView musicName;
        View musicView;

        public  ViewHolder(View view){
            super(view);
            musicView = view;
            musicImage = (ImageView) view.findViewById(R.id.music_image);
            musicName = (TextView) view.findViewById(R.id.music_name);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes);
//            将byte[]转换为bitmap就可以绑定到imageview上了
        }
    }

    public MusicAdapter(List<Song> MusicList){
        mMusicList = MusicList;
    }

    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent ,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Song music = mMusicList.get(position);
                Toast.makeText(v.getContext(),"you clicked view "+music.getName_Song(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.musicImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Song music = mMusicList.get(position);
                Toast.makeText(v.getContext(),"you clicked image "+music.getName_Song(),Toast.LENGTH_SHORT).show();
            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Song music = mMusicList.get(position);
        music.setIconFile_Song(music.getIconFile_Song().substring(music.getIconFile_Song().indexOf(',')+1));
        byte[] b=Base64Util.decode(music.getIconFile_Song());

//		BitmapFactory.Options op = new BitmapFactory.Options();
//		op.inSampleSize = 2;
//		op.inJustDecodeBounds = true; //它仅仅会把它的宽，高取回来给你，这样就不会占用太多的内存，也就不会那么频繁的发生OOM了。           
//		op.inPreferredConfig = Bitmap.Config.RGB_565; // 默认是Bitmap.Config.ARGB_8888

        holder.musicImage.setImageBitmap(MusicAdapter.createBitmapFromByteData(b,null));
//        MessageBox.sendMessage(MainActivity.class,);
        //测试用
//        holder.musicImage.setImageResource(music.getId_Song());
        holder.musicName.setText(music.getName_Song());
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
}

