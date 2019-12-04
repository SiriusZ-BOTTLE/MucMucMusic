package com.example.myapplication.ui.music.play;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.myapplication.R;
import com.example.myapplication.bean.Comment;
import com.example.myapplication.bean.Music;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    private List<Comment> commentList = new ArrayList<>();
    private List<Music> musiclist = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Toolbar toolbar = findViewById(R.id.tool_bar_comment);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initComment();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_comment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        CommentshowAdapter adapter = new CommentshowAdapter(commentList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        TextView add = (TextView) findViewById(R.id.comment_add2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentActivity.this,AddCommentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initMusic(){
        Music three = new Music("南山南", R.drawable.nanshannan,"数据");
        musiclist.add(three);
        Music one = new Music("消愁", R.drawable.xiaochou,"数据");
        musiclist.add(one);
        Music two = new Music("论坛", R.drawable.luntan,"数据");
        musiclist.add(two);
        Music one1 = new Music("发现", R.drawable.faxian,"数据");
        musiclist.add(one1);

    }
    private void initComment(){
        initMusic();
        for(int i=0;i<3;i++){
            com.example.myapplication.bean.Comment one = new com.example.myapplication.bean.Comment("这是一首很好听的歌，我非常喜欢","余阳",musiclist.get(0));
            commentList.add(one);
            com.example.myapplication.bean.Comment two = new com.example.myapplication.bean.Comment("这是一首很好听的歌，我非常喜欢,这是一首很好听的歌，我非常喜欢,这是一首很好听的歌，我非常喜欢,这是一首很好听的歌，我非常喜欢","余阳",musiclist.get(2));
            commentList.add(two);
            com.example.myapplication.bean.Comment three = new com.example.myapplication.bean.Comment("这是一首很好听的歌，我非常喜欢,这是一首很好听的歌，我非常喜欢","南歌w",musiclist.get(2));
            commentList.add(three);
            com.example.myapplication.bean.Comment four = new com.example.myapplication.bean.Comment("这是一首很好听的歌，我非常喜欢，这是一首很好听的歌，我非常喜欢，这是一首很好听的歌，我非常喜欢","余阳w",musiclist.get(3));
            commentList.add(four);
        }
    }
}

class CommentshowAdapter extends RecyclerView.Adapter<CommentshowAdapter.ViewHolder>{
    private List<Comment> commentList;
    static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView userIcon;
        TextView userName;
        TextView releaseTime;
        TextView commentDetails;
        TextView likeNum;
        ImageView like;
        View commentView;

        public  ViewHolder(View view){
            super(view);
            commentView = view;
            userIcon = (ImageView) view.findViewById(R.id.userimage);
            userName = (TextView) view.findViewById(R.id.username);
            releaseTime = (TextView) view.findViewById(R.id.releasetime);
            like = (ImageView) view.findViewById(R.id.comment_like);
            likeNum = (TextView) view.findViewById(R.id.comment_likenum);
            commentDetails = (TextView) view.findViewById(R.id.comment_detail);
        }
    }

    public CommentshowAdapter(List<Comment> CommentList){
       commentList = CommentList;
    }
    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_comment, parent ,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.commentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Comment comment = commentList.get(position);
                Toast.makeText(v.getContext(),"you clicked view "+comment.getAuthor_name(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Comment comment = commentList.get(position);
                Toast.makeText(v.getContext(),"you favorited this song "+comment.getAuthor_name(),Toast.LENGTH_SHORT).show();
            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Comment comment = commentList.get(position);
        holder.userIcon.setImageResource(comment.getMusic().getImageId());
        holder.userName.setText(comment.getAuthor_name());
        holder.commentDetails.setText(comment.getDetails());
        holder.likeNum.setText("1000");
        holder.releaseTime.setText("2019年12月2日");
    }

    @Override
    public int getItemCount(){
        return commentList.size();
    }
}
