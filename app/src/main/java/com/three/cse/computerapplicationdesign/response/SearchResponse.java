package com.three.cse.computerapplicationdesign.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ji Hoon on 2018-06-24.
 */

public class SearchResponse {
    @SerializedName("message")
    @Expose
    private List<List<String>> message = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    public List<List<String>> getMessage() {
        return message;
    }

    public void setMessage(List<List<String>> message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
