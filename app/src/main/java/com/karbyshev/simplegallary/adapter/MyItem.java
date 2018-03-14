package com.karbyshev.simplegallary.adapter;

public class MyItem {
    private String mImageUrl;
    private String mCreatorName;

    public MyItem(String mImageUrl, String mCreatorName) {
        this.mImageUrl = mImageUrl;
        this.mCreatorName = mCreatorName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmCreatorName() {
        return mCreatorName;
    }
}
