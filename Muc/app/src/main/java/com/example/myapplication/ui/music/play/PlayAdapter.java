package com.example.myapplication.ui.music.play;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Util.GetDurationUtil;
import com.example.myapplication.Util.MusicUtils;
import com.example.myapplication.bean_new.Song;
import com.example.myapplication.ui.music.ListAdapter;

import java.util.List;

public class PlayAdapter extends BaseAdapter {
    private Context context;
    private List<Song> list;
    private int position_flag = 0;
    // 定义四种颜色，蓝色，紫色，绿色，红色
    private int myBlue = Color.argb(0xff, 0x00, 0xBF, 0xFF);
    private int myPurple = Color.argb(0xff, 0xFF, 0x00, 0xFF);
    private int myGreeen = Color.argb(0xff, 0x00, 0xFF, 0x00);
    private int myRed = Color.argb(0xff, 0xFF, 0x00, 0x00);

    private String Theme;

    private SharedPreferences sp;
    public PlayAdapter(ListplayActivity listplayActivity, List<Song> list) {
        this.context = listplayActivity;
        this.list = list;
    }

    public void setFlag(int flag) {
        this.position_flag = flag;
    }

    public void setTheme(String Theme) {
        this.Theme = Theme;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            // 引入布局
            view = View.inflate(context, R.layout.item_playlist, null);
            // 实例化对象
            holder.song = (TextView) view.findViewById(R.id.item_mymusic_song);
            holder.singer = (TextView) view
                    .findViewById(R.id.item_mymusic_singer);
            holder.duration = (TextView) view
                    .findViewById(R.id.item_mymusic_duration);
            holder.position = (TextView) view
                    .findViewById(R.id.item_mymusic_postion);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // 给控件赋值
        String string_song = list.get(i).getName_Song();
//		if (string_song.length() >= 5
//				&& string_song.substring(string_song.length() - 4,
//						string_song.length()).equals(".mp3")) {
//			holder.song.setText(string_song.substring(0,
//					string_song.length() - 4).trim());
//		} else {
//
//		}
        holder.song.setText(string_song);//.trim());
        if (i == position_flag) {
            switch (Theme) {
                case "blue":
                    holder.song.setTextColor(myBlue);
                    holder.singer.setTextColor(myBlue);
                    holder.duration.setTextColor(myBlue);
                    holder.position.setText("");
                    holder.position.setBackgroundResource(R.mipmap.play_small);
                    break;
                case "purple":
                    holder.song.setTextColor(myPurple);
                    holder.singer.setTextColor(myPurple);
                    holder.duration.setTextColor(myPurple);
                    holder.position.setText("");
                    holder.position
                            .setBackgroundResource(R.mipmap.play_small_purple);
                    break;
                case "green":
                    holder.song.setTextColor(myGreeen);
                    holder.singer.setTextColor(myGreeen);
                    holder.duration.setTextColor(myGreeen);
                    holder.position.setText("");
                    holder.position
                            .setBackgroundResource(R.mipmap.play_small_green);
                    break;
                case "red":
                    holder.song.setTextColor(myRed);
                    holder.singer.setTextColor(myRed);
                    holder.duration.setTextColor(myRed);
                    holder.position.setText("");
                    holder.position
                            .setBackgroundResource(R.mipmap.play_small_red);
                    break;
                default:
                    break;
            }

        } else {
            holder.song.setTextColor(Color.BLACK);
            holder.singer.setTextColor(Color.BLACK);
            holder.duration.setTextColor(Color.BLACK);
            holder.position.setText(i + 1 + "");
        }

        holder.singer.setText(list.get(i).getSinger_Song().toString().trim());
        // 时间需要转换一下
        GetDurationUtil.getduration(list.get(i).getFile_Song(),view.getContext());
        int duration =0;
        sp =view.getContext().getSharedPreferences("test", Context.MODE_PRIVATE);//初始化
        if(sp.getInt("time",0)>0){
            duration=sp.getInt("time",0);//获得歌曲时长
        }
        String time = MusicUtils.formatTime(duration);
        holder.duration.setText(time);

        return view;
    }

    class ViewHolder {
        TextView song;// 歌曲名
        TextView singer;// 歌手
        TextView duration;// 时长
        TextView position;// 序号
    }
















//    private Context context;
//    private List<Song> list;
//    private int position_flag = 0;
//    private int myRed = Color.argb(0xff, 0xFF, 0x00, 0x00);
//    private ListAdapter.OnRecycleItemClickListener onRecycleItemClickListener=null;
//    private String Theme;
//
//    static class  ViewHolder extends RecyclerView.ViewHolder{
//        TextView song;// 歌曲名
//        TextView singer;// 歌手
//        TextView duration;// 时长
//        TextView position;// 序号
//        View songView;
//
//        public  ViewHolder(View view){
//            super(view);
//            songView = view;
//            song = (TextView) view.findViewById(R.id.item_mymusic_song);
//            singer = (TextView) view.findViewById(R.id.item_mymusic_singer);
//            duration = (TextView) view.findViewById(R.id.item_mymusic_duration);
//            position = (TextView) view.findViewById(R.id.item_mymusic_postion);
//        }
//    }
//
//        public PlayAdapter(ListplayActivity listplayActivity, List<Song> list) {
//            this.context = listplayActivity;
//            this.list = list;
//        }
//
//        public void setFlag(int flag) {
//            this.position_flag = flag;
//        }
//
//        public void setTheme(String Theme) {
//            this.Theme = Theme;
//        }
//
//    public PlayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist, parent ,false);
//        final PlayAdapter.ViewHolder holder = new PlayAdapter.ViewHolder(view);
//        return  holder;
//    }
//
//    @Override
//    public void onBindViewHolder(PlayAdapter.ViewHolder holder, final int position){
//        Song song = list.get(position);
//        String string_song = song.getSong();
//        if (string_song.length() >= 5
//                && string_song.substring(string_song.length() - 4, string_song.length()).equals(".mp3")) {
//            holder.song.setText(string_song.substring(0,
//                    string_song.length() - 4).trim());
//        } else {
//            holder.song.setText(string_song.trim());
//        }
//
//        holder.singer.setText(song.getSinger().toString().trim());
//        // 时间转换为时分秒
//        int duration = song.getDuration();
//        String time = MusicUtils.formatTime(duration);
//        holder.duration.setText(time);
//        holder.songView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(onRecycleItemClickListener!=null)
//                    onRecycleItemClickListener.OnRecycleItemClickListener(position);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount(){
//        return list.size();
//    }
//
//    public  void  OnRecycleItemClickListener(ListAdapter.OnRecycleItemClickListener v){
//        onRecycleItemClickListener = v;
//    }
//
//    public interface OnRecycleItemClickListener{
//        void OnRecycleItemClickListener(int position);
//    }

}
