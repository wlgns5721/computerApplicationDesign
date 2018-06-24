package com.three.cse.computerapplicationdesign.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.three.cse.computerapplicationdesign.MainPageActivity;
import com.three.cse.computerapplicationdesign.R;

public class FinishOrderActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishorder);

        Intent intent = new Intent(this.getIntent());
        final int itemID = intent.getIntExtra("itemID", 0); // item passed

        TextView productname_text = (TextView)findViewById(R.id.productname_text);

        productname_text.setText(Integer.toString(itemID));

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

