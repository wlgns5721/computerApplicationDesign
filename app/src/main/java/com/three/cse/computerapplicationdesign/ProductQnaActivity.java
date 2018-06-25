package com.three.cse.computerapplicationdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ProductQnaActivity extends AppCompatActivity {

    private WebView productQnaWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_qna);

        productQnaWebView = (WebView) findViewById(R.id.product_qna_web_view);
        productQnaWebView.setWebViewClient(new WebViewClient());
        productQnaWebView.loadUrl("http://ddovobb69.dothome.co.kr/productqna");
    }



}
