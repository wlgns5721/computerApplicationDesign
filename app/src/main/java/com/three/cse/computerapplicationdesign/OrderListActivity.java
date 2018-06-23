package com.three.cse.computerapplicationdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OrderListActivity extends AppCompatActivity {

    TextView productID;
    TextView productName;
    TextView productPrice;
    TextView productQuantity;

    Button orderListResultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

//        productID = (TextView)findViewById(R.id.order_list_product_id);
//        productName = (TextView)findViewById(R.id.order_list_product_name);
//        productPrice = (TextView)findViewById(R.id.order_list_product_price);
//        productQuantity = (TextView)findViewById(R.id.order_list_product_quantity);

        orderListResultButton = (Button)findViewById(R.id.order_list_result);


    }

}
