package com.example.myapplication.ui.home;

import android.app.ActionBar;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Util.Base64Util;
import com.example.myapplication.Util.HttpUtil;

import com.example.myapplication.Util.MessageBox;
import com.example.myapplication.bean.Music;
import com.example.myapplication.bean.MusicList;
import com.example.myapplication.bean_new.Song;
import com.example.myapplication.bean_new.InteractionEntity.ResultEntity;
import com.example.myapplication.bean_new.SongList;
import com.example.myapplication.bean_new.User;
import com.example.myapplication.ui.music.play.ListplayActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private List<Song> musiclist = new ArrayList<>();//歌曲数组
    private List<SongList> songlist= new ArrayList<>();//歌单数组

    private SharedPreferences sp;


    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        TabHost tabHost= root.findViewById(R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("歌曲").setContent(R.id.tab_music));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("歌单").setContent(R.id.tab_musiclist));

        SearchView mSearchView = (SearchView) root.findViewById(R.id.title_sousuo);
        TextView tv = null;
        TabWidget tabWidget = tabHost.getTabWidget();
        for (int i = 0, count = tabWidget.getChildCount(); i < count; i++) {
            View v = tabWidget.getChildAt(i);
            v.setBackgroundResource(R.drawable.tab_selector);
            tv = ((TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title));
            tv.setTextColor(Color.rgb(255,255,255));
        }

        initMusic();

        initMusicList();

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_music);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MusicAdapter adapter = new MusicAdapter(musiclist,getActivity());
        recyclerView.setAdapter(adapter);


        RecyclerView recyclerView1 =root.findViewById(R.id.recycler_view_musiclist);
        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(),2);
        recyclerView1.setLayoutManager(layoutManager1);
        MusicListAdapter adapter1 = new MusicListAdapter(songlist);
        recyclerView1.setAdapter(adapter1);

        ImageView play = (ImageView) root.findViewById(R.id.title_bofang);
        play.setOnClickListener(new View.OnClickListener() {//跳转到播放音乐界面
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListplayActivity.class);
                startActivity(intent);
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    Toast.makeText(getActivity(),"你使用了搜索",Toast.LENGTH_SHORT).show();
                }else{
                    return false;
                }
                return false;
            }
        });
        return root;
    }

    private void initMusicList(){
        sp = getActivity().getSharedPreferences("test",Context.MODE_PRIVATE);//初始化
        String res=sp.getString("Home_SongList","");
        if(res.isEmpty()){
            return;
        }
        ResultEntity result = JSON.parseObject(res, ResultEntity.class);
        if(result.getState()==true){
            for(int i=0;i<((JSONArray)(result.getObject())).size();i++){
                songlist.add(((JSONObject)(((JSONArray)(result.getObject())).get(i))).toJavaObject(SongList.class));
            }
        }
        else{
            Toast.makeText(getActivity(), "获得歌曲失败", Toast.LENGTH_SHORT).show();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("idList", "handleMessage: " );
                    break;
                case 2:
                    Log.d("music", "handleMessage: " );
                    break;
                default:
                    break;
            }
        }
    };
    private void initMusic(){//初始化主页歌曲界面
        sp = getActivity().getSharedPreferences("test",Context.MODE_PRIVATE);//初始化
        String res=sp.getString("HomeFragmentSong","");
        if(res.isEmpty()){
            return;
        }
        ResultEntity result = JSON.parseObject(res, ResultEntity.class);
        if(result.getState()==true){
            for(int i=0;i<((JSONArray)(result.getObject())).size();i++){
                musiclist.add(((JSONObject)(((JSONArray)(result.getObject())).get(i))).toJavaObject(Song.class));
            }
        }
        else{
            Toast.makeText(getActivity(), "获得歌曲失败", Toast.LENGTH_SHORT).show();
        }

//        Song three = new Song("南山南",R.drawable.nanshannan,"数据");
//        musiclist.add(three);
//        Song one = new Song("消愁",R.drawable.xiaochou,"数据");
//        musiclist.add(one);
//        Song two = new Song("论坛",R.drawable.luntan,"数据");
//        musiclist.add(two);
//        Song three1 = new Song("南山南",R.drawable.nanshannan,"数据");
//        musiclist.add(three1);
//        Song one1 = new Song("发现",R.drawable.faxian,"数据");
//        musiclist.add(one1);
//        Song two1 = new Song("消愁",R.drawable.xiaochou,"数据");
//        musiclist.add(two1);
//        Song three2 = new Song("南山南",R.drawable.nanshannan,"数据");
//        musiclist.add(three2);
//        Song one3 = new Song("发现",R.drawable.faxian,"数据");
//        musiclist.add(one3);
//        Song two4 = new Song("论坛",R.drawable.luntan,"数据");
//        musiclist.add(two4);
    }
}