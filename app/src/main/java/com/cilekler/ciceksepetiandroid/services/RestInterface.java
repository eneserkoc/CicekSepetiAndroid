package com.cilekler.ciceksepetiandroid.services;

import com.cilekler.ciceksepetiandroid.models.OrderModel;
import com.cilekler.ciceksepetiandroid.models.ShopModel;
//import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;


public interface RestInterface {

    @GET("shops")
    Call<List<ShopModel>> getShops();

    @GET("orders")
    Call<List<OrderModel>> getOrders();

    /*@GET("results")
    Call<HashMap<Integer, String>> getResults();*/
}