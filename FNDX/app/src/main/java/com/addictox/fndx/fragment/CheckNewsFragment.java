package com.addictox.fndx.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.addictox.fndx.R;
import com.addictox.fndx.util.ConnectionManager;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class CheckNewsFragment extends Fragment {

    private ImageView imgFake;
    private TextView txtRealFake;
    private ImageView imgReal;
    private RelativeLayout rlLoading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_news, container, false);

        rlLoading = view.findViewById(R.id.rlLoading);

        EditText etEnterNews = view.findViewById(R.id.etEnterNews);
        Button btnCheckNews = view.findViewById(R.id.btnCheckNews);
        imgReal = view.findViewById(R.id.imgReal);
        imgFake = view.findViewById(R.id.imgFake);
        txtRealFake = view.findViewById(R.id.txtRealFake);

        btnCheckNews.setOnClickListener(view1 -> {
            rlLoading.setVisibility(View.VISIBLE);
            runApi(etEnterNews.getText().toString());
//            if (imgReal.getVisibility() == View.INVISIBLE) {
//                imgReal.setVisibility(View.VISIBLE);
//                txtRealFake.setText("News Seems Real");
//                txtRealFake.setTextColor(Color.rgb(20, 148, 36));
//                txtNewsResult.setText("Probability: " + "1.0");
//                imgFake.setVisibility(View.INVISIBLE);
//            } else {
//                imgReal.setVisibility(View.INVISIBLE);
//                txtRealFake.setText("News Seems Fake");
//                txtRealFake.setTextColor(Color.rgb(239, 25, 10));
//                txtNewsResult.setText("Probability: " + "0.1");
//                imgFake.setVisibility(View.VISIBLE);
//            }
        });

        return view;
    }

    public void runApi(String text) {
        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        JSONObject jsonParam = new JSONObject();

        try { jsonParam.put("text", text); } catch (Exception e) { e.printStackTrace(); }

        if (new ConnectionManager().isNetworkAvailable(requireActivity())) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://marcus.npkn.net/fake-news-2-0",
                    jsonParam,
                    response -> {
                        try {
                            JSONObject data = response.getJSONObject("data").getJSONObject("message");
                            String message = data.getString("message");
                            if (message.equals("News seems fake")) {
                                imgReal.setVisibility(View.INVISIBLE);
                                imgFake.setVisibility(View.VISIBLE);
                                txtRealFake.setTextColor(Color.rgb(239, 25, 10));
                            } else {
                                imgFake.setVisibility(View.INVISIBLE);
                                imgReal.setVisibility(View.VISIBLE);
                                txtRealFake.setTextColor(Color.rgb(20, 148, 36));
                            }
                            txtRealFake.setText(message);
                            rlLoading.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    },
                    error -> {
                        System.out.println("ERRROR: " + error.getMessage());
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