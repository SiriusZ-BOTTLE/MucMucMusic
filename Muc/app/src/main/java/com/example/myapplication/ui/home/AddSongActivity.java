package com.example.myapplication.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean_new.InteractionEntity.songlistnew;
import com.example.myapplication.ui.found.TagActivity;

import java.util.ArrayList;
import java.util.List;

//import com.example.myapplication.bean_new.SongList;
//import com.example.myapplication.ui.found.AddSongActivity.AddSongAdapter;

public class AddSongActivity extends AppCompatActivity {
    private List<songlistnew> songLists = new ArrayList<>();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsong);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null) {
            actionBar.hide();
        }
        Toolbar toolbar = findViewById(R.id.tool_addsong);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initList1();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_addsongdetails);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        AddSongAdapter adapter = new AddSongAdapter(songLists);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }



    private void initList1() {
            songlistnew three = new songlistnew("南山南", R.drawable.nanshannan);
            songLists.add(three);
        songlistnew one = new songlistnew("消愁", R.drawable.xiaochou);
        songLists.add(one);

    }

    class AddSongAdapter extends RecyclerView.Adapter<AddSongAdapter.ViewHolder> {
        private List<songlistnew> addsongList;


        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView addsongimage;
            TextView addsongtName;
            View addsongView;

            public ViewHolder(View view) {
                super(view);
                addsongimage=(ImageView)view.findViewById(R.id.addsonglist_image) ;
                addsongView = view;
                addsongtName = (TextView) view.findViewById(R.id.addsonglist_name);

            }
        }

        public AddSongAdapter(List<songlistnew> AddsongList) {
            addsongList = AddsongList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addsonglist,parent, false);
            final ViewHolder holder = new ViewHolder(view);
            holder.addsongView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
//            holder.addsonglistImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getAdapterPosition();
//                    SongList music = addsongList.get(position);
//                    Toast.makeText(v.getContext(), "you clicked image " + music.getName_SL(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            View root = inflater.inflate(R.layout.activity_addsong, container, false);
//    //        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
//            ListAdapter adapter1 = new ListAdapter(musicLists1);
//            adapter1.OnRecycleItemClickListener(new ListAdapter.OnRecycleItemClickListener() {
//                @Override
//                public void OnRecycleItemClickListener(int position) {
//                    onBackPressed();
//                }
//            });

            return holder;
        }
        public void onBackPressed() {
            new AlertDialog.Builder(AddSongActivity.this)
                    .setTitle("确认对话框")
                    .setMessage("确定添加进该歌单?")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // finish();
                            Intent intent = new Intent(AddSongActivity.this, TagActivity.class);
                            startActivity(intent);
                        }
                    })
                    .show();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            songlistnew addsonglist = addsongList.get(position);
            holder.addsongimage.setImageResource(addsonglist.getImageID());
    //        holder.addsonglistImage.setImageResource(AddSongAdapter.MusicIdtoImage.get(addsonglist.getId_SL()));
            holder.addsongtName.setText(addsonglist.getName());
        }

        @Override
        public int getItemCount() {
            return addsongList.size();
        }

    //    public class MusicIdtoImage {
    //    }
    }
}

