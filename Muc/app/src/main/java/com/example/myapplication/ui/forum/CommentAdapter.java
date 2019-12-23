package com.example.myapplication.ui.forum;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Util.Base64Util;
import com.example.myapplication.Util.MusicUtils;
import com.example.myapplication.bean.Comment;
import com.example.myapplication.bean_new.Song;
import com.example.myapplication.ui.music.play.ListplayActivity;

import java.util.HashSet;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{ //评论（安利墙）适配器
    private List<Comment> mCommentList;
    private OnRecycleItemClickListener onRecycleItemClickListener=null;

    static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView musicImage;
        TextView musicName;
        TextView commentDetails;
        ImageView commentDianzan;
        RatingBar ratingBar;
        TextView commentFrom;
        TextView authorName;
        View commentView;
        TextView num;

        public  ViewHolder(View view){
            super(view);
            commentView = view;
            musicImage = (ImageView) view.findViewById(R.id.comment_musicimage);
            musicName = (TextView) view.findViewById(R.id.comment_musicname);
            commentDetails = (TextView) view.findViewById(R.id.comment_details);
            commentDianzan = (ImageView) view.findViewById(R.id.comment_dianzan);
            commentFrom = (TextView) view.findViewById(R.id.comment_from);
            authorName = (TextView) view.findViewById(R.id.comment_authorname);
            ratingBar = (RatingBar) view.findViewById(R.id.forum_ratingbar);
            num = (TextView) view.findViewById(R.id.comment_num);
        }
    }

    public CommentAdapter(List<Comment> CommentList){
        mCommentList = CommentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        final Comment comment = mCommentList.get(position);
        comment.getSong().setIconFile_Song(comment.getSong().getIconFile_Song().substring(comment.getSong().getIconFile_Song().indexOf(',')+1));
        byte[] data= Base64Util.decode(comment.getSong().getIconFile_Song());
        holder.commentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song music = comment.getSong();
                Intent intent = new Intent(v.getContext(), ListplayActivity.class);
                MusicUtils.list.add(music);
                HashSet h = new HashSet(MusicUtils.list);
                MusicUtils.list.clear();
                MusicUtils.list.addAll(h);
//                MusicUtils.list=new ArrayList<Song>(new LinkedHashSet<Song>(MusicUtils.list));//删去重复

                v.getContext().startActivity(intent);
            }
        });
        holder.musicImage.setImageBitmap(BitmapFactory.decodeByteArray(data,0,data.length));
        holder.musicName.setText(comment.getSong().getName_Song());
        holder.authorName.setText(comment.getUser().getNickname_User());
        holder.commentDetails.setText(comment.getDetails());
        holder.ratingBar.setRating(comment.getScore());
        holder.num.setText(String.valueOf(comment.getLikes()));
        holder.commentDianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRecycleItemClickListener!=null)
                    onRecycleItemClickListener.OnRecycleItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount(){
        return mCommentList.size();
    }

    public  void  OnRecycleItemClickListener(OnRecycleItemClickListener v){
        onRecycleItemClickListener = v;
    }

    public interface OnRecycleItemClickListener{
        void OnRecycleItemClickListener(int position);
    }
}
