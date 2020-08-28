package com.yash.nasaapi2.GsonConverters;

import com.google.gson.annotations.SerializedName;

public class ItemLink {
    @SerializedName("href")
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String value) {
        this.href = value;
    }

}
