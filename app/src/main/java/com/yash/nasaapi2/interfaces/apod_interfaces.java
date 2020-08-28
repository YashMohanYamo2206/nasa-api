package com.yash.nasaapi2.interfaces;

import com.yash.nasaapi2.GsonConverters.apod;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apod_interfaces {
    @GET("apod")
    Call<apod> get_apod(@Query("api_key") String api_key, @Query("date") String date);
}
