package com.yash.nasaapi2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.yash.nasaapi2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void datePicker(View view) {
        startActivity(new Intent(this, DatePicker_apod.class));
    }

    public void search(View view) {
        startActivity(new Intent(this, search.class));
    }
}