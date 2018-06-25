package com.three.cse.computerapplicationdesign.response;

import java.io.Serializable;
import java.util.List;

public class MainPageItem implements Serializable {
    private String productid;
    private String productname;
    private String price;
    private String count;

    public MainPageItem(List<String> response) {
        productid = response.get(1);
        productname = response.get(3);
        price = response.get(5);
        count=response.get(7);
    }

    public String getProductid() {
        return productid;
    }

    public String getProductname() {
        return productname;
    }

    public String getPrice() {
        return price;
    }

    public String getCount() {
        return count;
    }
}
