
package com.three.cse.computerapplicationdesign.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.adapters.SaleProductAdapter;
import com.three.cse.computerapplicationdesign.databinding.ActivityProductListBinding;
import com.three.cse.computerapplicationdesign.requests.LoadSaleProductRequest;
import com.three.cse.computerapplicationdesign.response.SaleProductResponse;
import com.three.cse.computerapplicationdesign.utils.APIClient;
import com.three.cse.computerapplicationdesign.utils.RequestManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleProductListActivity extends BaseActivity {
    private ActivityProductListBinding mBinding;
    private SaleProductAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar.setTitle("판매자 페이지");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_list);
        mAdapter = new SaleProductAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lv_product_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mBinding.progressbar.setVisibility(View.GONE);


        mBinding.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaleProductListActivity.this, RegisterSaleProductActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateProductList();
    }

    private void updateProductList() {
        mBinding.progressbar.setVisibility(View.VISIBLE);
        APIClient.getInstance().create(LoadSaleProductRequest.class).getProductList()
                .enqueue(new Callback<SaleProductResponse>() {
                    @Override
                    public void onResponse(Call<SaleProductResponse> call, Response<SaleProductResponse> response) {
                        if (response.isSuccessful()) {
                            mAdapter.clearProductList();
                            for (int i = 0; i < response.body().getSaleProduct().size(); i++)
                                mAdapter.addProduct(response.body().getSaleProduct().get(i));
                            mBinding.progressbar.setVisibility(View.GONE);
                            mAdapter.notifyDataSetChanged();
                        } else
                            Toast.makeText(getApplicationContext(), "서버에 연결할 수 없습니다.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<SaleProductResponse> call, Throwable t) {

                    }
                });
    }



}