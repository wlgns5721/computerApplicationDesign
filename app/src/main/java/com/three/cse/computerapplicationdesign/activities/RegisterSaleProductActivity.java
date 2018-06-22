package com.three.cse.computerapplicationdesign.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.databinding.ActivityRegisterProductBinding;
import com.three.cse.computerapplicationdesign.requests.RegisterSaleProductRequest;
import com.three.cse.computerapplicationdesign.response.GeneralResponse;
import com.three.cse.computerapplicationdesign.utils.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterSaleProductActivity extends AppCompatActivity {
    private ActivityRegisterProductBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_product);
        mBinding.btnRegisterProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIClient.getInstance().create(RegisterSaleProductRequest.class).registerProduct(mBinding.edtRegisterProductName.getText().toString(),
                        mBinding.edtRegisterProductPrice.getText().toString(),
                        mBinding.edtRegisterProductCount.getText().toString(),
                        mBinding.edtOption1.getText().toString(),mBinding.edtOption1Price.getText().toString(),
                        mBinding.edtOption2.getText().toString(),mBinding.edtOption2Price.getText().toString(),
                        mBinding.edtOption3.getText().toString(),mBinding.edtOption3Price.getText().toString())
                        .enqueue(new Callback<GeneralResponse>() {
                            @Override
                            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                                Toast.makeText(getApplicationContext(),"성공적으로 등록되었습니다.",Toast.LENGTH_LONG).show();
                                finish();
                                Intent intent = new Intent(RegisterSaleProductActivity.this,SaleProductListActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<GeneralResponse> call, Throwable t) {

                            }
                        });
            }
        });

    }
}
