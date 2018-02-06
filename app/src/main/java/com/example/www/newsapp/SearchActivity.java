package com.example.www.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class SearchActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = " com.example.www.newsapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.query);
        String Query = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, Query);
        startActivity(intent);
    }

}
