package com.example.myapplication.ui.found;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.Music;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder>{
        private List<Music> musicList;
        private int parentIndex;

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        ImageView musicImage;
        TextView musicName;
        View mContentView;

        public ChildViewHolder(View view) {
            super(view);
            musicImage = (ImageView) view.findViewById(R.id.gridrecycler_found_image);
            musicName = (TextView) view.findViewById(R.id.gridrecycler_found_name);
            mContentView = view;
        }
    }
        public ChildAdapter(List<Music> musiclist, int parentindex) {
            musicList = musiclist;
            parentIndex = parentindex;
        }

        @Override
        public ChildViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gridrecycler_found, viewGroup, false);
            final ChildViewHolder holder = new ChildViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ChildViewHolder holder,final int position) {
            Music music = musicList.get(position);
            holder.musicImage.setImageResource(music.getImageId());
            holder.musicName.setText(music.getName());
            holder.mContentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "parent " + parentIndex + " child item " + position + " is clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return musicList.size();
        }

        public void setData(List<Music> musiclist) {
            this.musicList = musiclist;
        }
}
