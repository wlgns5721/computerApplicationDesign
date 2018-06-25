
package com.three.cse.computerapplicationdesign.utils;

import android.content.Context;
import android.widget.Toast;

import com.three.cse.computerapplicationdesign.requests.OrderInfoRequest;
import com.three.cse.computerapplicationdesign.requests.OrderRequest;
import com.three.cse.computerapplicationdesign.response.GeneralResponse;
import com.three.cse.computerapplicationdesign.response.OrderInfoResponse;
import com.three.cse.computerapplicationdesign.response.SuccessResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ji Hoon on 2018-06-22.
 */

public class RequestManager {
    private static RequestManager instance = null;
    public static RequestManager getInstance() {
        if (instance==null)
            instance = new RequestManager();
        return instance;
    }

    public void requestOrder(String productId, String productName, String productCount, String option1, String option2, String option3) {
        APIClient.getInstance().create(OrderRequest.class).orderProduct(productId, productName, productCount, option1, option2, option3)
                .enqueue(new Callback<SuccessResponse>() {
                    @Override
                    public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                        if(response.body().getIsSuccess()=="1") {
                            //주문 성공했을 때의 부분을 넣어주세요
                        }
                        else {
                            //주문 실패했을 때의 부분을 넣어주세요.
                        }
                    }

                    @Override
                    public void onFailure(Call<SuccessResponse> call, Throwable t) {

                    }
                });
    }


}
