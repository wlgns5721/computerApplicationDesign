package com.three.cse.computerapplicationdesign.holders;

import android.app.LauncherActivity;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.three.cse.computerapplicationdesign.R;


public class OrderListViewHolder extends RecyclerView.ViewHolder {
    TextView productName;
    TextView productId;
    TextView productPrice;
    TextView productQuantity;

    public OrderListViewHolder(View view) {
        super(view);
        productName = (TextView)view.findViewById(R.id.order_list_product_name);
        productId = (TextView)view.findViewById(R.id.order_list_product_id);
        productPrice = (TextView)view.findViewById(R.id.order_list_product_price);
        productQuantity = (TextView)view.findViewById(R.id.order_list_product_quantity);
    }

    public TextView getProductName() {
        return productName;
    }

    public TextView getProductId() {
        return productId;
    }

    public TextView getProductPrice() {
        return productPrice;
    }

    public TextView getProductQuantity() {
        return productQuantity;
    }
}
