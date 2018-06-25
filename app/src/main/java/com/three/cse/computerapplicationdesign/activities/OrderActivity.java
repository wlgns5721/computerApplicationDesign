package com.three.cse.computerapplicationdesign.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.requests.OrderRequest;
import com.three.cse.computerapplicationdesign.response.DetailInfo;
import com.three.cse.computerapplicationdesign.response.SearchResult;
import com.three.cse.computerapplicationdesign.response.SuccessResponse;
import com.three.cse.computerapplicationdesign.utils.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends BaseActivity {
    private DetailInfo product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = new Intent(this.getIntent());
        product = (DetailInfo)intent.getSerializableExtra("product");

        TextView itemName_text = (TextView) findViewById(R.id.itemname_text);
        TextView itemCount_text = (TextView) findViewById(R.id.itemcount_text);
        TextView itemOption_text = (TextView) findViewById(R.id.itemoption_text);
        TextView itemPrice_text = (TextView) findViewById(R.id.itemprice_text);
        String option = "";
        itemName_text.setText(product.getProductname());
        itemCount_text.setText(product.getCount()+"원");
        if(!product.getOption1().equals(""))
            option=product.getOption1();
        else if(!product.getOption2().equals(""))
            option=product.getOption2();
        else if(!product.getOption3().equals(""))
            option=product.getOption3();

        itemOption_text.setText(option);
        itemPrice_text.setText(product.getPrice());

        Button orderFinal_btn = (Button)findViewById(R.id.orderFinal_btn);

        orderFinal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                APIClient.getInstance().create(OrderRequest.class).orderProduct(product.getProductid(),
                        product.getProductname(),
                        product.getCount(),
                        product.getOption1(),
                        product.getOption2(),
                        product.getOption3())
                        .enqueue(new Callback<SuccessResponse>() {
                            @Override
                            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                                Intent finishIntent = new Intent(OrderActivity.this,FinishOrderActivity.class);
                                finishIntent.putExtra("product",product);
                                finishIntent.putExtra("result",response.body().getIsSuccess());
                                finish();
                                startActivity(finishIntent);
                            }

                            @Override
                            public void onFailure(Call<SuccessResponse> call, Throwable t) {

                            }
                        });
            }
        });
    }

}