package com.example.quan_ly_cham_cong.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.quan_ly_cham_cong.R;

public class LoadBaoCaoWebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_bao_cao_web_view);
        WebView webView = findViewById(R.id.webview);
        webView.loadUrl("https://docs.google.com/spreadsheets/d/1T0b4cXnnqYg__YhqoPLvtT04fj9B0c_RAPakTjbDGYo/edit?usp=sharing");
    }
}