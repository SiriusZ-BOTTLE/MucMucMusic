package com.example.myapplication.ui.music.play;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import com.example.myapplication.bean.Music;
import com.example.myapplication.bean_new.InteractionEntity.ResultEntity;
import com.example.myapplication.bean_new.Song;
import com.example.myapplication.bean_new.User;
import com.example.myapplication.bean.Comment;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {
    private List<Comment> commentList = new ArrayList<>();
    private List<com.example.myapplication.bean_new.Comment> mCommentList = new ArrayList<>();
    private List<Music> musiclist = new ArrayList<>();
    private Music music = new Music();
    private SharedPreferences sp;
    private int Id;
    private ResultEntity result;
    private CommentshowAdapter adapter;
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
        adapter = new CommentshowAdapter(commentList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.OnRecycleItemClickListener(new CommentshowAdapter.OnRecycleItemClickListener() {
            @Override
            public void OnRecycleItemClickListener(int position) {
                Toast.makeText(CommentActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
                try {
                    ThreadLikes thread1=new ThreadLikes(commentList.get(position),position);
                    thread1.start();
                    thread1.join();
                    Log.d("id",String.valueOf(commentList.get(position).getLikes()));
                }catch (Exception e){
                }
            }
    });

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
                    final User user = new User();
                    sp = getSharedPreferences("test",Context.MODE_PRIVATE);
                    user.setNickname_User(sp.getString("nickname",""));
                    user.setIconFile_User(sp.getString("iconFile_User",""));
                    user.setId_User(sp.getString("name",""));
                    final Song song = new Song();
                    Intent intent = getIntent();
                    Id = intent.getIntExtra("song_id", 0);
                    song.setId_Song(Id);
                    final com.example.myapplication.bean_new.Comment cc = new com.example.myapplication.bean_new.Comment();
                    cc.setContent_Comment(detail);
                    cc.setScore_Comment((int) score);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String res = new String();
                            Map<String,Object> map = new HashMap();
                            map.put("comment",cc);
                            map.put("user",user);
                            map.put("song",song);
                            String body = JSON.toJSONString(map);
                            HttpUtil.sendPostUrl("http://47.97.202.142:8082/comment/write", body, "UTF-8");
                            Looper.prepare();
                            Toast.makeText(CommentActivity.this,"发表成功",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }).start();
                    comment.setUser(user);
                    commentList.add(comment);
                }
                break;
            default:
        }

    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("getComment", "handleMessage: " );
                    break;
                case 2:
                    Log.d("getUser", "handleMessage: " );
                    break;
                case 3:
                    adapter.notifyDataSetChanged();
                default:
                    break;
            }
        }
    };
    private class  ThreadgetComment extends Thread {
        @Override
        public void run() {
            String res = new String();
            Song song = new Song();
            Intent intent = getIntent();
            Id = intent.getIntExtra("song_id", 0);
            song.setId_Song(Id);
            String body = JSON.toJSONString(song);
            res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/comment/getCommentsUnderSong", body, "UTF-8");
            ResultEntity result = JSON.parseObject(res, ResultEntity.class);
            if (result.getState() == true) {
                for (int i = 0; i < ((JSONArray)(result.getObject())).size(); i++) {
                    com.example.myapplication.bean_new.Comment c = ((JSONObject) (((JSONArray) (result.getObject())).get(i))).toJavaObject(com.example.myapplication.bean_new.Comment.class);
                    Comment comment = new Comment();
                    comment.setScore(c.getScore_Comment());
                    comment.setDetails(c.getContent_Comment());
                    comment.setLikes(c.getLikes_Comment());
                    comment.setReleaseTime_Comment(c.getReleaseTime_Comment());
                    comment.setId_Comment(c.getId_Comment());
                    User user = new User();
                    user.setId_User(c.getId_User());
                    comment.setUser(user);
                    commentList.add(comment);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            }
        }
    }
    private class ThreadgetUser extends Thread {
        private User user;
        private int i;
        public ThreadgetUser(User u,int position){
            this.user = u;
            this.i = position;
        }
        @Override
        public void run() {
            String body = JSON.toJSONString(user);
            String res = new String();
            res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/user/get", body, "UTF-8");
            ResultEntity result2 = JSON.parseObject(res, ResultEntity.class);
            User u = ((JSONObject) result2.getObject()).toJavaObject(User.class);
            commentList.get(i).setUser(u);
            Message message = new Message();
            message.what = 2;
            handler.sendMessage(message);
        }
    }
    private class  ThreadLikes extends Thread {
        private int i;
        private Comment comment;
        public ThreadLikes(Comment comment,int position){
            this.i = position;
            this.comment = comment;
        }
        @Override
        public void run() {
            com.example.myapplication.bean_new.Comment cc = new com.example.myapplication.bean_new.Comment();
            cc.setId_Comment(comment.getId_Comment());
            cc.setLikes_Comment(comment.getLikes());
            String body = JSON.toJSONString(cc);
            String res = new String();
            res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/comment/likes", body, "UTF-8");
            ResultEntity result2 = JSON.parseObject(res, ResultEntity.class);
//            com.example.myapplication.bean_new.Comment c = ((JSONObject) result2.getObject()).toJavaObject(com.example.myapplication.bean_new.Comment.class);
            commentList.get(i).setLikes(comment.getLikes()+1);
            Message message = new Message();
            message.what = 3;
            handler.sendMessage(message);
        }
    }
    private void initComment(){
        try {
            ThreadgetComment thread1=new ThreadgetComment();
            thread1.start();
            thread1.join();
            for(int i=0;i<commentList.size();i++) {
                ThreadgetUser thread2=new ThreadgetUser(commentList.get(i).getUser(),i);
                thread2.start();
                thread2.join();
            }
        }catch (Exception e){
        }
    }
}

class CommentshowAdapter extends RecyclerView.Adapter<CommentshowAdapter.ViewHolder>{
    private List<Comment> commentList;
    private CommentshowAdapter.OnRecycleItemClickListener onRecycleItemClickListener=null;
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
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        Comment comment = commentList.get(position);
        byte [] b = Base64Util.decode(comment.getUser().getIconFile_User());
        holder.userIcon.setImageBitmap(BitmapFactory.decodeByteArray(b,0,b.length));
        holder.userName.setText(comment.getUser().getNickname_User());
        holder.commentDetails.setText(comment.getDetails());
        holder.ratingBar.setRating(comment.getScore());
        Log.d("num",String.valueOf((int)comment.getScore()));
        holder.likeNum.setText(String.valueOf((int)comment.getLikes()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String releaseTime = sdf.format(comment.getReleaseTime_Comment());
        holder.releaseTime.setText(releaseTime);
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRecycleItemClickListener!=null)
                    onRecycleItemClickListener.OnRecycleItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount(){
        return commentList.size();
    }

    public  void  OnRecycleItemClickListener(CommentshowAdapter.OnRecycleItemClickListener v){
        onRecycleItemClickListener = v;
    }

    public interface OnRecycleItemClickListener{
        void OnRecycleItemClickListener(int position);
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
