package com.three.cse.computerapplicationdesign.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.three.cse.computerapplicationdesign.databinding.ListItemSearchResultBinding;

/**
 * Created by Ji Hoon on 2018-06-24.
 */

public class SearchViewHolder extends RecyclerView.ViewHolder{
    ListItemSearchResultBinding mBinding;
    public ListItemSearchResultBinding getBinding() { return mBinding; }
    public SearchViewHolder(ListItemSearchResultBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
}
