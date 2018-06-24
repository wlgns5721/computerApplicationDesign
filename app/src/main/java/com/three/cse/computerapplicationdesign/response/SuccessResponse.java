package com.three.cse.computerapplicationdesign.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ji Hoon on 2018-06-24.
 */

public class SuccessResponse {
    @SerializedName("isSuccess")
    @Expose
    private String isSuccess=null;


    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getIsSuccess() {

        return isSuccess;
    }
}
