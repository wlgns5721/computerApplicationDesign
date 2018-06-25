package com.three.cse.computerapplicationdesign.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.databinding.ActivityRegisterProductBinding;
import com.three.cse.computerapplicationdesign.requests.RegisterSaleProductRequest;
import com.three.cse.computerapplicationdesign.requests.UploadImageRequest;
import com.three.cse.computerapplicationdesign.response.GeneralResponse;
import com.three.cse.computerapplicationdesign.response.SuccessResponse;
import com.three.cse.computerapplicationdesign.utils.APIClient;
import com.three.cse.computerapplicationdesign.utils.RequestManager;

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
                requestPermission();
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        mBinding.btnRegisterProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIClient.getInstance().create(RegisterSaleProductRequest.class).registerProduct(mBinding.edtRegisterProductName.getText().toString(),
                        mBinding.edtRegisterProductPrice.getText().toString(),
                        mBinding.edtRegisterProductCount.getText().toString(),
                        mBinding.edtOption1.getText().toString(), mBinding.edtOption1Price.getText().toString(),
                        mBinding.edtOption2.getText().toString(), mBinding.edtOption2Price.getText().toString(),
                        mBinding.edtOption3.getText().toString(), mBinding.edtOption3Price.getText().toString())
                        .enqueue(new Callback<GeneralResponse>() {
                            @Override
                            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "성공적으로 등록되었습니다.", Toast.LENGTH_LONG).show();
                                    finish();
                                    if (mFilePath == null)
                                        mFilePath = "drawable://" + R.drawable.default_image;
                                    File file = new File(mFilePath);
                                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                                    MultipartBody.Part body = MultipartBody.Part.createFormData("userfile", file.getName(), reqFile);
                                    RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "upload_test");
                                    APIClient.getInstance().create(UploadImageRequest.class).uploadImage(body, response.body().getMessage())
                                            .enqueue(new Callback<SuccessResponse>() {
                                                @Override
                                                public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                                                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                                                }

                                                @Override
                                                public void onFailure(Call<SuccessResponse> call, Throwable t) {
                                                    Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                                                }
                                            });

                                    Intent intent = new Intent(RegisterSaleProductActivity.this, SaleProductListActivity.class);
                                    startActivity(intent);
                                } else
                                    Toast.makeText(getApplicationContext(), "서버에 연결할 수 없습니다.", Toast.LENGTH_LONG).show();
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

    private String getRealPathFromURI(Uri contentUri) {
        if (contentUri.getPath().startsWith("/storage")) {
            return contentUri.getPath();
        }
        String id = DocumentsContract.getDocumentId(contentUri).split(":")[1];
        String[] columns = {MediaStore.Files.FileColumns.DATA};
        String selection = MediaStore.Files.FileColumns._ID + " = " + id;
        Cursor cursor = getContentResolver().query(MediaStore.Files.getContentUri("external"), columns, selection, null, null);
        try {
            int columnIndex = cursor.getColumnIndex(columns[0]);
            if (cursor.moveToFirst()) {
                return cursor.getString(columnIndex);
            }
        } finally {
            cursor.close();
        }
        return null;
    }

    private boolean requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // 이 권한을 필요한 이유를 설명해야하는가?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // 다이어로그같은것을 띄워서 사용자에게 해당 권한이 필요한 이유에 대해 설명합니다

                // 해당 설명이 끝난뒤 requestPermissions()함수를 호출하여 권한허가를 요청해야 합니다

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                // 필요한 권한과 요청 코드를 넣어서 권한허가요청에 대한 결과를 받아야 합니다

            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            mFilePath = getRealPathFromURI(uri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 권한 허가
                    // 해당 권한을 사용해서 작업을 진행할 수 있습니다
                } else {
                    // 권한 거부
                    // 사용자가 해당권한을 거부했을때 해주어야 할 동작을 수행합니다
                }
                return;
        }
    }

}
