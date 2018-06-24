package com.three.cse.computerapplicationdesign.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.three.cse.computerapplicationdesign.databinding.ListItemSearchResultBinding;
import com.three.cse.computerapplicationdesign.holders.SearchViewHolder;
import com.three.cse.computerapplicationdesign.response.OrderInfo;
import com.three.cse.computerapplicationdesign.response.SearchResult;

import java.util.ArrayList;

/**
 * Created by Ji Hoon on 2018-06-24.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private Context mContext;
    private ArrayList<SearchResult> searchResultList;
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ListItemSearchResultBinding binding = ListItemSearchResultBinding.inflate(inflater,parent,false);
        SearchViewHolder holder = new SearchViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.getBinding().tvSearchProductName.setText(searchResultList.get(position).getProductname());
        holder.getBinding().tvSearchPrice.setText(searchResultList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return searchResultList.size();
    }

    public void addSearchInfo(SearchResult info) {
        searchResultList.add(info);
    }
    public void clearSearchInfo() {
        searchResultList.clear();
    }
}
