package com.example.myapplication.ui.music;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Util.MessageBox;
import com.example.myapplication.bean_new.SongList;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {//音乐
    private List<SongList> mMusicList;
    private ListAdapter.OnRecycleItemClickListener onRecycleItemClickListener=null;

    static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView musiclistImage;
        TextView musiclistName;
        TextView musiclistAuthor;
        TextView musiclistSize;
        ImageView musiclistDelete;
        View musicView;

        public  ViewHolder(View view){
            super(view);
            musicView = view;
            musiclistImage = (ImageView) view.findViewById(R.id.list_image);
            musiclistName = (TextView) view.findViewById(R.id.list_name);
            musiclistAuthor = (TextView) view.findViewById(R.id.list_author);
            musiclistSize = (TextView) view.findViewById(R.id.list_size);
            musiclistDelete=(ImageView)view.findViewById(R.id.tv_item_menu);
        }
    }

    public ListAdapter(List<SongList> MusicList){
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
                SongList musiclist = mMusicList.get(position);
                Toast.makeText(v.getContext(),"you clicked image "+musiclist.getName_SL(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.musiclistAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SongList musiclist = mMusicList.get(position);
                Toast.makeText(v.getContext(),"you clicked author "+musiclist.getId_User(),Toast.LENGTH_SHORT).show();
            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        final SongList musiclist = mMusicList.get(position);
        holder.musiclistImage.setImageResource(R.drawable.nanshannan);
        holder.musiclistName.setText(musiclist.getName_SL());
//        holder.musiclistSize.setText(String.valueOf(musiclist.getSize()));//显示歌单里歌的数量
        holder.musiclistAuthor.setText(musiclist.getId_User());
        holder.musiclistDelete.setOnClickListener(new View.OnClickListener() {//删除歌单按钮
            @Override
            public void onClick(View v) {
                MessageBox.sendMessage(v.getContext(),"是否要删除这个歌单",musiclist);
            }
        });
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
