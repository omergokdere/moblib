package com.moblib.adapters.google;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/** Used for deserialization purposes*/
public class GoogleBookListAdapter {

    @SerializedName("items")
    private ArrayList<GoogleBookAdapter> mBookItems;

    public ArrayList<GoogleBookAdapter> getBookItems() {
        return mBookItems;
    }
}
