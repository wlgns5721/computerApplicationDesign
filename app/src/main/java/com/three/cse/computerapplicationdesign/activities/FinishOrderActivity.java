package com.three.cse.computerapplicationdesign.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.three.cse.computerapplicationdesign.MainPageActivity;
import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.response.DetailInfo;

public class FinishOrderActivity extends BaseActivity {
    private DetailInfo orderInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar.setTitle("주문 결과");
        setContentView(R.layout.activity_finishorder);

        Intent intent = getIntent();
        orderInfo = (DetailInfo) intent.getSerializableExtra("product");

        TextView tvProductName = (TextView)findViewById(R.id.tv_order_result_name);
        TextView tvProductCount = (TextView)findViewById(R.id.tv_order_result_count);
        TextView tvProductTotalPrice = (TextView)findViewById(R.id.tv_order_result_price);
        TextView tvSuccess = (TextView)findViewById(R.id.successmessage_text);

        Integer totalPrice = Integer.parseInt(orderInfo.getCount())*Integer.parseInt(orderInfo.getPrice());
        tvProductName.setText("제품 이름 : " + orderInfo.getProductname());
        tvProductCount.setText("수량 : "+orderInfo.getCount()+"개");
        tvProductTotalPrice.setText("총 가격 : "+totalPrice.toString()+"원");

        String isSuccess = intent.getStringExtra("result");
        if(isSuccess.equals("0")) {
            tvSuccess.setText("재고가 부족합니다");
        }
        else if(isSuccess.equals("2")) {
            tvSuccess.setText("서버 오류입니다.");
        }
        Button toMain_btn = (Button)findViewById(R.id.toMain_btn);
        toMain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishOrderActivity.this, MainPageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}

