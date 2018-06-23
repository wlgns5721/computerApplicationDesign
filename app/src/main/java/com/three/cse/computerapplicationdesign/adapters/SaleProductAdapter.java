package com.three.cse.computerapplicationdesign.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.three.cse.computerapplicationdesign.activities.ModifySaleProductActivity;
import com.three.cse.computerapplicationdesign.databinding.ListItemProductBinding;
import com.three.cse.computerapplicationdesign.holders.SaleProductViewHolder;
import com.three.cse.computerapplicationdesign.response.SaleProduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ji Hoon on 2018-06-22.
 */

public class SaleProductAdapter extends RecyclerView.Adapter<SaleProductViewHolder> {
    private ArrayList<SaleProduct> mProductList;
    private Context mContext;
    public SaleProductAdapter() {
        mProductList = new ArrayList<SaleProduct>();
    }

    @NonNull
    @Override
    public SaleProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemProductBinding binding = ListItemProductBinding.inflate(inflater,parent,false);
        SaleProductViewHolder holder = new SaleProductViewHolder(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SaleProductViewHolder holder, final int position) {
        holder.getBinding().btnModifyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaleProduct product = mProductList.get(position);
                Intent intent = new Intent(mContext, ModifySaleProductActivity.class);
                intent.putExtra("product", (Serializable) product);
                mContext.startActivity(intent);
            }
        });
    }

    public void addProduct(SaleProduct product) {
        mProductList.add(product);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }
}
