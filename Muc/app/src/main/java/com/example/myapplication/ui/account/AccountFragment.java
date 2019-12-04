package com.example.myapplication.ui.account;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.ui.home.LoginActivity;

import java.util.LinkedList;
import java.util.List;

public class AccountFragment extends Fragment {
    private AccountViewModel accountViewModel1;
    public static List<Activity> activityList = new LinkedList();
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        accountViewModel1 = ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        Button btn1 = root.findViewById(R.id.xiugai);

        btn1.setOnClickListener(new View.OnClickListener() { //修改用户资料
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"you clicked xiugai",Toast.LENGTH_SHORT).show();

            }
        });
        ImageView image_head = (ImageView) root.findViewById(R.id.h_head); //绑定用户头像控件
        image_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"更换头像",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn2 = root.findViewById(R.id.shezhi);//设置
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"you clicked shezhi",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn3 = root.findViewById(R.id.concerning);//关于
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"you clicked concern",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn4 = root.findViewById(R.id.exit); //退出当前用户
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        return root;
    }

    public void onBackPressed() {
        new AlertDialog.Builder( getActivity() )
                .setTitle( "确认对话框" )
                .setMessage( "你确定要退出？" )
                .setNegativeButton( "取消",null )
                .setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // finish();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                } )
                .show();
    }
}
