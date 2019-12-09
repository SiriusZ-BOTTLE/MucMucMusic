package com.example.myapplication.ui.account;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.R;
import com.example.myapplication.Util.Base64Util;
import com.example.myapplication.Util.HttpUtil;
import com.example.myapplication.bean_new.User;
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
    private SharedPreferences.Editor editor;

    private Uri imageUri;
    private ImageView image_head;
    public static final int TAKE_PHOTO =1;
    public static final int CHOOSE_PHOTO =2;
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        accountViewModel1 = ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        sp = getActivity().getSharedPreferences("test",Context.MODE_PRIVATE);

        bottomSheetLayout = (BottomSheetLayout) root.findViewById(R.id.bottomSheetLayout);
        image_head = (ImageView) root.findViewById(R.id.h_head); //绑定用户头像控件
        byte[] data=Base64Util.decode(sp.getString("iconFile_User",""));
        image_head.setImageBitmap( BitmapFactory.decodeByteArray(data, 0, data.length));//修改头像
        image_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(),"you clicked 头像",Toast.LENGTH_SHORT).show();
                showBottomSheet();
            }
        });

        TextView nickname = (TextView) root.findViewById(R.id.user_name);
        nickname.setText(sp.getString("nickname",""));

        TextView idiograph = (TextView) root.findViewById(R.id.user_iograph);
        idiograph.setText(sp.getString("idiograph",""));

        Button btn1 = root.findViewById(R.id.xiugai);//设置
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ModifyActivity.class);
                startActivity(intent);
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
        selectphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetLayout.dismissSheet();
            }
        });
        return view;
    }

    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(getActivity(), "you denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
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
                        ThreadUpdateicon thread1 = new ThreadUpdateicon(bitmap);
                        thread1.start();
                        thread1.join();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if(resultCode==getActivity().RESULT_OK){
                    if(Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    }else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(getActivity(),uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" +id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = getActivity().getContentResolver().query(uri, null,selection,null,null);
        if(cursor !=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("Updateicon", "handleMessage: " );
                    break;
                default:
                    break;
            }
        }
    };
    private class  ThreadUpdateicon extends Thread {
        private Bitmap bit;
        public ThreadUpdateicon(Bitmap bit) {
            this.bit=bit;
        }
        @Override
        public void run() {
            sp = getActivity().getSharedPreferences("test",Context.MODE_PRIVATE);
            editor =  sp.edit();
            String b=Base64Util.bitmapToBase64(bit);
            User user=new User();
            user.setId_User(sp.getString("name",""));
            user.setIconFile_User(b);
            editor.putString("iconFile_User",b);
            editor.commit();
            String body= JSON.toJSONString(user);
            HttpUtil.sendPostUrl("http://47.97.202.142:8082/user/update",body,"UTF-8");
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }

    }
    private void displayImage(String imagePath){
        if(imagePath != null){
            final Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            image_head.setImageBitmap(bitmap);
            try{
                ThreadUpdateicon thread1 = new ThreadUpdateicon(bitmap);
                thread1.start();
                thread1.join();
            }catch (Exception e){

            }
        }else {
            Toast.makeText(getActivity(),"failed to get image",Toast.LENGTH_SHORT).show();
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
