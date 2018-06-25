
package com.three.cse.computerapplicationdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ProductQnaActivity extends AppCompatActivity {

    WebView productQnaWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_qna);

        productQnaWebView = (WebView)findViewById(R.id.product_qna_web_view);

        WebSettings productQnaWebSettings = productQnaWebView.getSettings();
        productQnaWebSettings.setAppCacheEnabled(false);

        productQnaWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        productQnaWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        productQnaWebView.loadUrl("hhttp://ddovobb69.dothome.co.kr/productQNA");

    }



}

