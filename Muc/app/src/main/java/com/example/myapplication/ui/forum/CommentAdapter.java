package com.example.myapplication.ui.forum;

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
import com.example.myapplication.bean.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{ //评论（安利墙）适配器
    private List<Comment> mCommentList;
    private CommentAdapter.OnRecycleItemClickListener onRecycleItemClickListener=null;

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
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        final CommentAdapter.ViewHolder holder = new CommentAdapter.ViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, final int position){
        Comment comment = mCommentList.get(position);
        comment.getSong().setIconFile_Song(comment.getSong().getIconFile_Song().substring(comment.getSong().getIconFile_Song().indexOf(',')+1));
        byte[] data= Base64Util.decode(comment.getSong().getIconFile_Song());
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

    public  void  OnRecycleItemClickListener(CommentAdapter.OnRecycleItemClickListener v){
        onRecycleItemClickListener = v;
    }

    public interface OnRecycleItemClickListener{
        void OnRecycleItemClickListener(int position);
    }
}
