package com.yash.nasaapi2.interfaces;

import com.yash.nasaapi2.GsonConverters.search_results_collection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface searching {
    @GET("search")
    Call<search_results_collection> get_search_results(@Query("title") String title, @Query("media_type") String media_type);
}
