package com.yash.nasaapi2.GsonConverters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Collection {
    @SerializedName("items")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> value) {
        this.items = value;
    }

}
