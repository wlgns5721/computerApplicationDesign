package com.three.cse.computerapplicationdesign.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.three.cse.computerapplicationdesign.activities.ProductInfoActivity;
import com.three.cse.computerapplicationdesign.databinding.ListItemSearchResultBinding;
import com.three.cse.computerapplicationdesign.holders.SearchViewHolder;
import com.three.cse.computerapplicationdesign.requests.DetailInfoRequest;
import com.three.cse.computerapplicationdesign.response.DetailInfoResponse;
import com.three.cse.computerapplicationdesign.response.OrderInfo;
import com.three.cse.computerapplicationdesign.response.SearchResult;
import com.three.cse.computerapplicationdesign.utils.APIClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ji Hoon on 2018-06-24.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private Context mContext;
    private ArrayList<SearchResult> searchResultList = new ArrayList<SearchResult>();
    private ArrayList<Bitmap> imageList = new ArrayList<Bitmap>();
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
    public void onBindViewHolder(@NonNull SearchViewHolder holder, final int position) {
        holder.getBinding().tvSearchProductName.setText(searchResultList.get(position).getProductname());
        holder.getBinding().tvSearchPrice.setText(searchResultList.get(position).getPrice()+"원");
        holder.getBinding().ivSearchImage.setImageBitmap(imageList.get(position));
        holder.getBinding().linearSearchResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIClient.getInstance().create(DetailInfoRequest.class).requestDetailInfo(searchResultList.get(position).getProductid())
                        .enqueue(new Callback<DetailInfoResponse>() {
                            @Override
                            public void onResponse(Call<DetailInfoResponse> call, Response<DetailInfoResponse> response) {
                                Intent intent = new Intent(mContext, ProductInfoActivity.class);
                                intent.putExtra("product",response.body().getDetailInfo().get(0));
//                                intent.putExtra("image",imageList.get(position));
                                mContext.startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<DetailInfoResponse> call, Throwable t) {

                            }
                        });

            }
        });

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
    public void addImage(Bitmap bitmap) {
        imageList.add(bitmap);
    }
}
