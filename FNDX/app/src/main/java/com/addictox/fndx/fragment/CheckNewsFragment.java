package com.addictox.fndx.fragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.addictox.fndx.R;

public class CheckNewsFragment extends Fragment {

    private Button btnCheckNews;
    private ImageView imgReal;
    private ImageView imgFake;
    private TextView txtRealFake;
    private TextView txtNewsResult;
    private EditText etEnterNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_news, container, false);

        etEnterNews = view.findViewById(R.id.etEnterNews);
        btnCheckNews = view.findViewById(R.id.btnCheckNews);
        imgReal = view.findViewById(R.id.imgReal);
        imgFake = view.findViewById(R.id.imgFake);
        txtRealFake = view.findViewById(R.id.txtRealFake);
        txtNewsResult = view.findViewById(R.id.txtNewsResult);

        btnCheckNews.setOnClickListener(view1 -> {
            if(imgReal.getVisibility() == View.INVISIBLE){
                imgReal.setVisibility(View.VISIBLE);
                txtRealFake.setText("News Seems Real");
                txtRealFake.setTextColor(Color.rgb(20,148,36));
                txtNewsResult.setText("Probability: "+"1.0");
                imgFake.setVisibility(View.INVISIBLE);
            } else {
                imgReal.setVisibility(View.INVISIBLE);
                txtRealFake.setText("News Seems Fake");
                txtRealFake.setTextColor(Color.rgb(239,25,10));
                txtNewsResult.setText("Probability: "+"0.1");
                imgFake.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }
}