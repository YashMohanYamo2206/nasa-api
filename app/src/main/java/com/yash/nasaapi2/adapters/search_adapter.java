package com.yash.nasaapi2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.yash.nasaapi2.R;

import java.util.List;

public class search_adapter extends RecyclerView.Adapter<search_adapter.search_viewHolder> {

    List<String> titles;
    List<String> urls;


    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position, String img_link);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public search_adapter(List<String> titles, List<String> urls) {
        this.titles = titles;
        this.urls = urls;
    }

    public class search_viewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public search_viewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_search_result_name);
            itemView.setOnClickListener(v -> {
                if (mListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(position, urls.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public search_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new search_viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull search_viewHolder holder, int position) {
        String title = titles.get(position);
        holder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}
