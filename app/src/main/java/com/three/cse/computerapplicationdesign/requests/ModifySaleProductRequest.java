package com.three.cse.computerapplicationdesign.requests;

import com.three.cse.computerapplicationdesign.response.GeneralResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Ji Hoon on 2018-06-23.
 */

public interface ModifySaleProductRequest {
    @FormUrlEncoded
    @POST("/registerproductlist")
    Call<GeneralResponse> modifyProduct(@Field("productid") String productId,
                                        @Field("productname") String productName,
                                        @Field("price") String price,
                                        @Field("count") String count,
                                        @Field("option1") String option1,
                                        @Field("option1price") String option1Price,
                                        @Field("option2") String option2,
                                        @Field("option2price") String option2Price,
                                        @Field("option3") String option3,
                                        @Field("option3price") String option3Price);
}



