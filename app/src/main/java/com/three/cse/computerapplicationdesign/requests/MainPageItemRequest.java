package com.three.cse.computerapplicationdesign.requests;

import com.three.cse.computerapplicationdesign.response.MainPageItemResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface MainPageItemRequest {
    @FormUrlEncoded
    @GET("/searchproductallbycount")
    Call<MainPageItemResponse> searchProduct();
}
