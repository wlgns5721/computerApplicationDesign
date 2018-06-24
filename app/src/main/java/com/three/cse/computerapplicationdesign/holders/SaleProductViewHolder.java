package com.three.cse.computerapplicationdesign.holders;

import android.app.LauncherActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.three.cse.computerapplicationdesign.databinding.ListItemProductBinding;

/**
 * Created by Ji Hoon on 2018-06-22.
 */

public class SaleProductViewHolder extends RecyclerView.ViewHolder {
    private ListItemProductBinding binding;
    public ListItemProductBinding getBinding() {return binding;}
    public SaleProductViewHolder(ListItemProductBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
