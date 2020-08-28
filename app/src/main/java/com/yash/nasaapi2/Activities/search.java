package com.yash.nasaapi2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yash.nasaapi2.GsonConverters.Collection;
import com.yash.nasaapi2.GsonConverters.Item;
import com.yash.nasaapi2.GsonConverters.search_results_collection;
import com.yash.nasaapi2.RetrofitServices.retrofit;
import com.yash.nasaapi2.adapters.search_adapter;
import com.yash.nasaapi2.interfaces.searching;
import com.yash.nasaapi2.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class search extends AppCompatActivity implements search_adapter.OnItemClickListener {

    List<String> titles = new ArrayList<>();
    List<String> urls = new ArrayList<>();
    SearchView searchView;
    RecyclerView rv_search;
    search_adapter adapter;
    ProgressBar progressBar;
    TextView tv_noLoad;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        searchView = findViewById(R.id.search_view);
        progressBar = findViewById(R.id.progressBar);
        tv_noLoad = findViewById(R.id.tv_noLoad);
        rv_search = findViewById(R.id.rv_search);
        rv_search.setLayoutManager(new LinearLayoutManager(this));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
                search_action(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                progressBar.setVisibility(View.VISIBLE);
                search_action(newText);
                return false;
            }
        });
    }

    private void search_action(String search) {
        titles = new ArrayList<>();
        urls = new ArrayList<>();
        searching search1 = retrofit.get_search_results().create(searching.class);
        search1.get_search_results(search, "image").enqueue(new Callback<search_results_collection>() {
            @Override
            public void onResponse(Call<search_results_collection> call, Response<search_results_collection> response) {
                if (!response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    tv_noLoad.setVisibility(View.VISIBLE);
                    Toast.makeText(search.this, R.string.network_error_message, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(response.body()==null){
                    progressBar.setVisibility(View.INVISIBLE);
                    tv_noLoad.setVisibility(View.VISIBLE);
                    return;
                }
                tv_noLoad.setVisibility(View.INVISIBLE);
                Collection collection = response.body().getCollection();
                List<Item> items = new ArrayList<>();
                items = collection.getItems();
                for(Item item:items){
                    if(!titles.contains(item.getData().get(0).getTitle())){
                        titles.add(item.getData().get(0).getTitle());
                        urls.add(item.getLinks().get(0).getHref());
                    }
                }
                if(titles.isEmpty()){
                    tv_noLoad.setVisibility(View.VISIBLE);
                }
                adapter = new search_adapter(titles,urls);
                adapter.setOnItemClickListener(com.yash.nasaapi2.Activities.search.this);
                rv_search.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<search_results_collection> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                tv_noLoad.setVisibility(View.VISIBLE);
                Toast.makeText(search.this, R.string.network_error_message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position, String img_link) {
        if(img_link!=null){
            Intent intent = new Intent(search.this, search_image.class);
            intent.putExtra("img_url",img_link);
            startActivity(intent);
        }
    }
}
