package com.example.myapplication.ui.music;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.MusicList;
import com.example.myapplication.ui.music.play.ListplayActivity;

import java.util.ArrayList;
import java.util.List;

public class MusicFragment extends Fragment {
    private List<MusicList> musicLists1 = new ArrayList<>();
    private List<MusicList> musicLists2 = new ArrayList<>();
    private MusicViewModel musicViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        musicViewModel =
                ViewModelProviders.of(this).get(MusicViewModel.class);
        View root = inflater.inflate(R.layout.fragment_music, container, false);

        TextView recent_play = (TextView) root.findViewById(R.id.recent_play_text);
        recent_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"you clicked recent_play",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView play = (ImageView) root.findViewById(R.id.title_bofang);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListplayActivity.class);
                startActivity(intent);
            }
        });
        initList1();
        RecyclerView recyclerView1 = (RecyclerView) root.findViewById(R.id.My_create_recycler);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        recyclerView1.setLayoutManager(layoutManager1);
        ListAdapter adapter1 = new ListAdapter(musicLists1);
        recyclerView1.setAdapter(adapter1);
        adapter1.OnRecycleItemClickListener(new ListAdapter.OnRecycleItemClickListener() {
            @Override
            public void OnRecycleItemClickListener(int position) {
                Intent intent=new Intent(getActivity(),SonglistActivity.class);
                startActivity(intent);
            }
        });

        initList2();
        RecyclerView recyclerView2 = (RecyclerView) root.findViewById(R.id.My_collect_recycler);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(layoutManager2);
        ListAdapter adapter2 = new ListAdapter(musicLists2);
        recyclerView2.setAdapter(adapter2);
        adapter2.OnRecycleItemClickListener(new ListAdapter.OnRecycleItemClickListener() {
            @Override
            public void OnRecycleItemClickListener(int position) {
                Intent intent=new Intent(getActivity(),SonglistActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
    private void initList1(){
        for(int i=0;i<2;i++){
            MusicList three = new MusicList("南山南",R.drawable.nanshannan,100,"马頔");
            musicLists1.add(three);
            MusicList one = new MusicList("消愁",R.drawable.xiaochou,100,"毛不易");
            musicLists1.add(one);
        }
    }

    private void initList2(){
        for(int i=0;i<2;i++){
            MusicList two = new MusicList("南山南",R.drawable.nanshannan,100,"假数据");
            musicLists2.add(two);
            MusicList one1 = new MusicList("消愁",R.drawable.xiaochou,100,"假数据");
            musicLists2.add(one1);
        }
    }

}