package com.example.myapplication.ui.account;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.ui.home.LoginActivity;
import com.flipboard.bottomsheet.BottomSheetLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AccountFragment extends Fragment {
    private AccountViewModel accountViewModel1;
    public static List<Activity> activityList = new LinkedList();
    private View bottomSheet;
    private BottomSheetLayout bottomSheetLayout;
    SharedPreferences sp;
    private Uri imageUri;
    private ImageView image_head;
    public static final  int TAKE_PHOTO =1;
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        accountViewModel1 = ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        Button btn1 = root.findViewById(R.id.xiugai);

        sp = getActivity().getSharedPreferences("test",Context.MODE_PRIVATE);
        bottomSheetLayout = (BottomSheetLayout) root.findViewById(R.id.bottomSheetLayout);
        image_head = (ImageView) root.findViewById(R.id.h_head); //绑定用户头像控件
        image_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(),"you clicked 头像",Toast.LENGTH_SHORT).show();
                showBottomSheet();
            }
        });

        TextView nickname = (TextView) root.findViewById(R.id.user_name);
        nickname.setText(sp.getString("nickname",""));

        TextView iograph = (TextView) root.findViewById(R.id.user_iograph);
        iograph.setText(sp.getString("iograph",""));

        btn1.setOnClickListener(new View.OnClickListener() { //修改用户资料
            @Override
            public void onClick(View v) {
//                showBottomSheet();
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

    private void showBottomSheet() {

//          创建要弹出的布局
        bottomSheet = createBottomSheetView();

//          判断打开关闭
        if (bottomSheetLayout.isSheetShowing()) {
            bottomSheetLayout.dismissSheet();
        } else {
//          弹出布局
            bottomSheetLayout.showWithSheetView(bottomSheet);
        }
    }

    /**
     * 从底部弹出的子布局
     * @return
     */
    private View createBottomSheetView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_bottom_sheet, (ViewGroup) getActivity().getWindow().getDecorView(), false);
        TextView takephoto = (TextView) view.findViewById(R.id.takephoto);
        TextView selectphoto = (TextView) view.findViewById(R.id.selectphoto);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(getActivity().getExternalCacheDir(),"output_image.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24){
                    imageUri = FileProvider.getUriForFile(getActivity(),"com.example.myapplication.fileprovider",outputImage);
                }else {
                    imageUri = Uri.fromFile(outputImage);
                }
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
//                Toast.makeText(getActivity(),"you clicked concern",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode == getActivity().RESULT_OK){
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        image_head.setImageBitmap(bitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
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
