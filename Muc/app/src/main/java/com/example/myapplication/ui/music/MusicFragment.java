package com.example.myapplication.ui.music;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.R;
import com.example.myapplication.Util.HttpUtil;
import com.example.myapplication.bean.MusicList;
import com.example.myapplication.bean_new.InteractionEntity.ResultEntity;
import com.example.myapplication.bean_new.Song;
import com.example.myapplication.bean_new.SongList;
import com.example.myapplication.bean_new.User;
import com.example.myapplication.ui.home.SearchActivity;
import com.example.myapplication.ui.music.play.ListplayActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MusicFragment extends Fragment {         //音乐（中间那颗按钮）的碎片
    private List<SongList> musicLists1 = new ArrayList<>();
    private List<SongList> musicLists2 = new ArrayList<>();
    private List<Song> songList = new ArrayList<>();
    private MusicViewModel musicViewModel;

	private SharedPreferences sp;
	private SharedPreferences.Editor editor;
	private String Search = new String();

	public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        musicViewModel = ViewModelProviders.of(this).get(MusicViewModel.class);
        View root = inflater.inflate(R.layout.fragment_music, container, false);

        TextView recent_play = (TextView) root.findViewById(R.id.recent_play_text);
        recent_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"you clicked recent_play",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView play = (ImageView) root.findViewById(R.id.title_bofang);
        play.setOnClickListener(new View.OnClickListener() {//跳转到播放音乐界面
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
        adapter1.OnRecycleItemClickListener(new ListAdapter.OnRecycleItemClickListener() {//从 我的歌单 里的歌单跳转到 查看歌单所有歌曲里
            @Override
            public void OnRecycleItemClickListener(final int position) {
//				sp = getActivity().getSharedPreferences("test",Context.MODE_PRIVATE);
//				editor =  sp.edit();
//				new Thread(new Runnable(){
//					@Override
//					public void run() {
//						try{
//							String body = JSON.toJSONString(musicLists1.get(position));
//							String res = new String();
//							res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/song/getSongInSongList", body, "UTF-8");
//							ResultEntity result = JSON.parseObject(res, ResultEntity.class);
//							if (result.getState() == true) {
//								editor.putString("SongList'sSong",res);
//								editor.commit();
//								for(int i=0;i<((JSONArray)(result.getObject())).size();i++){
//									songList.add(((JSONObject)(((JSONArray)(result.getObject())).get(i))).toJavaObject(Song.class));
//								}
//							} else {
//								Looper.prepare();
//								Toast.makeText(getActivity(), "获得歌曲失败", Toast.LENGTH_SHORT).show();
//								Looper.loop();
//								editor.putString("SongList'sSong",res);
//								editor.commit();
//							}
//						}catch (Exception e){
//							Looper.prepare();
//							Toast.makeText(getActivity(), "连接失败", Toast.LENGTH_SHORT).show();
//							Looper.loop();
//						}
//					}
//				}).start();
                Intent intent=new Intent(getActivity(),SonglistActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("songlist", musicLists1.get(position));
				intent.putExtras(bundle);
//                intent.putExtra("Song_list",(Serializable)musicLists1.get(position));
//                intent.putExtra("Song_List",(Serializable) songList);
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

		ImageView imageView=root.findViewById(R.id.item_menu1);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		final SearchView mSearchView = (SearchView) root.findViewById(R.id.title_sousuo);
		mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			// 当点击搜索按钮时触发该方法
			@Override
			public boolean onQueryTextSubmit(String query) {
				if (!TextUtils.isEmpty(query)){
					Intent intent = new Intent(getActivity(), SearchActivity.class);
					intent.putExtra("Search",query);
					startActivity(intent);
				}else{
					return false;
				}
				return false;
			}
			// 当搜索内容改变时触发该方法
			@Override
			public boolean onQueryTextChange(String newText) {
				return false;
			}
		});
        return root;
    }

	private void onBackPressed() {//创建歌单
		LayoutInflater inflater = getLayoutInflater();
		ViewGroup container = null;
		View root = inflater.inflate(R.layout.fragment_music, container, false);
		final View layout = inflater.inflate(R.layout.dialog, container, false);
		new AlertDialog.Builder(getActivity()).setTitle("创建歌单").setView(layout)
				.setNegativeButton( "取消",null )
				.setPositiveButton( "创建", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {//创建歌单
						TextView editText=layout.findViewById(R.id.etname);
						String inputText=editText.getText().toString();
						sp = getActivity().getSharedPreferences("test", Context.MODE_PRIVATE);//初始化
						EditText songlistname=layout.findViewById(R.id.etname);
						final SongList songlist=new SongList();
						songlist.setName_SL(songlistname.getText().toString());
						songlist.setId_User(sp.getString("name",""));
						musicLists1.add(songlist);
						new Thread(new Runnable(){
							@Override
							public void run() {
								try {
									String body=JSON.toJSONString(songlist);

									String res=new String();
									res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/songList/create",body,"UTF-8");
									ResultEntity result = JSON.parseObject(res, ResultEntity.class);
									if(result.getState()==true){
										Looper.prepare();
										Toast.makeText(getActivity(), "创建成功", Toast.LENGTH_SHORT).show();
										Looper.loop();
									}
									else{
										Looper.prepare();
										Toast.makeText(getActivity(), "创建失败", Toast.LENGTH_SHORT).show();
										Looper.loop();
									}
								}catch (Exception e){
									Looper.prepare();
									Toast.makeText(getActivity(), "连接失败", Toast.LENGTH_SHORT).show();
									Looper.loop();
								}

							}
						}).start();

					}
				}).show();

	}


    private void initList1(){//我创建的歌单

		sp = getActivity().getSharedPreferences("test", Context.MODE_PRIVATE);//初始化
		String res=sp.getString("MySongList","");
		if(res.isEmpty()){
			return;
		}
		ResultEntity result = JSON.parseObject(res, ResultEntity.class);
		if(result.getState()==true){
			for(int i = 0; i<((JSONArray)(result.getObject())).size(); i++){
				musicLists1.add(((JSONObject)(((JSONArray)(result.getObject())).get(i))).toJavaObject(SongList.class));
			}
		}
		else{
			Toast.makeText(getActivity(), "获得歌曲失败", Toast.LENGTH_SHORT).show();
		}
//        for(int i=0;i<2;i++){
//            MusicList three = new MusicList("南山南",R.drawable.nanshannan,100,"马頔");
//            musicLists1.add(three);
//            MusicList one = new MusicList("消愁",R.drawable.xiaochou,100,"毛不易");
//            musicLists1.add(one);
//        }
    }

    private void initList2(){//我收藏的歌单
//        for(int i=0;i<2;i++){
//            MusicList two = new MusicList("南山南",R.drawable.nanshannan,100,"假数据");
//            musicLists2.add(two);
//            MusicList one1 = new MusicList("消愁",R.drawable.xiaochou,100,"假数据");
//            musicLists2.add(one1);
//        }
    }

}