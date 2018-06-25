package com.three.cse.computerapplicationdesign.requests;

import com.three.cse.computerapplicationdesign.response.GeneralResponse;
import com.three.cse.computerapplicationdesign.response.SuccessResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Ji Hoon on 2018-06-24.
 */

public interface OrderRequest {
    @FormUrlEncoded
    @POST("/order")
    Call<SuccessResponse> orderProduct(@Field("productid") String productId,
                                       @Field("productname") String productName,
                                       @Field("count") String count,
                                       @Field("option1") String option1,
                                       @Field("option2") String option2,
                                       @Field("option3")  String option3);


}
