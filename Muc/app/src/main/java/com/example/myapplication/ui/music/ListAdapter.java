package com.example.myapplication.ui.music;

import android.content.Intent;
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


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<MusicList> mMusicList;
    private ListAdapter.OnRecycleItemClickListener onRecycleItemClickListener=null;

    static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView musiclistImage;
        TextView musiclistName;
        TextView musiclistAuthor;
        TextView musiclistSize;
        View musicView;

        public  ViewHolder(View view){
            super(view);
            musicView = view;
            musiclistImage = (ImageView) view.findViewById(R.id.list_image);
            musiclistName = (TextView) view.findViewById(R.id.list_name);
            musiclistAuthor = (TextView) view.findViewById(R.id.list_author);
            musiclistSize = (TextView) view.findViewById(R.id.list_size);
        }
    }

    public ListAdapter(List<MusicList> MusicList){
        mMusicList = MusicList;
    }
    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent ,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.musiclistImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MusicList musiclist = mMusicList.get(position);
                Toast.makeText(v.getContext(),"you clicked image "+musiclist.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.musiclistAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MusicList musiclist = mMusicList.get(position);
                Toast.makeText(v.getContext(),"you clicked author "+musiclist.getAuthor(),Toast.LENGTH_SHORT).show();
            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        MusicList musiclist = mMusicList.get(position);
        holder.musiclistImage.setImageResource(musiclist.getImageID());
        holder.musiclistName.setText(musiclist.getName());
        holder.musiclistSize.setText(String.valueOf(musiclist.getSize()));
        holder.musiclistAuthor.setText(musiclist.getAuthor());
        holder.musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRecycleItemClickListener!=null)
                    onRecycleItemClickListener.OnRecycleItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount(){
        return mMusicList.size();
    }

    public  void  OnRecycleItemClickListener(ListAdapter.OnRecycleItemClickListener v){
        onRecycleItemClickListener = v;
    }

    public interface OnRecycleItemClickListener{
        void OnRecycleItemClickListener(int position);
    }
}
