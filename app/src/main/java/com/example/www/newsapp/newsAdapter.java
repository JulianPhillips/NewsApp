package com.example.www.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class newsAdapter extends ArrayAdapter<News> {

    public newsAdapter(Context context, List<News> News) {
        super(context, 0, News);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }

        News currentNews = getItem(position);

        TextView TitleView = (TextView) listItemView.findViewById(R.id.Title);

        String Title = currentNews.getTitle();

        TitleView.setText(Title);
        TextView AuthorView  = (TextView) listItemView.findViewById(R.id.Author);
        String Author = currentNews.getAuthor();
        AuthorView.setText(Author);
        TextView DateView = (TextView) listItemView.findViewById(R.id.Date);

        String Date = currentNews.getDate();

        DateView.setText(Date);

        TextView SectionView = (TextView) listItemView.findViewById(R.id.Section);

        String Section= currentNews.getSection();

        SectionView.setText(Section);

        return listItemView;
    }
}