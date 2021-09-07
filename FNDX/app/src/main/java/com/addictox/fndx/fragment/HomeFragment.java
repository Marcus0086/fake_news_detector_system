package com.addictox.fndx.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
    private ArrayList<News> newsList;



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
    }
}