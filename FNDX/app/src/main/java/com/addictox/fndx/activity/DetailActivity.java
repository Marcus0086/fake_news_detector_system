package com.addictox.fndx.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.addictox.fndx.R;

public class DetailActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progress;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String url = getIntent().getStringExtra("url");

        if (url != null) {
            webView = findViewById(R.id.wvDetailActivity);
            progress = findViewById(R.id.progress_bar);
            WebSettings webSettings = webView.getSettings();

            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true);

            webView.setWebViewClient(new WebViewClient());
            webView.setWebChromeClient(new WebChromeClient());
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    progress.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                }
            });
            webView.loadUrl(url);
        }
    }
}
