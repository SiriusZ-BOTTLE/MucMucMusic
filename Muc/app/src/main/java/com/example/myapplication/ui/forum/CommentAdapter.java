package com.example.myapplication.ui.forum;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{ //评论（安利墙）适配器
    private List<Comment> mCommentList;


    static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView musicImage;
        TextView musicName;
        TextView commentDetails;
        ImageView commentDianzan;
        TextView commentFrom;
        TextView authorName;
        View commentView;

        public  ViewHolder(View view){
            super(view);
            commentView = view;
            musicImage = (ImageView) view.findViewById(R.id.comment_musicimage);
            musicName = (TextView) view.findViewById(R.id.comment_musicname);
            commentDetails = (TextView) view.findViewById(R.id.comment_details);
            commentDianzan = (ImageView) view.findViewById(R.id.comment_dianzan);
            commentFrom = (TextView) view.findViewById(R.id.comment_from);
            authorName = (TextView) view.findViewById(R.id.comment_authorname);
        }
    }

    public CommentAdapter(List<Comment> CommentList){
        mCommentList = CommentList;
    }

    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        final CommentAdapter.ViewHolder holder = new CommentAdapter.ViewHolder(view);
        holder.commentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 点击评论 将评论所属歌曲加载到播放歌曲链表（List<song>） 并播放
                int position = holder.getAdapterPosition();
                Comment comment = mCommentList.get(position);
                Toast.makeText(v.getContext(),"you clicked view "+comment.getMusic().getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.commentDianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// 评论点赞数量+1
                int position = holder.getAdapterPosition();
                Comment comment = mCommentList.get(position);
                Toast.makeText(v.getContext(),"you clicked Dianzan+1",Toast.LENGTH_SHORT).show();
            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position){
        Comment comment = mCommentList.get(position);
        holder.musicName.setText(comment.getMusic().getName());
        holder.musicImage.setImageResource(comment.getMusic().getImageId());
        holder.authorName.setText(comment.getAuthor_name());
        holder.commentDetails.setText(comment.getDetails());
    }

    @Override
    public int getItemCount(){
        return mCommentList.size();
    }
}
