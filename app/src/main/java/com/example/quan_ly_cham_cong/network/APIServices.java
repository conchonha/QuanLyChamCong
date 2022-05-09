package com.example.quan_ly_cham_cong.network;


public class APIServices {
    private static String baseurl= "https://script.google.com/macros/s/AKfycbzW894ehWWeYNenHX6YiUvEqLxh9SQcTwq3pHmCKnpRi5YnmXZRum6OXRaWTqnG1Hc_/";

    public static ApiIpList getService(){
        return APIRetrofitClient.getClient(baseurl).create(ApiIpList.class);
    }
}
