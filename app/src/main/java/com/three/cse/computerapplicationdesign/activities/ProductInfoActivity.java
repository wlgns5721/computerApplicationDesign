package com.three.cse.computerapplicationdesign.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.response.DetailInfo;


public class ProductInfoActivity  extends BaseActivity {
    private DetailInfo detailInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productinfo);

        Intent intent = getIntent();
        detailInfo = (DetailInfo) intent.getSerializableExtra("product");

        Button order_btn = (Button)findViewById(R.id.order_btn);
        TextView itemDetail_text = (TextView)findViewById(R.id.itemdetail_text);
        final RadioGroup option_radiogroup = (RadioGroup)findViewById(R.id.option_radiogroup);

        itemDetail_text.setText("제품명 : " + detailInfo.getProductname() +
                "가격 : " + detailInfo.getPrice()
        );

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = option_radiogroup.getCheckedRadioButtonId();
                RadioButton option_radio = (RadioButton)findViewById(id);
                Intent intent = new Intent(ProductInfoActivity.this, OrderActivity.class);
                intent.putExtra("product", detailInfo);
                finish();
                startActivity(intent);
            }
        });


    }
}
