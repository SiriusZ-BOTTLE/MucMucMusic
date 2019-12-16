package com.example.myapplication.ui.music;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.R;
import com.example.myapplication.Util.Base64Util;
import com.example.myapplication.Util.HttpUtil;
import com.example.myapplication.bean_new.InteractionEntity.ResultEntity;
import com.example.myapplication.bean_new.Song;
import com.example.myapplication.bean_new.SongList;
import com.example.myapplication.bean_new.User;
import com.example.myapplication.ui.found.TagActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.example.myapplication.bean_new.SongList;
//import com.example.myapplication.ui.found.AddSongActivity.AddSongAdapter;

public class AddSongActivity extends AppCompatActivity {
    private List<SongList> songLists = new ArrayList<>();
    private String songid;
    private SharedPreferences sp;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsong);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
        Toolbar toolbar = findViewById(R.id.tool_addsong);
        Intent it = getIntent();
        songid = it.getStringExtra("song_id_c");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initList1();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_addsongdetails);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        AddSongAdapter adapter = new AddSongAdapter(songLists,songid);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }


    private void initList1() {

        sp = AddSongActivity.this.getSharedPreferences("test", Context.MODE_PRIVATE);//初始化
        String res=sp.getString("MySongList","");
        if(res.isEmpty()){
            return;
        }
        ResultEntity result = JSON.parseObject(res, ResultEntity.class);
        if(result.getState()==true){
            for(int i = 0; i<((JSONArray)(result.getObject())).size(); i++){
                songLists.add(((JSONObject)(((JSONArray)(result.getObject())).get(i))).toJavaObject(SongList.class));
            }
        }
        else{
            Toast.makeText(AddSongActivity.this, "获得歌曲失败", Toast.LENGTH_SHORT).show();
        }

    }
//    public void onBackPressed() {
//        new AlertDialog.Builder(AddSongActivity.this)
//                .setTitle("确认对话框")
//                .setMessage("确定添加进该歌单?")
//                .setNegativeButton("取消", null)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // finish();
//                        Intent intent = new Intent(AddSongActivity.this, TagActivity.class);
//                        startActivity(intent);
//                    }
//                })
//                .show();
//    }
}
class AddSongAdapter extends RecyclerView.Adapter<AddSongAdapter.ViewHolder> {
    private List<SongList> addsongList;
    private String songid;
    private SharedPreferences sp;

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView addsongimage;
            TextView addsongtName;
            View addsongView;

            public ViewHolder(View view) {
                super(view);
                addsongimage = (ImageView) view.findViewById(R.id.addsonglist_image);
                addsongView = view;
                addsongtName = (TextView) view.findViewById(R.id.addsonglist_name);

            }
        }

        public AddSongAdapter(List<SongList> AddsongList,String songId) {
            addsongList = AddsongList;
            this.songid=songId;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addsonglist, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            holder.addsongView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("确认对话框")
                            .setMessage("确定添加进该歌单?")
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new Thread(new Runnable(){
                                        @Override
                                        public void run() {
                                            try{
                                                int position = holder.getAdapterPosition();
                                                sp = v.getContext().getSharedPreferences("test",Context.MODE_PRIVATE);
                                                User user=new User();
                                                user.setId_User(sp.getString("name",""));
                                                Map<String,Object> map=new HashMap<>();
                                                Song song=new Song();
                                                song.setId_Song(Integer.parseInt(songid));
                                                map.put("song",song);
                                                map.put("songList",addsongList.get(position));
                                                String body= JSON.toJSONString(map);
                                                String res= HttpUtil.sendPostUrl("http://47.97.202.142:8082/songList/addSongToSL",body,"UTF-8");
                                                ResultEntity result = JSON.parseObject(res, ResultEntity.class);
                                                if(result.getState()==true){
                                                    Looper.prepare();
                                                    Toast.makeText(v.getContext(), "成功", Toast.LENGTH_SHORT).show();
                                                    Looper.loop();
                                                }
                                                else{
                                                    Looper.prepare();
                                                    Toast.makeText(v.getContext(), "失败", Toast.LENGTH_SHORT).show();
                                                    Looper.loop();
                                                }
                                            }catch(Exception e){
                                                Looper.prepare();
                                                Toast.makeText(v.getContext(), "连接失败1", Toast.LENGTH_SHORT).show();
                                                Looper.loop();
                                            }
                                        }
                                    }).start();

                                }
                            })
                            .show();
                }

            });


            return holder;
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            SongList songlist = addsongList.get(position);
//            holder.addsongimage.setImageResource(addsonglist.getImageID());
//            song.setIconFile_Song(song.getIconFile_Song().substring(song.getIconFile_Song().indexOf(',')+1));
//            byte[] b= Base64Util.decode(song.getIconFile_Song());
//            holder.addsongimage.setImageBitmap(BitmapFactory.decodeByteArray(b, 0, b.length));
            //        holder.addsonglistImage.setImageResource(AddSongAdapter.MusicIdtoImage.get(addsonglist.getId_SL()));
            holder.addsongtName.setText(songlist.getName_SL());
        }

        @Override
        public int getItemCount() {
            return addsongList.size();
        }

        //    public class MusicIdtoImage {
        //    }
}


