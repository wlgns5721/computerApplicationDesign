package com.three.cse.computerapplicationdesign.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ji Hoon on 2018-06-24.
 */

public class SearchResult {
    @SerializedName("productid")
    @Expose
    private String productid;
    @SerializedName("productname")
    @Expose
    private String productname;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("count")
    @Expose
    private String count;

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
