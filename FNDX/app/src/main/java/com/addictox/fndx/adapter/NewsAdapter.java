package com.addictox.fndx.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.addictox.fndx.R;
import com.addictox.fndx.activity.DetailActivity;
import com.addictox.fndx.model.News;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final ArrayList<News> news;
    private final Context context;
    private final RecyclerViewClickListener listener;

    public NewsAdapter(ArrayList<News> news, Context context, RecyclerViewClickListener listener) {
        this.news = news;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.news_custom_row, parent, false);

        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int p) {
        News newsObject = news.get(p);
        holder.txtHeading.setText(newsObject.title);
        if (!newsObject.desc.equals("NULL")){
            holder.txtArticle.setVisibility(View.VISIBLE);
            holder.txtArticle.setText(newsObject.desc);
        } else{
            holder.txtArticle.setVisibility(View.GONE);
        }

        holder.txtPublishedDate.setText(newsObject.pub);
        Picasso.get().load(newsObject.img).error(R.drawable.ic_action_profile)
                .into(holder.imgNewsThumbnail);

        holder.cardNews.setOnClickListener(view -> {
            listener.onRecyclerViewItemClick(holder.cardNews , newsObject.link);
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        public TextView txtHeading;
        public TextView txtArticle;
        public TextView txtPublishedDate;
        public ImageView imgNewsThumbnail;
        public CardView cardNews;

        public NewsViewHolder(View view) {
            super(view);
            txtHeading = view.findViewById(R.id.txtHeading);
            txtArticle = view.findViewById(R.id.txtArticle);
            txtPublishedDate = view.findViewById(R.id.txtPublishedDate);
            imgNewsThumbnail = view.findViewById(R.id.imgNewsThumbnail);
            cardNews = view.findViewById(R.id.cardNews);
        }
    }
}
