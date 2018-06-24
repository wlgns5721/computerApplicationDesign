package com.three.cse.computerapplicationdesign.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ji Hoon on 2018-06-25.
 */

public class DetailInfoResponse {
    @SerializedName("message")
    @Expose
    private List<DetailInfo> message = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    public List<DetailInfo> getDetailInfo() {
        return message;
    }

    public void setDetailInfo(List<DetailInfo> message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
