package com.three.cse.computerapplicationdesign.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.three.cse.computerapplicationdesign.R;

public class BaseActivity extends AppCompatActivity {
    protected android.support.v7.app.ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));

    }
}
