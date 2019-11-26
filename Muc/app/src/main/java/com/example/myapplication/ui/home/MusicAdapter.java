package com.example.myapplication.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.Music;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private List<Music> mMusicList;


    static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView musicImage;
        TextView musicName;
        View musicView;

        public  ViewHolder(View view){
            super(view);
            musicView = view;
            musicImage = (ImageView) view.findViewById(R.id.music_image);
            musicName = (TextView) view.findViewById(R.id.music_name);

        }
    }

    public MusicAdapter(List<Music> MusicList){
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
                Music music = mMusicList.get(position);
                Toast.makeText(v.getContext(),"you clicked view "+music.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.musicImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Music music = mMusicList.get(position);
                Toast.makeText(v.getContext(),"you clicked image "+music.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Music music = mMusicList.get(position);
        holder.musicImage.setImageResource(music.getImageId());
        holder.musicName.setText(music.getName());
    }

    @Override
    public int getItemCount(){
        return mMusicList.size();
    }
}

