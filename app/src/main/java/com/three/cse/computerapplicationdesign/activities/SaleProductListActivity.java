package com.three.cse.computerapplicationdesign.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.adapters.SaleProductAdapter;
import com.three.cse.computerapplicationdesign.databinding.ActivityProductListBinding;
import com.three.cse.computerapplicationdesign.requests.LoadSaleProductRequest;
import com.three.cse.computerapplicationdesign.response.SaleProductResponse;
import com.three.cse.computerapplicationdesign.utils.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleProductListActivity extends AppCompatActivity {
    private ActivityProductListBinding mBinding;
    private SaleProductAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_list);
        mAdapter = new SaleProductAdapter();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.lv_product_list);

        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        APIClient.getInstance().create(LoadSaleProductRequest.class).getProductList()
                .enqueue(new Callback<SaleProductResponse>() {
                    @Override
                    public void onResponse(Call<SaleProductResponse> call, Response<SaleProductResponse> response) {

                        if(response.body()!=null) {
                            for (int i = 0; i < response.body().getSaleProduct().size(); i++)
                                mAdapter.addProduct(response.body().getSaleProduct().get(i));
                        }
                        else
                            Toast.makeText(getApplicationContext(),"서버에 연결할 수 없습니다.",Toast.LENGTH_LONG).show();
                        //UI Thread에서 notifyDataSetChanged를 호출해야 정상적으로 화면 갱신
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.notifyDataSetChanged();
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<SaleProductResponse> call, Throwable t) {

                    }
                });
        mBinding.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaleProductListActivity.this, RegisterSaleProductActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
