package com.three.cse.computerapplicationdesign.requests;

import com.three.cse.computerapplicationdesign.response.GeneralResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ji Hoon on 2018-06-24.
 */

public interface DeleteProductRequest {
    @GET("/deleteproduct/{productid}")
    Call<GeneralResponse> deleteProduct(@Path("productid") String productId);
}
