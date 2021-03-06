package com.example.www.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String mURL;
    private static final String LOG_TAG =NewsLoader.class.getName();
    public NewsLoader(Context context, String url) {
        super(context);
        mURL=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {

        if (mURL == null) {
            return null;
        }

        List<News> result = QueryUtils.fetchBookData(mURL);
        return result;
    }
}