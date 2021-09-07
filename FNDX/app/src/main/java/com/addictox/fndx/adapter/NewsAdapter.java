package com.addictox.fndx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.addictox.fndx.R;
import com.addictox.fndx.model.News;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> implements Filterable{

    private ArrayList<News> news;
    private Context context;

    public NewsAdapter(ArrayList<News> news, Context context) {
        news = news;
        context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.news_custom_row, parent, false);

        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        public NewsViewHolder(View view) {
            super(view);
            TextView txtHeading = view.findViewById(R.id.txtHeading);
            TextView txtArticle = view.findViewById(R.id.txtArticle);
        }
    }
}
