package com.three.cse.computerapplicationdesign.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ji Hoon on 2018-06-22.
 */

public class SaleProductResponse {
    @SerializedName("message")
    @Expose
    private List<SaleProduct> saleProduct = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    public List<SaleProduct> getSaleProduct() {
        return saleProduct;
    }

    public void setSaleProduct(List<SaleProduct> saleProduct) {
        this.saleProduct = saleProduct;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

