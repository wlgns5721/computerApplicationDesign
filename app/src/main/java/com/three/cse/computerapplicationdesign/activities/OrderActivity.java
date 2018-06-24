package com.three.cse.computerapplicationdesign.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class OrderActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = new Intent(this.getIntent());
        final int itemContent = intent.getIntExtra("itemID", 0); // item passed
        final String itemOption = intent.getStringExtra("itemOption"); // item option passed
        TextView itemContent_text = (TextView) findViewById(R.id.itemContent_text);

        itemContent_text.setText("itemID : " + Integer.toString(itemContent) + " itemOption : " + itemOption);

        Button orderFinal_btn = (Button)findViewById(R.id.orderFinal_btn);

        orderFinal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OrderActivity.this, FinishOrderActivity.class);
                intent.putExtra("itemID", itemContent);
                startActivity(intent);
            }
        });
    }

}