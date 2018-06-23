package com.three.cse.computerapplicationdesign.activities;

import android.app.Activity;
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
import com.three.cse.computerapplicationdesign.requests.RegisterSaleProductRequest;
import com.three.cse.computerapplicationdesign.requests.UploadImageRequest;
import com.three.cse.computerapplicationdesign.response.GeneralResponse;
import com.three.cse.computerapplicationdesign.utils.APIClient;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class RegisterSaleProductActivity extends BaseActivity {
    private ActivityRegisterProductBinding mBinding;
    private String mFilePath = null;
    private final int PICK_IMAGE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar.setTitle("제품 정보 등록");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_product);

        mBinding.btnRegisterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

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
                                if(response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "성공적으로 등록되었습니다.", Toast.LENGTH_LONG).show();
                                    finish();
                                    if(mFilePath==null)
                                        mFilePath = "drawable://" + R.drawable.default_image;
                                    File file = new File(mFilePath);
                                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                                    MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
                                    RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "upload_test");
                                    APIClient.getInstance().create(UploadImageRequest.class).uploadImage(body,description,"1")
                                            .enqueue(new Callback<GeneralResponse>() {
                                                @Override
                                                public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                                                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                                                }

                                                @Override
                                                public void onFailure(Call<GeneralResponse> call, Throwable t) {
                                                    Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_LONG).show();
                                                }
                                            });

                                    Intent intent = new Intent(RegisterSaleProductActivity.this, SaleProductListActivity.class);
                                    startActivity(intent);
                                }
                                else
                                    Toast.makeText(getApplicationContext(),"서버에 연결할 수 없습니다.",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<GeneralResponse> call, Throwable t) {

                            }
                        });
            }
        });

    }

    public void uploadImage() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE && resultCode== Activity.RESULT_OK) {
            mFilePath = data.getData().getPath();
        }
    }
}
