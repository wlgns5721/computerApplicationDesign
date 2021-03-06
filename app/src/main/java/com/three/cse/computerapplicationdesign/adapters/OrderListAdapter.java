package com.three.cse.computerapplicationdesign.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.holders.OrderListViewHolder;
import com.three.cse.computerapplicationdesign.response.OrderInfo;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListViewHolder> {

    private ArrayList<OrderInfo> orderList = new ArrayList<OrderInfo>();

    @NonNull
    @Override
    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View orderListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_product, parent, false);

        return new OrderListViewHolder(orderListView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListViewHolder holder, int position) {
        holder.getProductName().setText(orderList.get(position).getProductname());
        holder.getProductPrice().setText("가격 : "+orderList.get(position).getPrice()+"원");
        holder.getProductQuantity().setText("주문수량 : "+orderList.get(position).getCount()+"개");
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void clearOrderList() {
        orderList.clear();
    }

    public void getOrderList(OrderInfo info) {
        orderList.add(info);
    }

}

