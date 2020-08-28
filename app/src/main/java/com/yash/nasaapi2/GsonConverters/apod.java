package com.yash.nasaapi2.GsonConverters;

import com.google.gson.annotations.SerializedName;

public class apod {
    @SerializedName("copyright")
    public String Copyright;

    @SerializedName("date")
    public String Date;

    @SerializedName("explanation")
    public String Explanation;

    @SerializedName("hdurl")
    public String HdUrl;

    @SerializedName("media_type")
    public String MediaType;

    @SerializedName("service_version")
    public String ServiceVersion;

    @SerializedName("title")
    public String Title;

    @SerializedName("url")
    public String Url;


    public String getCopyright() {
        return Copyright;
    }

    public String getDate() {
        return Date;
    }

    public String getExplanation() {
        return Explanation;
    }

    public String getHdUrl() {
        return HdUrl;
    }

    public String getMediaType() {
        return MediaType;
    }

    public String getServiceVersion() {
        return ServiceVersion;
    }

    public String getTitle() {
        return Title;
    }

    public String getUrl() {
        return Url;
    }
}
