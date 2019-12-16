package com.example.myapplication.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.R;
import com.example.myapplication.Util.MusicUtils;
import com.example.myapplication.bean_new.InteractionEntity.ResultEntity;
import com.example.myapplication.bean_new.Song;
import com.example.myapplication.ui.music.play.ListplayActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private List<Song> mMusicList = new ArrayList<>();
    private SharedPreferences sp;
    private SearchshowAdapter adapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Toolbar toolbar = findViewById(R.id.tool_bar_serach);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        sp = SearchActivity.this.getSharedPreferences("test", Context.MODE_PRIVATE);//初始化
        String res=sp.getString("search_result","");
        ResultEntity result = JSON.parseObject(res, ResultEntity.class);
        if(result.getState()==true){
            for(int i = 0; i<((JSONArray)(result.getObject())).size(); i++){
                mMusicList.add(((JSONObject)(((JSONArray)(result.getObject())).get(i))).toJavaObject(Song.class));
            }
        }
        else{
            Toast.makeText(SearchActivity.this, "获得歌曲失败", Toast.LENGTH_SHORT).show();
        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_serach);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new SearchshowAdapter(mMusicList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.OnRecycleItemClickListener(new SearchshowAdapter.OnRecycleItemClickListener() {//点击歌曲跳转到播放界面
            @Override
            public void OnRecycleItemClickListener(int position) {
                Song music = mMusicList.get(position);
                Intent intent = new Intent(SearchActivity.this, ListplayActivity.class);
                MusicUtils.list.add(music);
                HashSet h = new HashSet(MusicUtils.list);
                MusicUtils.list.clear();
                MusicUtils.list.addAll(h);
//                MusicUtils.list=new ArrayList<Song>(new LinkedHashSet<Song>(MusicUtils.list));//删去重复

                SearchActivity.this.startActivity(intent);
            }
        });

    }
    public String getSearchResult(){
        Intent intent = getIntent();
        String query = intent.getStringExtra("Search");
        return query;
    }
}

class SearchshowAdapter extends RecyclerView.Adapter<SearchshowAdapter.ViewHolder> {
    private List<Song> mMusicList;
    private OnRecycleItemClickListener onRecycleItemClickListener=null;
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Num;
        TextView songName;
        TextView songSinger;
        View searchView;

        public ViewHolder(View view) {
            super(view);
            searchView = view;
            Num = view.findViewById(R.id.search_num);
            songName = view.findViewById(R.id.search_name);
            songSinger = view.findViewById(R.id.search_singer);

        }
    }

    public SearchshowAdapter(List<Song> SongList) {
        mMusicList = SongList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击歌曲跳转到播放界面
                int position = holder.getAdapterPosition();
                Song music = mMusicList.get(position);
                Intent intent = new Intent(v.getContext(), ListplayActivity.class);
                MusicUtils.list.add(music);
                HashSet h = new HashSet(MusicUtils.list);
                MusicUtils.list.clear();
                MusicUtils.list.addAll(h);
//                MusicUtils.list=new ArrayList<Song>(new LinkedHashSet<Song>(MusicUtils.list));//删去重复

                v.getContext().startActivity(intent);            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = mMusicList.get(position);
        holder.songName.setText(song.getName_Song());
        holder.songSinger.setText(song.getSinger_Song());
//        holder.Num.setText(position + 1);
    }

    @Override
    public int getItemCount() {
        return mMusicList.size();
    }
    public  void  OnRecycleItemClickListener(SearchshowAdapter.OnRecycleItemClickListener v){
        onRecycleItemClickListener = v;
    }

    public interface OnRecycleItemClickListener{
        void OnRecycleItemClickListener(int position);
    }
}
