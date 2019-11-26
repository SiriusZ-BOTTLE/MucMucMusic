package com.example.myapplication.ui.home;

import android.app.ActionBar;
import android.app.Notification;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.bean.Music;
import com.example.myapplication.bean.MusicList;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private List<Music> musiclist = new ArrayList<>();
    private List<MusicList> musicList = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
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
            tv = ((TextView) tabWidget.getChildAt(i).findViewById(
                    android.R.id.title));
            tv.setTextColor(Color.rgb(255,255,255));
        }

        initMusic();
        initMusicList();

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_music);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MusicAdapter adapter = new MusicAdapter(musiclist);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView1 =root.findViewById(R.id.recycler_view_musiclist);
        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(),2);
        recyclerView1.setLayoutManager(layoutManager1);
        MusicListAdapter adapter1 = new MusicListAdapter(musicList);
        recyclerView1.setAdapter(adapter1);

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
        for(int i=0;i<3;i++){
            MusicList three = new MusicList("南山南",R.drawable.nanshannan);
            musicList.add(three);
            MusicList one = new MusicList("消愁",R.drawable.xiaochou);
            musicList.add(one);
            MusicList two = new MusicList("南山南",R.drawable.nanshannan);
            musicList.add(two);
            MusicList one1 = new MusicList("消愁",R.drawable.xiaochou);
            musicList.add(one1);
        }
    }
    private void initMusic(){
            Music three = new Music("南山南",R.drawable.nanshannan);
        musiclist.add(three);
        Music one = new Music("消愁",R.drawable.xiaochou);
        musiclist.add(one);
        Music two = new Music("论坛",R.drawable.luntan);
        musiclist.add(two);
        Music three1 = new Music("南山南",R.drawable.nanshannan);
        musiclist.add(three1);
        Music one1 = new Music("发现",R.drawable.faxian);
        musiclist.add(one1);
        Music two1 = new Music("消愁",R.drawable.xiaochou);
        musiclist.add(two1);
        Music three2 = new Music("南山南",R.drawable.nanshannan);
        musiclist.add(three2);
        Music one3 = new Music("发现",R.drawable.faxian);
        musiclist.add(one3);
        Music two4 = new Music("论坛",R.drawable.luntan);
        musiclist.add(two4);
    }
}