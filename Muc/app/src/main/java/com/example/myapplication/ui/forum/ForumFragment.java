package com.example.myapplication.ui.forum;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.R;
import com.example.myapplication.bean.Comment;
import com.example.myapplication.bean.Music;
import com.example.myapplication.bean_new.InteractionEntity.ResultEntity;
import com.example.myapplication.bean_new.Song;
import com.example.myapplication.bean_new.User;
import com.example.myapplication.ui.home.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class ForumFragment extends Fragment {
    private ForumViewModel forumViewModel;
    private List<Comment> commentList = new ArrayList<>();
    private List<Music> musiclist = new ArrayList<>();
    private List<Song> songList = new ArrayList<>();
    private List<com.example.myapplication.bean_new.Comment> mCommentList = new ArrayList<>();
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private ResultEntity result;
    private CommentAdapter adapter;
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forumViewModel =
                ViewModelProviders.of( this).get(ForumViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum, container, false);

        //initComment();
        init();
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_forum);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CommentAdapter(commentList);
        adapter.OnRecycleItemClickListener(new CommentAdapter.OnRecycleItemClickListener() {
            @Override
            public void OnRecycleItemClickListener(int position) {
                try {
                    ThreadLikes thread1=new ThreadLikes(commentList.get(position),position);
                    thread1.start();
                    thread1.join();
                    Log.d("id",String.valueOf(commentList.get(position).getLikes()));
                }catch (Exception e){
                }
            }
        });
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
    private void init(){
        try {
            ThreadgetComment thread1=new ThreadgetComment();
            thread1.start();
            thread1.join();

            for(int i=0;i<commentList.size();i++) {
                ThreadgetSong thread2 = new ThreadgetSong(commentList.get(i).getSong(), i);
                thread2.start();
                thread2.join();
            }
            for(int i=0;i<commentList.size();i++) {
                ThreadgetUser thread3=new ThreadgetUser(commentList.get(i).getUser(),i);
                thread3.start();
                thread3.join();
            }
//            for(int j=0;j<commentList.size();j++){
//                Log.d("musicName",String.valueOf(commentList.get(j).getSong().getId_Song()));
//            }
        }catch (Exception e){
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
                    Log.d("getSong", "handleMessage: " );
                //finish();
                case 3:
                    Log.d("getUser", "handleMessage: " );
                    break;
                case 4:
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
            res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/comment/getRandom", "8", "UTF-8");
            ResultEntity result = JSON.parseObject(res, ResultEntity.class);
            if (result.getState() == true) {
                for (int i = 0; i < ((JSONArray)(result.getObject())).size(); i++) {
                    com.example.myapplication.bean_new.Comment c = ((JSONObject) (((JSONArray) (result.getObject())).get(i))).toJavaObject(com.example.myapplication.bean_new.Comment.class);
                    Comment comment = new Comment();
                    comment.setScore(c.getScore_Comment());
                    comment.setDetails(c.getContent_Comment());
                    comment.setLikes(c.getLikes_Comment());
                    User user = new User();
                    user.setId_User(c.getId_User());
                    Song song = new Song();
                    song.setId_Song(c.getId_Song());
                    comment.setSong(song);
                    comment.setUser(user);
                    commentList.add(comment);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            }
        }
    }

    private class  ThreadgetSong extends Thread {
        private Song song;
        private int i;
        public ThreadgetSong(Song s,int position){
            this.song = s;
            this.i = position;
        }
        @Override
        public void run() {
            String body = JSON.toJSONString(song);
            String res = new String();
            res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/song/get", body, "UTF-8");
            ResultEntity result2 = JSON.parseObject(res, ResultEntity.class);
            Song s = ((JSONObject) result2.getObject()).toJavaObject(Song.class);
            commentList.get(i).setSong(s);
            Message message = new Message();
            message.what = 2;
            handler.sendMessage(message);
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
            message.what = 3;
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
            message.what = 4;
            handler.sendMessage(message);
        }
    }
//    private void initForum(){
//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//                String res = new String();
//                try {
//                    res = HttpUtil.sendPostUrl("http://47.97.202.142:8082/comment/getRandom", "2", "UTF-8");
//                    result = JSON.parseObject(res, ResultEntity.class);
//                    if (result.getState() == true) {
//                        for (int i = 0; i < 2; i++) {
//                            com.example.myapplication.bean_new.Comment c = ((JSONObject) (((JSONArray) (result.getObject())).get(i))).toJavaObject(com.example.myapplication.bean_new.Comment.class);
//                            Comment comment = new Comment();
//                            comment.setScore(c.getScore_Comment());
//                            comment.setDetails(c.getContent_Comment());
//                            comment.setLikes(c.getLikes_Comment());
//                            User user = new User();
//                            user.setId_User(c.getId_User());
//                            Song song = new Song();
//                            song.setId_Song(c.getId_Song());
//
//                            String body = JSON.toJSONString(song);
//                            String res2 = new String();
//                            res2 = HttpUtil.sendPostUrl("http://47.97.202.142:8082/song/get", body, "UTF-8");
//                            ResultEntity result2 = JSON.parseObject(res2,ResultEntity.class);
//                            song = ((JSONObject)result2.getObject()).toJavaObject(Song.class);
//
//                            String body2 = JSON.toJSONString(user);
//                            String res3 = new String();
//                            res3 = HttpUtil.sendPostUrl("http://47.97.202.142:8082/user/get", body, "UTF-8");
//                            ResultEntity result3 = JSON.parseObject(res2,ResultEntity.class);
//                            user = ((JSONObject)result3.getObject()).toJavaObject(User.class);
//                            comment.setUser(user);
//                            comment.setSong(song);
//                            commentList.add(comment);
//                        }
//                    } else {
//                        Looper.prepare();
//                        Toast.makeText(getActivity(), "获得歌曲失败", Toast.LENGTH_SHORT).show();
//                        Looper.loop();
//                    }
//                } catch (Exception e) {
//                    Looper.prepare();
//                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                    Looper.loop();
//                }
//            }
//        }).start();
//    }
}
