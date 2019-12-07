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
import com.example.myapplication.bean_new.SongList;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{
    private List<SongList> mMusicList;


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

    public MusicListAdapter(List<SongList> MusicList){
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
                SongList music = mMusicList.get(position);
                Toast.makeText(v.getContext(),"you clicked view "+music.getName_SL(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.musiclistImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SongList music = mMusicList.get(position);
                Toast.makeText(v.getContext(),"you clicked image "+music.getName_SL(),Toast.LENGTH_SHORT).show();
            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(MusicListAdapter.ViewHolder holder, int position){
        SongList musiclist = mMusicList.get(position);
//        musiclist.setDescription_SL();
//        music.setIconFile_Song(music.getIconFile_Song().substring(music.getIconFile_Song().indexOf(',')+1));
//
//        byte[] b= Base64Util.decode(music.getIconFile_Song());
//        musicViewHolder.musicImage.setImageBitmap(BitmapFactory.decodeByteArray(b, 0, b.length));
//        holder.musiclistImage.setImageResource(musiclist.getId_SL());
        //holder.musiclistImage.setImageResource(MusicAdapter.MusicIdtoImage.get(musiclist.getId_SL()));
        holder.musiclistName.setText(musiclist.getName_SL());
    }

    @Override
    public int getItemCount(){
        return mMusicList.size();
    }
}
