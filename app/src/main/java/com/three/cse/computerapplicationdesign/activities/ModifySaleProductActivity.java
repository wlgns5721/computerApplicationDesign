package com.three.cse.computerapplicationdesign.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.databinding.ActivityRegisterProductBinding;
import com.three.cse.computerapplicationdesign.requests.ModifySaleProductRequest;
import com.three.cse.computerapplicationdesign.response.GeneralResponse;
import com.three.cse.computerapplicationdesign.response.SaleProduct;
import com.three.cse.computerapplicationdesign.utils.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifySaleProductActivity extends BaseActivity {
    private ActivityRegisterProductBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        actionBar.setTitle("제품 정보 수정");

        SaleProduct product = (SaleProduct) intent.getSerializableExtra("product");
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_register_product);
        mBinding.btnRegisterProduct.setVisibility(View.GONE);
        mBinding.tvDetailImage.setVisibility(View.GONE);
        mBinding.edtRegisterProductName.setText(product.getProductname());
        mBinding.edtRegisterProductPrice.setText(product.getPrice());
        mBinding.edtRegisterProductCount.setText(product.getCount());
        mBinding.edtOption1.setText(product.getOption1());
        mBinding.edtOption1Price.setText(product.getOption1price());
        mBinding.edtOption2.setText(product.getOption2());
        mBinding.edtOption2Price.setText(product.getOption2price());
        mBinding.edtOption3.setText(product.getOption3());
        mBinding.edtOption3Price.setText(product.getOption3price());
        final String productId = product.getProductid();
        mBinding.btnRegisterProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIClient.getInstance().create(ModifySaleProductRequest.class).modifyProduct(Integer.parseInt(productId),
                        mBinding.edtRegisterProductName.getText().toString(),
                        mBinding.edtRegisterProductPrice.getText().toString(),
                        mBinding.edtRegisterProductCount.getText().toString(),
                        mBinding.edtOption1.getText().toString(),
                        mBinding.edtOption1Price.getText().toString(),
                        mBinding.edtOption2.getText().toString(),
                        mBinding.edtOption2Price.getText().toString(),
                        mBinding.edtOption3.getText().toString(),
                        mBinding.edtOption3Price.getText().toString())
                        .enqueue(new Callback<GeneralResponse>() {
                            @Override
                            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                                if(response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "성공적으로 등록되었습니다.", Toast.LENGTH_LONG).show();
                                    finish();
                                    Intent intent = new Intent(ModifySaleProductActivity.this, SaleProductListActivity.class);
                                    startActivity(intent);
                                }
                                else
                                    Toast.makeText(getApplicationContext(),"서버에 연결할 수 없습니다.",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),"fail.",Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}
