package com.three.cse.computerapplicationdesign.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ji Hoon on 2018-06-25.
 */

public class DetailInfo implements Serializable {
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
    @SerializedName("option1")
    @Expose
    private String option1;
    @SerializedName("option1price")
    @Expose
    private String option1price;
    @SerializedName("option2")
    @Expose
    private String option2;
    @SerializedName("option2price")
    @Expose
    private String option2price;
    @SerializedName("option3")
    @Expose
    private String option3;
    @SerializedName("option3price")
    @Expose
    private String option3price;

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

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption1price() {
        return option1price;
    }

    public void setOption1price(String option1price) {
        this.option1price = option1price;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption2price() {
        return option2price;
    }

    public void setOption2price(String option2price) {
        this.option2price = option2price;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption3price() {
        return option3price;
    }

    public void setOption3price(String option3price) {
        this.option3price = option3price;
    }
}
