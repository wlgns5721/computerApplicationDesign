package com.three.cse.computerapplicationdesign.requests;

import com.three.cse.computerapplicationdesign.response.DetailInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ji Hoon on 2018-06-25.
 */

public interface DetailInfoRequest {
    @GET("/detailinfo/{productid}")
    Call<DetailInfoResponse> requestDetailInfo(@Path("productid")String productId);
}
