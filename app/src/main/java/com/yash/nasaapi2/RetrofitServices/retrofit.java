package com.yash.nasaapi2.RetrofitServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofit {
    public static final String APOD_BASE_URL ="https://api.nasa.gov/planetary/";
    public static final String SEARCH_BASE_URL ="https://images-api.nasa.gov/";

    public static Retrofit get_apod(){
        return new Retrofit.Builder().baseUrl(APOD_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static Retrofit get_search_results(){
        return new Retrofit.Builder().baseUrl(SEARCH_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
}
