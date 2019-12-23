package com.example.myapplication.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.example.myapplication.Util.Base64Util;
import com.example.myapplication.Util.HttpUtil;
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
        initSearch();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_serach);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        SearchshowAdapter adapter = new SearchshowAdapter(mMusicList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.OnRecycleItemClickListener(new SearchshowAdapter.OnRecycleItemClickListener() {//点击歌曲跳转到播放界面
            @Override
            public void OnRecycleItemClickListener(int position) {
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
            }
        });

    }
    private void initSearch(){
        try{
            ThreadgetSong thread1 = new ThreadgetSong();
            thread1.start();
            thread1.join();
        }catch (Exception e){

        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("getSong", "handleMessage: " );
                    break;
                default:
                    break;
            }
        }
    };
    private class  ThreadgetSong extends Thread {
        @Override
        public void run() {
            String res = new String();
            Song song=new Song();
            song.setName_Song(getSearchResult());
            String body= JSON.toJSONString(song);
            res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/song/search",body,"UTF-8");
            ResultEntity result = JSON.parseObject(res, ResultEntity.class);
            if (result.getState() == true) {
                for(int i = 0; i<((JSONArray)(result.getObject())).size(); i++){
                    mMusicList.add(((JSONObject)(((JSONArray)(result.getObject())).get(i))).toJavaObject(Song.class));
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            }
        }
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
        ImageView songIcon;
        View searchView;

        public ViewHolder(View view) {
            super(view);
            searchView = view;
            Num = view.findViewById(R.id.search_num);
            songName = view.findViewById(R.id.search_name);
            songSinger = view.findViewById(R.id.search_singer);
            songIcon = view.findViewById(R.id.search_image);
        }
    }

    public SearchshowAdapter(List<Song> SongList) {
        mMusicList = SongList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Song song = mMusicList.get(position);
        song.setIconFile_Song(song.getIconFile_Song().substring(song.getIconFile_Song().indexOf(',')+1));
        byte[] b= Base64Util.decode(song.getIconFile_Song());
        holder.songIcon.setImageBitmap(BitmapFactory.decodeByteArray(b, 0, b.length));
        holder.songName.setText(song.getName_Song());
        holder.songSinger.setText(song.getSinger_Song());

        holder.Num.setText(String.valueOf(position+1));
        holder.searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListplayActivity.class);
                MusicUtils.list.add(song);
                HashSet h = new HashSet(MusicUtils.list);
                MusicUtils.list.clear();
                MusicUtils.list.addAll(h);
//                MusicUtils.list=new ArrayList<Song>(new LinkedHashSet<Song>(MusicUtils.list));//删去重复

                v.getContext().startActivity(intent);
            }
        });
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
