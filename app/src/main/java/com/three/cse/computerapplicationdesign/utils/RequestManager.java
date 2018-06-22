package com.three.cse.computerapplicationdesign.utils;

/**
 * Created by Ji Hoon on 2018-06-22.
 */

public class RequestManager {
    private static RequestManager instance = null;
    public static RequestManager getInstance() {
        if (instance==null)
            instance = new RequestManager();
        return instance;
    }



}
