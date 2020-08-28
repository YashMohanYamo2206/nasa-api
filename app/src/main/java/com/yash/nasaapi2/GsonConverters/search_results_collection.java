package com.yash.nasaapi2.GsonConverters;

import com.google.gson.annotations.SerializedName;

public class search_results_collection {
    @SerializedName("collection")
    private Collection collection;

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection value) {
        this.collection = value;
    }
}
