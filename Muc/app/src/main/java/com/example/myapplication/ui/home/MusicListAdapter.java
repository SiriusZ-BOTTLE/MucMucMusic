package com.example.myapplication.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.MusicList;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{
    private List<MusicList> mMusicList;


    static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView musiclistImage;
        TextView musiclistName;
        View musicListView;

        public  ViewHolder(View view){
            super(view);
            musicListView = view;
            musiclistImage = (ImageView) view.findViewById(R.id.musiclist_image);
            musiclistName = (TextView) view.findViewById(R.id.musiclist_name);

        }
    }

    public MusicListAdapter(List<MusicList> MusicList){
        mMusicList = MusicList;
    }
    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_musiclist, parent ,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.musicListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MusicList music = mMusicList.get(position);
                Toast.makeText(v.getContext(),"you clicked view "+music.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.musiclistImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MusicList music = mMusicList.get(position);
                Toast.makeText(v.getContext(),"you clicked image "+music.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(MusicListAdapter.ViewHolder holder, int position){
        MusicList musiclist = mMusicList.get(position);
        holder.musiclistImage.setImageResource(musiclist.getImageID());
        holder.musiclistName.setText(musiclist.getName());
    }

    @Override
    public int getItemCount(){
        return mMusicList.size();
    }
}
