package com.three.cse.computerapplicationdesign.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.three.cse.computerapplicationdesign.MainPageActivity;
import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.response.SearchResult;

public class FinishOrderActivity extends BaseActivity {
    private SearchResult searchResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishorder);

        Intent intent = getIntent();
        searchResult = (SearchResult) intent.getSerializableExtra("product");

        TextView product_text = (TextView)findViewById(R.id.product_text);

        product_text.setText(searchResult.getCount() + " " + searchResult.getProductname() + "(s)");

        Button toMain_btn = (Button)findViewById(R.id.toMain_btn);
        toMain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishOrderActivity.this, MainPageActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

}

