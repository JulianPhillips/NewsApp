package com.example.www.newsapp;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    private final String USGS_REQUEST_URL =
      "http://content.guardianapis.com/search?q=pokemon&api-key=test&show-tags=contributor";
    public static final String LOG_TAG = MainActivity.class.getName();
    private newsAdapter mAdapter;
    private static final int BOOK_LOADER_ID = 1;
    private TextView mEmptyStateTextView;
    private ProgressBar mProgressBarView;
    private ConnectivityManager mConnectionStatus;
    private NetworkInfo mNetworkInfo;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String Query = intent.getStringExtra(SearchActivity.EXTRA_MESSAGE);
        URL = "https://content.guardianapis.com/search?q=" + Query + "&api-key=test&show-tags=contributor";

        ListView bookListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

        mAdapter = new newsAdapter(this, new ArrayList<News>());

        mConnectionStatus = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        mNetworkInfo = mConnectionStatus.getActiveNetworkInfo();

        bookListView.setAdapter(mAdapter);

        if (mAdapter == null) {

            bookListView.setEmptyView(mEmptyStateTextView);

        }

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                News currentNews = mAdapter.getItem(position);

                Uri newsUri = Uri.parse(currentNews.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                if (websiteIntent.resolveActivity(getPackageManager()) != null) {

                    startActivity(websiteIntent);

                }
            }
        });
        if (mNetworkInfo != null && mNetworkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.progress_bar);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, URL);
    }
    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {

        View loadingIndicator = findViewById(R.id.progress_bar);
        loadingIndicator.setVisibility(View.GONE);
        mAdapter.clear();

        if (news != null && !news.isEmpty()) {
            mAdapter.addAll(news);
        }
        else
            mEmptyStateTextView.setText(R.string.no_news);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();
    }
}
