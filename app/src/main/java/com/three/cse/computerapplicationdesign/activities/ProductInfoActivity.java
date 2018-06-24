package com.three.cse.computerapplicationdesign.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.response.SearchResult;

import java.io.Serializable;


public class ProductInfoActivity  extends BaseActivity {
    private SearchResult searchResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productinfo);

        Intent intent = getIntent();
        searchResult = (SearchResult) intent.getSerializableExtra("product");

        Button order_btn = (Button)findViewById(R.id.order_btn);

        final RadioGroup option_radiogroup = (RadioGroup)findViewById(R.id.option_radiogroup);

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = option_radiogroup.getCheckedRadioButtonId();
                RadioButton option_radio = (RadioButton)findViewById(id);
                Intent intent = new Intent(ProductInfoActivity.this, OrderActivity.class);
                intent.putExtra("product",searchResult);
                finish();
                startActivity(intent);
            }
        });
    }
}
