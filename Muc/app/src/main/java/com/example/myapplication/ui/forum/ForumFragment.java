package com.example.myapplication.ui.forum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.fastjson.JSON;
import com.example.myapplication.R;
import com.example.myapplication.Util.HttpUtil;
import com.example.myapplication.bean.Comment;
import com.example.myapplication.bean.Music;
import com.example.myapplication.bean_new.InteractionEntity.ResultEntity;
import com.example.myapplication.bean_new.Song;
import com.example.myapplication.ui.home.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class ForumFragment extends Fragment {
    private ForumViewModel forumViewModel;
    private List<Comment> commentList = new ArrayList<>();
    private List<Music> musiclist = new ArrayList<>();
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forumViewModel =
                ViewModelProviders.of( this).get(ForumViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum, container, false);

        initComment();
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_forum);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CommentAdapter adapter = new CommentAdapter(commentList);
        recyclerView.setAdapter(adapter);
        SearchView mSearchView = (SearchView) root.findViewById(R.id.title_sousuo);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(final String query) {
                if (!TextUtils.isEmpty(query)){
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("Search",query);
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            try {
                                sp = getActivity().getSharedPreferences("test", Context.MODE_PRIVATE);
                                editor =  sp.edit();
                                Song song=new Song();
                                song.setName_Song(query);
                                String body= JSON.toJSONString(song);
                                String res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/song/search",body,"UTF-8");
                                ResultEntity result = JSON.parseObject(res, ResultEntity.class);
                                if(result.getState()==true){
                                    editor.putString("search_result",res);
                                    editor.commit();
                                }
                                else{
                                    Looper.prepare();
                                    Toast.makeText(getActivity(), "搜索失败", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                            }catch (Exception e){
                                Looper.prepare();
                                Toast.makeText(getActivity(), "连接失败", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }

                        }
                    }).start();


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

    private void initMusic(){
        Music three = new Music("南山南",R.drawable.nanshannan,"数据");
        musiclist.add(three);
        Music one = new Music("消愁",R.drawable.xiaochou,"数据");
        musiclist.add(one);
        Music two = new Music("论坛",R.drawable.luntan,"数据");
        musiclist.add(two);
        Music one1 = new Music("发现",R.drawable.faxian,"数据");
        musiclist.add(one1);

    }
    private void initComment(){
        initMusic();
        for(int i=0;i<3;i++){
            Comment one = new Comment("这是一首很好听的歌，我非常喜欢","余阳",musiclist.get(0));
            one.setScore(3);
            commentList.add(one);
            Comment two = new Comment("这是一首很好听的歌，我非常喜欢,这是一首很好听的歌，我非常喜欢,这是一首很好听的歌，我非常喜欢,这是一首很好听的歌，我非常喜欢","余阳",musiclist.get(2));
            commentList.add(two);
            one.setScore(1);
            Comment three = new Comment("这是一首很好听的歌，我非常喜欢,这是一首很好听的歌，我非常喜欢","南歌w",musiclist.get(2));
            commentList.add(three);
            one.setScore(4);
            Comment four = new Comment("这是一首很好听的歌，我非常喜欢，这是一首很好听的歌，我非常喜欢，这是一首很好听的歌，我非常喜欢","余阳w",musiclist.get(3));
            commentList.add(four);
            one.setScore(5);
        }


    }

}
