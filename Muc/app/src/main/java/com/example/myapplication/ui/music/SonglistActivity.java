package com.example.myapplication.ui.music;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.myapplication.bean_new.SongList;
import com.example.myapplication.bean_new.User;
import com.example.myapplication.ui.music.play.ListplayActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SonglistActivity extends AppCompatActivity {
    private List<Song> SongList = new ArrayList<>();
    private SongList list;
    private Intent intent;
    private SharedPreferences sp;
    private TextView username;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songlist);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null) {
            actionBar.hide();
        }
        Toolbar toolbar = findViewById(R.id.tool_bar_songlist);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list = (com.example.myapplication.bean_new.SongList) getIntent().getSerializableExtra("songlist");

        username = (TextView) findViewById(R.id.info_author);
        User uu = new User();
        uu.setId_User(list.getId_User());
        try{
            ThreadgetUser thread2 = new ThreadgetUser(uu);
            thread2.start();
            thread2.join();
        }catch (Exception e){

        }
        TextView detail = (TextView) findViewById(R.id.info_details);

        detail.setText(list.getDescription_SL());
        initMusic();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_songlist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        SonglistAdapter adapter = new SonglistAdapter(SongList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.OnRecycleItemClickListener(new SonglistAdapter.OnRecycleItemClickListener() {//点击歌曲播放
            @Override
            public void OnRecycleItemClickListener(int position) {
                Song music = SongList.get(position);
                Intent intent = new Intent(SonglistActivity.this, ListplayActivity.class);
                MusicUtils.list.add(music);
                HashSet h = new HashSet(MusicUtils.list);
                MusicUtils.list.clear();
                MusicUtils.list.addAll(h);
//                MusicUtils.list=new ArrayList<Song>(new LinkedHashSet<Song>(MusicUtils.list));//删去重复

                SonglistActivity.this.startActivity(intent);
                Toast.makeText(SonglistActivity.this,"you clicked image "+music.getName_Song(),Toast.LENGTH_SHORT).show();
            }
        });

        Button button = findViewById(R.id.info_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"you collect this list",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initMusic(){//初始化歌单中的歌曲
        try{
            ThreadgetSong thread1 = new ThreadgetSong();
            thread1.start();
            thread1.join();
        }catch (Exception e){

        }

//        sp = SonglistActivity.this.getSharedPreferences("test", Context.MODE_PRIVATE);//初始化
//        String res=sp.getString("SongList'sSong","");
//
//        if(res.isEmpty()){
//            return;
//        }
//        ResultEntity result = JSON.parseObject(res, ResultEntity.class);
//        if(result.getState()==true){
//            for(int i = 0; i<((JSONArray)(result.getObject())).size(); i++){
//                SongList.add(((JSONObject)(((JSONArray)(result.getObject())).get(i))).toJavaObject(Song.class));
//            }
//        }
//        else{
//            Toast.makeText(SonglistActivity.this, "获得歌曲失败", Toast.LENGTH_SHORT).show();
//        }
//        Music three = new Music("南山南",R.drawable.nanshannan,"数据");
//       SongList.add(three);
//        Music one = new Music("消愁",R.drawable.xiaochou,"数据");
//        SongList.add(one);
//        Music two = new Music("论坛",R.drawable.luntan,"数据");
//        SongList.add(two);
//        Music three1 = new Music("南山南",R.drawable.nanshannan,"数据");
//        SongList.add(three1);
//        Music one1 = new Music("发现",R.drawable.faxian,"数据");
//        SongList.add(one1);
//        Music two1 = new Music("消愁",R.drawable.xiaochou,"数据");
//        SongList.add(two1);
//        Music three2 = new Music("南山南",R.drawable.nanshannan,"数据");
//        SongList.add(three2);
//        Music one3 = new Music("发现",R.drawable.faxian,"数据");
//        SongList.add(one3);
//        Music two4 = new Music("论坛",R.drawable.luntan,"数据");
//        SongList.add(two4);
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("getSong", "handleMessage: " );
                    break;
                case 2:
                    Log.d("getUser", "handleMessage: " );
                    break;
                default:
                    break;
            }
        }
    };
    private class ThreadgetUser extends Thread {
        private User user;
        public ThreadgetUser(User u){
            this.user = u;
        }
        @Override
        public void run() {
            String body = JSON.toJSONString(user);
            String res = new String();
            res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/user/get", body, "UTF-8");
            ResultEntity result2 = JSON.parseObject(res, ResultEntity.class);
            User u = ((JSONObject) result2.getObject()).toJavaObject(User.class);
            username.setText(u.getNickname_User());
            Message message = new Message();
            message.what = 2;
            handler.sendMessage(message);
        }
    }
    private class  ThreadgetSong extends Thread {
        @Override
        public void run() {
            String res = new String();
            String body = JSON.toJSONString(list);
            res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/song/getSongInSongList", body, "UTF-8");
            ResultEntity result = JSON.parseObject(res, ResultEntity.class);
            if (result.getState() == true) {
                for (int i = 0; i < ((JSONArray) (result.getObject())).size(); i++) {
                    SongList.add(((JSONObject) (((JSONArray) (result.getObject())).get(i))).toJavaObject(Song.class));
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            }
        }
    }
}
class SonglistAdapter extends RecyclerView.Adapter<SonglistAdapter.ViewHolder>{
    private List<Song> SongList;
    private SonglistAdapter.OnRecycleItemClickListener onRecycleItemClickListener=null;
    static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView musicImage;
        TextView musicName;
        TextView musicAuthor;
        ImageView musicFavorite;
        View musicView;

        public  ViewHolder(View view){
            super(view);
            musicView = view;
            musicImage = (ImageView) view.findViewById(R.id.song_image);
            musicName = (TextView) view.findViewById(R.id.song_name);
            musicAuthor = (TextView) view.findViewById(R.id.song_author);
            musicFavorite = (ImageView) view.findViewById(R.id.image_favorite);
        }
    }

    public SonglistAdapter(List<Song> MusicList){
        SongList = MusicList;
    }
    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_songlist, parent ,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Song music = SongList.get(position);
                Intent intent = new Intent(v.getContext(), ListplayActivity.class);
                MusicUtils.list.add(music);
                HashSet h = new HashSet(MusicUtils.list);
                MusicUtils.list.clear();
                MusicUtils.list.addAll(h);
//                MusicUtils.list=new ArrayList<Song>(new LinkedHashSet<Song>(MusicUtils.list));//删去重复

                v.getContext().startActivity(intent);
            }
        });
        holder.musicFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Song song = SongList.get(position);
                Toast.makeText(v.getContext(),"you favorited this song "+song.getName_Song(),Toast.LENGTH_SHORT).show();
            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Song song = SongList.get(position);

        song.setIconFile_Song(song.getIconFile_Song().substring(song.getIconFile_Song().indexOf(',')+1));
        byte[] b= Base64Util.decode(song.getIconFile_Song());
        holder.musicImage.setImageBitmap(BitmapFactory.decodeByteArray(b, 0, b.length));

//        holder.musicImage.setImageResource(song.getImageId());
        holder.musicName.setText(song.getName_Song());
        holder.musicAuthor.setText(song.getSinger_Song());

    }

    @Override
    public int getItemCount(){
        return SongList.size();
    }

    public  void  OnRecycleItemClickListener(SonglistAdapter.OnRecycleItemClickListener v){
        onRecycleItemClickListener = v;
    }

    public interface OnRecycleItemClickListener{
        void OnRecycleItemClickListener(int position);
    }
}
