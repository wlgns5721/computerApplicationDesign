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
import com.three.cse.computerapplicationdesign.databinding.ListItemMainPageBinding;
import com.three.cse.computerapplicationdesign.holders.MainPageItemListViewHolder;
import com.three.cse.computerapplicationdesign.requests.DetailInfoRequest;
import com.three.cse.computerapplicationdesign.response.DetailInfoResponse;
import com.three.cse.computerapplicationdesign.response.MainPageItem;
import com.three.cse.computerapplicationdesign.utils.APIClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPageItemListAdapter extends RecyclerView.Adapter<MainPageItemListViewHolder> {
    private Context mContext;
    private ArrayList<MainPageItem> MainPageItemList = new ArrayList<MainPageItem>();
    private ArrayList<Bitmap> imageList = new ArrayList<Bitmap>();
    @NonNull
    @Override
    public MainPageItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ListItemMainPageBinding binding = ListItemMainPageBinding.inflate(inflater,parent,false);
        MainPageItemListViewHolder holder = new MainPageItemListViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainPageItemListViewHolder holder, final int position) {
        holder.getBinding().tvMainPageItemName.setText(MainPageItemList.get(position).getProductname());
        holder.getBinding().tvMainPageItemPrice.setText(MainPageItemList.get(position).getPrice()+"원");
        holder.getBinding().ivMainPageItemImage.setImageBitmap(imageList.get(position));
        final Bitmap bitmap = imageList.get(position);
        holder.getBinding().linearListItemMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIClient.getInstance().create(DetailInfoRequest.class).requestDetailInfo(MainPageItemList.get(position).getProductid())
                        .enqueue(new Callback<DetailInfoResponse>() {
                            @Override
                            public void onResponse(Call<DetailInfoResponse> call, Response<DetailInfoResponse> response) {
                                Intent intent = new Intent(mContext, ProductInfoActivity.class);
                                intent.putExtra("product",response.body().getDetailInfo().get(0));

                                intent.putExtra("image",(Bitmap)bitmap);
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
        return MainPageItemList.size();
    }

    public void addMainPageItemInfo(MainPageItem info) {
        MainPageItemList.add(info);
    }
    public void clearSearchInfo() {
        MainPageItemList.clear();
    }
    public void addImage(Bitmap bitmap) {
        imageList.add(bitmap);
    }
}


