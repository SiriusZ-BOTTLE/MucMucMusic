package com.example.myapplication.ui.found;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.Category;


import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> mCategoryList;


    static class  ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        View caregoryView;

        public  ViewHolder(View view){
            super(view);
            caregoryView = view;
            categoryName = (TextView) view.findViewById(R.id.category_name);

        }
    }

    public CategoryAdapter(List<Category> CategoryList){
        mCategoryList = CategoryList;
    }
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent ,false);
        final CategoryAdapter.ViewHolder holder = new CategoryAdapter.ViewHolder(view);
        holder.caregoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Category category = mCategoryList.get(position);
                Toast.makeText(v.getContext(),"you clicked view "+category.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Category category = mCategoryList.get(position);
                Toast.makeText(v.getContext(),"you clicked text "+category.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position){
        Category category  = mCategoryList.get(position);
        holder.categoryName.setText(category.getName());
    }

    @Override
    public int getItemCount(){
        return mCategoryList.size();
    }
}
