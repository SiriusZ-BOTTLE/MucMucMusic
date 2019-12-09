package com.example.myapplication.ui.found;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.Music;
import com.example.myapplication.ui.home.AddSongActivity;

import java.util.ArrayList;
import java.util.List;

public class TagActivity extends AppCompatActivity {
    private List<Music> SongList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null) {
            actionBar.hide();
        }
        Toolbar toolbar = findViewById(R.id.tool_bar_tag);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initMusic();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_caregorydetails);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        TagSongAdapter adapter = new TagSongAdapter(SongList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    private void initMusic(){
        Music three = new Music("南山南", R.drawable.nanshannan,"数据");
        SongList.add(three);
        Music one = new Music("消愁", R.drawable.xiaochou,"数据");
        SongList.add(one);
        Music two = new Music("论坛", R.drawable.luntan,"数据");
        SongList.add(two);
        Music three1 = new Music("南山南", R.drawable.nanshannan,"数据");
        SongList.add(three1);
        Music one1 = new Music("发现", R.drawable.faxian,"数据");
        SongList.add(one1);
        Music two1 = new Music("消愁", R.drawable.xiaochou,"数据");
        SongList.add(two1);
        Music three2 = new Music("南山南", R.drawable.nanshannan,"数据");
        SongList.add(three2);
        Music one3 = new Music("发现", R.drawable.faxian,"数据");
        SongList.add(one3);
        Music two4 = new Music("论坛", R.drawable.luntan,"数据");
        SongList.add(two4);
    }

    class TagSongAdapter extends RecyclerView.Adapter<TagSongAdapter.ViewHolder>{
        private List<Music> SongList;
        class  ViewHolder extends RecyclerView.ViewHolder{
            ImageView songIcon;
            TextView songName;
            TextView songSinger;
            ImageView songAdd;
            View songView;

            public  ViewHolder(View view){
                super(view);
                songView = view;
                songIcon = (ImageView) view.findViewById(R.id.songicon);
                songName = (TextView) view.findViewById(R.id.songname);
                songSinger = (TextView) view.findViewById(R.id.songsinger);
                songAdd = (ImageView) view.findViewById(R.id.songadd);
            }
        }

        public TagSongAdapter(List<Music> MusicList){
            SongList = MusicList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categorydetails, parent ,false);
            final ViewHolder holder = new ViewHolder(view);
            holder.songView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Music song = SongList.get(position);
                    Toast.makeText(v.getContext(),"you clicked view "+song.getName(),Toast.LENGTH_SHORT).show();
                }
            });
            holder.songAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TagActivity.this, AddSongActivity.class);
                    startActivity(intent);
                }
            });
            return  holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            Music song = SongList.get(position);
            holder.songIcon.setImageResource(song.getImageId());
            holder.songName.setText(song.getName());
            holder.songSinger.setText(song.getAuthor());
        }

        @Override
        public int getItemCount(){
            return SongList.size();
        }
    }
}

