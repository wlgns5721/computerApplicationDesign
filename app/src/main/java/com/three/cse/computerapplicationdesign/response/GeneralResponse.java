package com.three.cse.computerapplicationdesign.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ji Hoon on 2018-06-22.
 */

public class GeneralResponse {
    @SerializedName("message")
    @Expose
    private Integer message;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
