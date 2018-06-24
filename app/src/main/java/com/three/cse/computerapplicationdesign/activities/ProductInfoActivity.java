package com.three.cse.computerapplicationdesign.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;


public class ProductInfoActivity  extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productinfo);

        Intent intent = new Intent(this.getIntent());
        final int itemID = intent.getIntExtra("itemID", 0); // item passed
        TextView itemText = (TextView)findViewById(R.id.itemID_text);

        itemText.setText(Integer.toString(itemID));

        Button order_btn = (Button)findViewById(R.id.order_btn);

        final RadioGroup option_radiogroup = (RadioGroup)findViewById(R.id.option_radiogroup);

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = option_radiogroup.getCheckedRadioButtonId();
                RadioButton option_radio = (RadioButton)findViewById(id);
                Intent intent = new Intent(ProductInfoActivity.this, OrderActivity.class);
                intent.putExtra("itemID", itemID); //item passed
                intent.putExtra("itemOption", String.valueOf(option_radio.getText()));
                startActivity(intent);
            }
        });
    }
}
