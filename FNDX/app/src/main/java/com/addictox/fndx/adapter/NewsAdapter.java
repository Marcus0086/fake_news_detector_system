package com.addictox.fndx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.addictox.fndx.R;
import com.addictox.fndx.model.News;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> implements Filterable{

    private final ArrayList<News> news;
    private final Context context;

    public NewsAdapter(ArrayList<News> news, Context context) {
        this.news = news;
        this.context = context;
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
        holder.txtHeading.setText(newsObject.Heading);
        holder.txtArticle.setText(newsObject.Article);
        Picasso.get().load("https://static.toiimg.com/thumb/msid-85833759,imgsize-21126,width-400,resizemode-4/85833759.jpg")
                .error(R.drawable.ic_action_profile).into(holder.imgNewsThumbnail);
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

        public TextView txtHeading;
        public TextView txtArticle;
        public ImageView imgNewsThumbnail;

        public NewsViewHolder(View view) {
            super(view);
            txtHeading = view.findViewById(R.id.txtHeading);
            txtArticle = view.findViewById(R.id.txtArticle);
            imgNewsThumbnail = view.findViewById(R.id.imgNewsThumbnail);
        }
    }
}
