package com.example.myapplication.ui.found;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.Category;

import java.util.List;

public class FoundRecyclerViewAdapter extends RecyclerView.Adapter<FoundRecyclerViewAdapter.FoundRecyclerViewHolder> {
    private List<Category> mCategoryList;
    private Context mContext;

    static class FoundRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView categorytitle;
        TextView foundmore;
        RecyclerView recyclerView;

        public FoundRecyclerViewHolder(View view) {
            super(view);
            categorytitle = (TextView) view.findViewById(R.id.recycler_found_title);
            foundmore = (TextView) view.findViewById(R.id.recycler_found_more);
            recyclerView = (RecyclerView) view.findViewById(R.id.gridrecycler_found);
        }
    }

    public FoundRecyclerViewAdapter(List<Category> categorylist, Context context) {
        mCategoryList= categorylist;
        mContext = context;
    }

    @Override
    public FoundRecyclerViewHolder onCreateViewHolder( ViewGroup viewGroup,int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_found, viewGroup, false);
        final FoundRecyclerViewHolder holder = new FoundRecyclerViewHolder(view);
        holder.foundmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Toast.makeText(v.getContext(),"you clicked text foundmore",Toast.LENGTH_SHORT).show();
            }
        });
        return  holder;
    }


    @Override
    public void onBindViewHolder(FoundRecyclerViewHolder viewHolder, int position) {
        Category category = mCategoryList.get(position);
        viewHolder.categorytitle.setText(category.getName());

        ChildAdapter childAdapter = (ChildAdapter) viewHolder.recyclerView.getAdapter();

        if(childAdapter==null){
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            manager.setAutoMeasureEnabled(true);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolder.recyclerView.setLayoutManager(manager);
            viewHolder.recyclerView.setAdapter(new ChildAdapter(category.getMusicList(), position));
            }else{
                    childAdapter.setData(category.getMusicList());
                    childAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }
}
