package com.three.cse.computerapplicationdesign.requests;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ji Hoon on 2018-06-25.
 */

public interface DownloadImageRequest {
    @GET("/images/{productid}")
    Call<ResponseBody> downloadImageRequest(@Path("productid") String productId);
}
