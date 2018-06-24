package com.three.cse.computerapplicationdesign.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ji Hoon on 2018-06-24.
 */

public class OrderInfoResponse {
    @SerializedName("message")
    @Expose
    private List<OrderInfo> message = null;

    @SerializedName("status")
    @Expose
    private Integer status;
    public List<OrderInfo> getMessage() {
        return message;
    } // 제품 주문 정보 가져옴

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
