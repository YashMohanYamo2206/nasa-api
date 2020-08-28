package com.yash.nasaapi2.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.squareup.picasso.Picasso;
import com.yash.nasaapi2.GsonConverters.apod;
import com.yash.nasaapi2.RetrofitServices.retrofit;
import com.yash.nasaapi2.interfaces.apod_interfaces;
import com.yash.nasaapi2.R;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatePicker_apod extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    String date;
    ImageView apod_img;
    Button showImg;
    TextView img_loads_here,tv_date;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_apod);
        img_loads_here = findViewById(R.id.image_loads_here);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        showImg = findViewById(R.id.btn_showImg);
        apod_img = findViewById(R.id.img_apod);
        tv_date = findViewById(R.id.date);
        showImg.setVisibility(View.INVISIBLE);
        showImg.setOnClickListener(v -> {
            System.out.println(date);
            progressBar.setVisibility(View.VISIBLE);
            init_retrofit_services(date);
        });
    }

    private void init_retrofit_services(String date) {
        apod_interfaces interfaces = retrofit.get_apod().create(apod_interfaces.class);
        interfaces.get_apod("uTKD7B42PtZmrZ5hsueOCUqpvuihuQho0DiTBMJ8", date).enqueue(new Callback<apod>() {
            @Override
            public void onResponse(@NonNull Call<apod> call, @NonNull Response<apod> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DatePicker_apod.this, R.string.network_error_message, Toast.LENGTH_SHORT).show();
                    return;
                }
                assert response.body() != null;
                String img_url = response.body().getUrl();
                Picasso.with(DatePicker_apod.this).load(img_url).fit().into(apod_img, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        img_loads_here.setVisibility(View.GONE);
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {
                        img_loads_here.setText(R.string.network_error_message);
                        Toast.makeText(DatePicker_apod.this, R.string.network_error_message, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
            @Override
            public void onFailure(@NonNull Call<apod> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(DatePicker_apod.this, R.string.network_error_message, Toast.LENGTH_SHORT).show();
                img_loads_here.setText(R.string.network_error_message);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void Calendar_view(View view) {
        DialogFragment datePicker = new DatePicker();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,dayOfMonth+1);
        if(calendar.after(Calendar.getInstance())){
            Toast.makeText(this, "please select a date in the past", Toast.LENGTH_SHORT).show();
            showImg.setVisibility(View.INVISIBLE);
            return;
        }
        date = String.format("%d-%02d-%02d", year, month+1, dayOfMonth);
        showImg.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            showImg.setBackgroundColor(getResources().getColor(R.color.cool_blue,null));
        }
        tv_date.setText(date);
    }
}