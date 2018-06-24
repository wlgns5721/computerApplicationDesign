package com.three.cse.computerapplicationdesign.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ji Hoon on 2018-06-24.
 */

public class SearchResult implements Serializable{
    private String productid;
    private String productname;
    private String price;
    private String count;

    public SearchResult(List<String> response) {
        productid = response.get(1);
        productname = response.get(3);
        price = response.get(5);
        count=response.get(7);
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
