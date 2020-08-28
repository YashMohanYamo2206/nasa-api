package com.yash.nasaapi2.GsonConverters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    @SerializedName("data")
    private List<Data> data;
    @SerializedName("links")
    private List<ItemLink> links;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> value) {
        this.data = value;
    }


    public List<ItemLink> getLinks() {
        return links;
    }

    public void setLinks(List<ItemLink> value) {
        this.links = value;
    }

}
