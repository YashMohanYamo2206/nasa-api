package com.yash.nasaapi2.GsonConverters;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("nasa_id")
    private String nasaID;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getNasaID() {
        return nasaID;
    }

}
