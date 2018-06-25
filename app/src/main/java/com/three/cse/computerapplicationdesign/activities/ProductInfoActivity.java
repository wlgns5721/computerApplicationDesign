package com.three.cse.computerapplicationdesign.activities;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.response.DetailInfo;


public class ProductInfoActivity  extends BaseActivity {
    private DetailInfo detailInfo;
    private int itemCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productinfo);

        Intent intent = getIntent();
        detailInfo = (DetailInfo) intent.getSerializableExtra("product");

        itemCount = 1;

        Button order_btn = (Button)findViewById(R.id.order_btn);
        Button minus_btn = (Button)findViewById(R.id.minus_btn);
        Button plus_btn = (Button)findViewById(R.id.plus_btn);

        final TextView itemDetail_text = (TextView)findViewById(R.id.itemdetail_text);
        final TextView itemCount_text = (TextView)findViewById(R.id.itemcount_text);
        final TextView itemPrice_text = (TextView)findViewById(R.id.itemprice_text);

        final RadioGroup option_radiogroup = (RadioGroup)findViewById(R.id.option_radiogroup);


        final RadioButton option1_radio = (RadioButton)findViewById(R.id.option1_radio);
        final RadioButton option2_radio = (RadioButton)findViewById(R.id.option2_radio);
        final RadioButton option3_radio = (RadioButton)findViewById(R.id.option3_radio);

        option1_radio.setText(detailInfo.getOption1());
        option2_radio.setText(detailInfo.getOption2());
        option3_radio.setText(detailInfo.getOption3());

        minus_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    if(itemCount < 1)
                        itemCount = 1;
                    else
                        itemCount--;

                    itemCount_text.setText(itemCount);
                    itemPrice_text.setText(Integer.parseInt(detailInfo.getPrice()) * itemCount);
                }
                return true;
            }
        });

        plus_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    itemCount++;
                    itemCount_text.setText(itemCount);
                    itemPrice_text.setText(Integer.parseInt(detailInfo.getPrice()) * itemCount);
                }
                return true;
            }
        });

        itemDetail_text.setText("제품명 : " + detailInfo.getProductname() + "\n" +
                "가격 : " + detailInfo.getPrice() + "\n" +
                "옵션1 : " + detailInfo.getOption1() + " 가격 : +" + detailInfo.getOption1price() + "\n" +
                "옵션2 : " + detailInfo.getOption2() + " 가격 : +" + detailInfo.getOption2price() + "\n" +
                "옵션3 : " + detailInfo.getOption3() + " 가격 : +" + detailInfo.getOption3price() + "\n"
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
