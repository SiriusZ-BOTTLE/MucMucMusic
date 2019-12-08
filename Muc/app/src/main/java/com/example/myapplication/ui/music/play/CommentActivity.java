package com.example.myapplication.ui.music.play;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import com.example.myapplication.bean.Music;
import com.example.myapplication.bean_new.InteractionEntity.ResultEntity;
import com.example.myapplication.bean_new.Song;
import com.example.myapplication.bean_new.User;
import com.example.myapplication.bean.Comment;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    private List<Comment> commentList = new ArrayList<>();
    private List<com.example.myapplication.bean_new.Comment> mCommentList = new ArrayList<>();
    private List<Music> musiclist = new ArrayList<>();
    private Music music = new Music();
    private SharedPreferences sp;
    private int Id;
    private ResultEntity result;
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

        Intent it = getIntent();
        String songname = it.getStringExtra("song_name");
        String songsinger = it.getStringExtra("song_singer");
        String songimage = it.getStringExtra("song_icon");
        TextView s_name = (TextView) findViewById(R.id.comment_songname);
        TextView s_singer = (TextView) findViewById(R.id.comment_songsinger);
        ImageView s_icon = (ImageView) findViewById(R.id.song_icon);
        byte [] b = Base64Util.decode(songimage);
        s_icon.setImageBitmap(BitmapFactory.decodeByteArray(b,0,b.length));
        s_name.setText(songname);
        s_singer.setText(songsinger);
        music.setAuthor(songsinger);
        music.setName(songname);
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
                startActivityForResult(intent,3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        switch (requestCode){
            case 3:
                if(resultCode == RESULT_OK){
                    String detail = data.getStringExtra("details");
                    float score = data.getFloatExtra("score",0);
                    Comment comment = new Comment();
                    comment.setScore(score);
                    comment.setDetails(detail);
                    User user = new User();
                    sp = getSharedPreferences("test",Context.MODE_PRIVATE);
                    user.setNickname_User(sp.getString("nickname",""));
                    user.setIconFile_User(sp.getString("iconFile_User",""));
                    comment.setUser(user);
                    commentList.add(comment);
                }
                break;
            default:
        }

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
        new Thread(new Runnable(){
            @Override
            public void run() {
                String res = new String();
                Song song = new Song();
                Intent intent = getIntent();
                Id = intent.getIntExtra("song_id", 0);
                song.setId_Song(Id);
                try {
                    String body = JSON.toJSONString(song);
                    res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/comment/queryBySong", body, "UTF-8");
                    result = JSON.parseObject(res, ResultEntity.class);
                    if (result.getState() == true) {
                        for (int i = 0; i < 2; i++) {
                           com.example.myapplication.bean_new.Comment c = ((JSONObject) (((JSONArray) (result.getObject())).get(i))).toJavaObject(com.example.myapplication.bean_new.Comment.class);
                           Comment comment = new Comment();
                           comment.setScore(c.getScore_Comment());
                           comment.setDetails(c.getContent_Comment());
                           comment.setLikes(c.getLikes_Comment());
                           User user = new User();
                           sp = getSharedPreferences("test",Context.MODE_PRIVATE);
                           user.setNickname_User(sp.getString("nickname",""));
                           user.setIconFile_User(sp.getString("iconFile_User",""));
                           comment.setUser(user);
                           commentList.add(comment);
                        }
                    } else {
                        Toast.makeText(CommentActivity.this, "获得歌曲失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(CommentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }
}

class CommentshowAdapter extends RecyclerView.Adapter<CommentshowAdapter.ViewHolder>{
    private List<Comment> commentList;
    static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView userIcon;
        TextView userName;
        TextView releaseTime;
        TextView commentDetails;
        RatingBar ratingBar;
        TextView likeNum;
        ImageView like;
        View commentView;

        public  ViewHolder(View view){
            super(view);
            commentView = view;
            userIcon = (ImageView) view.findViewById(R.id.userimage);
            ratingBar = (RatingBar) view.findViewById(R.id.comment_ratingbar);
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
        byte [] b = Base64Util.decode(comment.getUser().getIconFile_User());
        holder.userIcon.setImageBitmap(BitmapFactory.decodeByteArray(b,0,b.length));
        holder.userName.setText(comment.getUser().getNickname_User());
        holder.commentDetails.setText(comment.getDetails());
        holder.ratingBar.setRating(comment.getScore());
        holder.likeNum.setText(comment.getLikes());

        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);
        holder.releaseTime.setText(createdate);
    }

    @Override
    public int getItemCount(){
        return commentList.size();
    }
}
