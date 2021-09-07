package com.addictox.fndx.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.addictox.fndx.R;
import com.addictox.fndx.adapter.NewsAdapter;
import com.addictox.fndx.model.News;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public RecyclerView recyclerNews;
    public NewsAdapter newsAdapter;
    private ProgressBar progressBar;
    private RelativeLayout rlLoading;
    private EditText etSearch;
    private ArrayList<News> newsList = new ArrayList<News>();


    public HomeFragment() {
//        newsList.add(new News("Manchester United return 'the best decision', says Ronaldo",
//                "Cristiano Ronaldo on Wednesday vowed to help Manchester Unit .. "));
        newsList.add(new News("Priyanshu", "Gupta"));
        newsList.add(new News("Vikas", "Singh"));
        newsList.add(new News("Raghav", "Gupta"));
        newsList.add(new News("Harsh", "Rastogi"));
        newsList.add(new News("Hello", "World"));
        newsList.add(new News("Hello", "World"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        etSearch = view.findViewById(R.id.etSearch);
        progressBar = view.findViewById(R.id.progressBar);
        rlLoading = view.findViewById(R.id.rlLoading);
        rlLoading.setVisibility(View.VISIBLE);

        setupRecycler(view);

        return view;
    }

    private void setupRecycler(View view){
        recyclerNews = view.findViewById(R.id.recyclerNews);

        if (getActivity() != null) {
            rlLoading.setVisibility(View.GONE);
            newsAdapter = new NewsAdapter(newsList, (Context) getActivity());
            recyclerNews.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerNews.setItemAnimator(new DefaultItemAnimator());
            recyclerNews.setAdapter(newsAdapter);
            recyclerNews.setHasFixedSize(true);
        }
    }
}