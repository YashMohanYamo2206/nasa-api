package com.yash.nasaapi2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yash.nasaapi2.R;

public class search_image extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_image);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        TextView tv_imageLoad = findViewById(R.id.image_loads_here);
        Intent intent = getIntent();
        String url = intent.getStringExtra("img_url");
        Picasso.with(this).load(url).fit().into((ImageView) findViewById(R.id.iv_searchImage), new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.INVISIBLE);
                tv_imageLoad.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {
                tv_imageLoad.setText(R.string.network_error_message);
            }
        });
    }

    public void back_button(View view) {
        finish();
    }
}