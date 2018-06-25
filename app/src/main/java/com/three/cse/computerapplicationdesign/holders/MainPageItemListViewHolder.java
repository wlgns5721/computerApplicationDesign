package com.three.cse.computerapplicationdesign.holders;

import android.support.v7.widget.RecyclerView;

import com.three.cse.computerapplicationdesign.databinding.ListItemMainPageBinding;

public class MainPageItemListViewHolder extends RecyclerView.ViewHolder{
    ListItemMainPageBinding mBinding;
    public ListItemMainPageBinding getBinding() { return mBinding; }
    public MainPageItemListViewHolder(ListItemMainPageBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
}
