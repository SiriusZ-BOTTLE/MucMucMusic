package com.example.myapplication.ui.music.play;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;


public class AddCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcomment);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ImageView exit = (ImageView) findViewById(R.id.title_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final EditText details = (EditText) findViewById(R.id.details);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        Button btn = (Button) findViewById(R.id.title_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c_details = details.getText().toString();
                float score = ratingBar.getRating();
                Intent intent = new Intent();
                intent.putExtra("details",c_details);
                intent.putExtra("score",score);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}