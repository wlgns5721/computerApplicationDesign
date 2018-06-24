package com.three.cse.computerapplicationdesign.requests;

import com.three.cse.computerapplicationdesign.response.SearchResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Ji Hoon on 2018-06-24.
 */

public interface SearchRequest {
    @FormUrlEncoded
    @POST("/searchbyname")
    Call<SearchResponse> searchProduct(@Field("name") String name);

}
