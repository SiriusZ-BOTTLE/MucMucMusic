package com.example.myapplication.ui.forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.myapplication.R;
import com.example.myapplication.bean.Comment;
import com.example.myapplication.bean.Music;

import java.util.ArrayList;
import java.util.List;

public class ForumFragment extends Fragment {
    private ForumViewModel forumViewModel;
    private List<Comment> commentList = new ArrayList<>();
    private List<Music> musiclist = new ArrayList<>();
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
            commentList.add(one);
            Comment two = new Comment("这是一首很好听的歌，我非常喜欢,这是一首很好听的歌，我非常喜欢,这是一首很好听的歌，我非常喜欢,这是一首很好听的歌，我非常喜欢","余阳",musiclist.get(2));
            commentList.add(two);
            Comment three = new Comment("这是一首很好听的歌，我非常喜欢,这是一首很好听的歌，我非常喜欢","南歌w",musiclist.get(2));
            commentList.add(three);
            Comment four = new Comment("这是一首很好听的歌，我非常喜欢，这是一首很好听的歌，我非常喜欢，这是一首很好听的歌，我非常喜欢","余阳w",musiclist.get(3));
            commentList.add(four);
        }


    }

}
