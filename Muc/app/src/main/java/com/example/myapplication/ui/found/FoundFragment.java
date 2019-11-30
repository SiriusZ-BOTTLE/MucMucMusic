package com.example.myapplication.ui.found;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.bean.Category;
import com.example.myapplication.bean.Music;

import java.util.ArrayList;
import java.util.List;

public class FoundFragment extends Fragment {

    private FoundViewModel foundViewModel;
    private LinearLayout lltPageIndicator;
    private List<Category> categorylist = new ArrayList<>();
    private List<Music> musiclist = new ArrayList<>();
    private int[] ImageArray=new int[]{R.drawable.nanshannan,R.drawable.xiaochou,R.drawable.faxian,R.drawable.luntan};
    private ImageView[] DotArray;
    private  List<View> ViewList = new ArrayList<View>();
    private  int[] ids = {R.id.dot1,R.id.dot2,R.id.dot3,R.id.dot4};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        foundViewModel =
                ViewModelProviders.of(this).get(FoundViewModel.class);
        View root = inflater.inflate(R.layout.fragment_found, container, false);

        //viewpager的设置

        ViewPager boot_vp=(ViewPager) root.findViewById(R.id.viewpage);
        for(int i=0;i<4;i++){
            ViewList.add(getGuidePage(i));
        }
        DotArray = new ImageView[ViewList.size()];
        for (int i = 0; i < ViewList.size(); i++) {
            DotArray[i] = (ImageView) root.findViewById(ids[i]);
        }

        boot_vp.setAdapter(new ViewPagerAdapter(ViewList));

        boot_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                    // 设置底部小点选中状态
                for(int i = 0;i<ids.length;i ++){
                    if(position==i){
                        DotArray[i].setImageResource(R.drawable.yuandian1);
                    }else {
                        DotArray[i].setImageResource(R.drawable.yuandian);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        initCategory();

        RecyclerView recyclerView = root.findViewById(R.id.recycler_category);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        CategoryAdapter adapter = new CategoryAdapter(categorylist);
        recyclerView.setAdapter(adapter);


        RecyclerView recyclerView2 = root.findViewById(R.id.recycler_found);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(manager);
        FoundRecyclerViewAdapter adapter2 = new FoundRecyclerViewAdapter(categorylist,getActivity());
        recyclerView2.setAdapter(adapter2);
        return root;
    }

    private View getGuidePage(int i) {
        View v = View.inflate(getActivity(), R.layout.musiclist_viewpager, null);
        ImageView ivGuidePage = (ImageView) v.findViewById(R.id.music_vp_image);
        switch (i) {
            case 0:
                ivGuidePage.setImageResource(R.drawable.nanshannan);
                break;
            case 1:
                ivGuidePage.setImageResource(R.drawable.xiaochou);
                break;
            case 2:
                ivGuidePage.setImageResource(R.drawable.luntan);
                break;
            case 3:
                ivGuidePage.setImageResource(R.drawable.faxian);
                break;
        }
        return v;
    }

    private void initMusic(){
        Music three = new Music("南山南",R.drawable.nanshannan,"数据");
        musiclist.add(three);
        Music one = new Music("消愁",R.drawable.xiaochou,"数据");
        musiclist.add(one);
        Music two = new Music("论坛",R.drawable.luntan,"数据");
        musiclist.add(two);
        Music three1 = new Music("南山南",R.drawable.nanshannan,"数据");
        musiclist.add(three1);
        Music one1 = new Music("发现",R.drawable.faxian,"数据");
        musiclist.add(one1);
        Music two1 = new Music("消愁",R.drawable.xiaochou,"数据");
        musiclist.add(two1);
        Music three2 = new Music("南山南",R.drawable.nanshannan,"数据");
        musiclist.add(three2);
        Music one3 = new Music("发现",R.drawable.faxian,"数据");
        musiclist.add(one3);
        Music two4 = new Music("论坛",R.drawable.luntan,"数据");
        musiclist.add(two4);
    }
    private void initCategory(){
        initMusic();
        Category one = new Category("往期推荐",musiclist);
        categorylist.add(one);
        Category two = new Category("每日更新",musiclist);
        categorylist.add(two);
        Category three = new Category("古风",musiclist);
        categorylist.add(three);
        Category four = new Category("流行",musiclist);
        categorylist.add(four);
        Category five = new Category("摇滚",musiclist);
        categorylist.add(five);
        Category six = new Category("民谣",musiclist);
        categorylist.add(six);
        Category seven = new Category("爱情",musiclist);
        categorylist.add(seven);
    }
}