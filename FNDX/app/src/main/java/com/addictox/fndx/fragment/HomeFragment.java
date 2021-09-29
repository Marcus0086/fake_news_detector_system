package com.addictox.fndx.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.addictox.fndx.R;
import com.addictox.fndx.adapter.NewsAdapter;
import com.addictox.fndx.model.News;
import com.addictox.fndx.util.ConnectionManager;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    public RecyclerView recyclerNews;
    public NewsAdapter newsAdapter;
    private RelativeLayout rlLoading;
    ArrayList<News> newsList = new ArrayList<News>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rlLoading = view.findViewById(R.id.rlLoading);
        rlLoading.setVisibility(View.VISIBLE);
        recyclerNews = view.findViewById(R.id.recyclerNews);

        JSONObject jsonParam = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        Chip chipTopStories = view.findViewById(R.id.chipTopStories);
        Chip chipMostRecentStories = view.findViewById(R.id.chipMostRecentStories);
        Chip chipWorld = view.findViewById(R.id.chipWorld);
        Chip chipSports = view.findViewById(R.id.chipSports);
        Chip chipTech = view.findViewById(R.id.chipTech);
        Chip chipBusiness = view.findViewById(R.id.chipBusiness);
        Chip chipEducation = view.findViewById(R.id.chipEducation);
        Chip chipEntertainment = view.findViewById(R.id.chipEntertainment);
        Chip chipScience = view.findViewById(R.id.chipScience);

        try{ jsonParam.put("personalised", jsonArray); }
        catch(Exception e){e.printStackTrace();}

        CompoundButton.OnCheckedChangeListener clickListener = (chip, isChecked) -> {
            String chipName = chip.getText().toString().toLowerCase().replace(" ","-");
            if(isChecked){
                jsonArray.put(chipName);
            } else {
                jsonArray.remove(jsonArray.length()-1);
                for(int i = 0; i<jsonArray.length();++i){
                    try {
                        if(jsonArray.get(i) == chipName)
                            jsonArray.remove(i);
                    } catch (JSONException e) { e.printStackTrace(); }
                }
            }
            System.out.println("CHIPS:"+jsonArray.toString());
            try { jsonParam.put("personalised", jsonArray); }
            catch(Exception e){e.printStackTrace();}
//            rlLoading.setVisibility(View.VISIBLE);
        };

        chipTopStories.setOnCheckedChangeListener(clickListener);
        chipMostRecentStories.setOnCheckedChangeListener(clickListener);
        chipWorld.setOnCheckedChangeListener(clickListener);
        chipSports.setOnCheckedChangeListener(clickListener);
        chipTech.setOnCheckedChangeListener(clickListener);
        chipBusiness.setOnCheckedChangeListener(clickListener);
        chipEducation.setOnCheckedChangeListener(clickListener);
        chipEntertainment.setOnCheckedChangeListener(clickListener);
        chipScience.setOnCheckedChangeListener(clickListener);

        setupRecycler(jsonParam);
        return view;
    }

    private void setupRecycler(JSONObject jsonParam) {
        newsList.clear();
        RequestQueue queue = Volley.newRequestQueue(requireActivity());

        if (new ConnectionManager().isNetworkAvailable(requireActivity())) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                "https://519eae26.eu-gb.apigw.appdomain.cloud/news-api/news",
                jsonParam,
                response -> {
//                    System.out.println("Response:"+response);
                    try {
                        if(response.getBoolean("success")){
                            rlLoading.setVisibility(View.GONE);
                            JSONArray keys = response.getJSONObject("data").names();
                            System.out.println("CHIPS:"+keys.toString());
                            for (int i = 0; i < Objects.requireNonNull(keys).length(); ++i){
                                JSONArray newsTypeArray = response.getJSONObject("data").
                                        getJSONArray(keys.getString(i));
                                for(int j=0; j<newsTypeArray.length();++j){
                                    JSONObject newsObject = newsTypeArray.getJSONObject(j);
                                    News news = new News(
                                            newsObject.getString("title"),
                                            newsObject.getString("desc"),
                                            newsObject.getString("published"),
                                            newsObject.getString("photo"),
                                            newsObject.getString("link")
                                    );
                                    newsList.add(news);
                                }
                            }
                            newsAdapter = new NewsAdapter(newsList, requireActivity());
                            recyclerNews.setLayoutManager(new LinearLayoutManager(requireActivity()));
                            recyclerNews.setItemAnimator(new DefaultItemAnimator());
                            recyclerNews.setAdapter(newsAdapter);
                            recyclerNews.setHasFixedSize(true);
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    System.out.println("ERRROR: "+error.getMessage());
                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                }) {
            };

            queue.add(jsonObjectRequest);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder((Context) requireActivity());
            builder.setTitle("Error");
            builder.setMessage("No Internet Connection found. Please connect to the internet and re-open the app.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", (dialogInterface, i) -> ActivityCompat.finishAffinity(requireActivity()));
            builder.create();
            builder.show();
        }
    }
}