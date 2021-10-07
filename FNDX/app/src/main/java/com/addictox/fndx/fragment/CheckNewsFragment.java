package com.addictox.fndx.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

    private Button btnCheckNews;
    private ImageView imgReal;
    private ImageView imgFake;
    private TextView txtRealFake;
    private TextView txtNewsResult;
    private EditText etEnterNews;
    private JSONObject jsonParam;

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

        try {
            jsonParam.put("text", etEnterNews.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnCheckNews.setOnClickListener(view1 -> {
//            runApi();
            if (imgReal.getVisibility() == View.INVISIBLE) {
                imgReal.setVisibility(View.VISIBLE);
                txtRealFake.setText("News Seems Real");
                txtRealFake.setTextColor(Color.rgb(20, 148, 36));
                txtNewsResult.setText("Probability: " + "1.0");
                imgFake.setVisibility(View.INVISIBLE);
            } else {
                imgReal.setVisibility(View.INVISIBLE);
                txtRealFake.setText("News Seems Fake");
                txtRealFake.setTextColor(Color.rgb(239, 25, 10));
                txtNewsResult.setText("Probability: " + "0.1");
                imgFake.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    public void runApi() {
        RequestQueue queue = Volley.newRequestQueue(requireActivity());

        if (new ConnectionManager().isNetworkAvailable(requireActivity())) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "url",
                    jsonParam,
                    response -> {
                        try {
                            String message = response.getJSONObject("message").getString("message");
                            double prob = response.getJSONObject("message").getDouble("probability");
                            String probability = "Probability: " + prob;
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
                            txtNewsResult.setText(probability);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    },
                    error -> {
                        System.out.println("ERRROR: " + error.getMessage());
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