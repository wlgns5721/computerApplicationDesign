package com.three.cse.computerapplicationdesign.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.response.DetailInfo;


public class ProductInfoActivity extends BaseActivity {
    private DetailInfo detailInfo;
    private int itemCount;
    private int optionPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar.setTitle("상세 정보");
        setContentView(R.layout.activity_productinfo);
        Intent intent = getIntent();
        detailInfo = (DetailInfo) intent.getSerializableExtra("product");
        Bitmap image = (Bitmap) intent.getParcelableExtra("image");

        itemCount = 1;

        Button order_btn = (Button) findViewById(R.id.order_btn);
        Button minus_btn = (Button) findViewById(R.id.minus_btn);
        Button plus_btn = (Button) findViewById(R.id.plus_btn);
        ImageView imageView = (ImageView) findViewById(R.id.item_image);

        final TextView itemDetail_text = (TextView) findViewById(R.id.itemdetail_text);
        final TextView itemCount_text = (TextView) findViewById(R.id.tv_productinfo_count);
        final TextView itemPrice_text = (TextView) findViewById(R.id.tv_productinfo_price);


        final CheckBox option1_radio = (CheckBox) findViewById(R.id.option1_radio);
        final CheckBox option2_radio = (CheckBox) findViewById(R.id.option2_radio);
        final CheckBox option3_radio = (CheckBox) findViewById(R.id.option3_radio);

        option1_radio.setText(detailInfo.getOption1() + " - 가격 : +" + detailInfo.getOption1price() + "원");
        option2_radio.setText(detailInfo.getOption2() + " - 가격 : +" + detailInfo.getOption2price() + "원");
        option3_radio.setText(detailInfo.getOption3() + " - 가격 : +" + detailInfo.getOption3price() + "원");
        itemPrice_text.setText(detailInfo.getPrice() + "원");
        imageView.setImageBitmap(image);
        minus_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (itemCount > 1)
                        itemCount--;
                    itemCount_text.setText(String.valueOf(itemCount));
                    itemPrice_text.setText(String.valueOf(Integer.parseInt(detailInfo.getPrice()) * itemCount) + "원");
                }
                return true;
            }
        });

        plus_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    itemCount++;
                    itemCount_text.setText(String.valueOf(itemCount));
                    itemPrice_text.setText(String.valueOf(Integer.parseInt(detailInfo.getPrice() + optionPrice) * itemCount));
                }
                return true;
            }
        });

        option1_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option1_radio.isChecked())
                    optionPrice += Integer.parseInt(detailInfo.getOption1price());
                else
                    optionPrice -= Integer.parseInt(detailInfo.getOption1price());
                itemPrice_text.setText(String.valueOf(Integer.parseInt(detailInfo.getPrice() + optionPrice) * itemCount));
            }
        });

        option2_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option2_radio.isChecked())
                    optionPrice += Integer.parseInt(detailInfo.getOption2price());
                else
                    optionPrice -= Integer.parseInt(detailInfo.getOption2price());
                itemPrice_text.setText(String.valueOf(Integer.parseInt(detailInfo.getPrice() + optionPrice) * itemCount));
            }
        });

        option3_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option3_radio.isChecked())
                    optionPrice += Integer.parseInt(detailInfo.getOption3price());
                else
                    optionPrice -= Integer.parseInt(detailInfo.getOption3price());
                itemPrice_text.setText(String.valueOf(Integer.parseInt(detailInfo.getPrice() + optionPrice) * itemCount));
            }
        });


        itemDetail_text.setText("제품명 : " + detailInfo.getProductname() + "\n" +
                "가격 : " + detailInfo.getPrice() + "\n" +
                "옵션1 : " + detailInfo.getOption1() + " - 가격 : +" + detailInfo.getOption1price() + "\n" +
                "옵션2 : " + detailInfo.getOption2() + " - 가격 : +" + detailInfo.getOption2price() + "\n" +
                "옵션3 : " + detailInfo.getOption3() + " - 가격 : +" + detailInfo.getOption3price() + "\n"
        );

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductInfoActivity.this, OrderActivity.class);
                detailInfo.setCount(String.valueOf(itemCount));
                if (!option1_radio.isChecked())
                    detailInfo.setOption1("");
                if (!option2_radio.isChecked())
                    detailInfo.setOption2("");
                if (!option3_radio.isChecked())
                    detailInfo.setOption3("");

                intent.putExtra("product", detailInfo);
                finish();
                startActivity(intent);
            }
        });


    }
}
