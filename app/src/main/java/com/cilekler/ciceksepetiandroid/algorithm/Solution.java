package com.cilekler.ciceksepetiandroid.algorithm;



import com.cilekler.ciceksepetiandroid.models.OrderCase;
import com.cilekler.ciceksepetiandroid.models.OrderModel;
import com.cilekler.ciceksepetiandroid.models.ShopModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Solution {

    private static Solution solution_instance = null;

    private List<ShopModel> shopList;
    private List<OrderModel> orderList;
    private ArrayList<OrderCase> orderCases = new ArrayList<>();
    private HashMap<Integer, String> assignedOrders = new HashMap<>();
    private int sumMinLimit = 0 ;

    private Solution(List<ShopModel> shopList, List<OrderModel> orderList) {
        this.shopList = shopList;
        this.orderList = orderList;
        for(ShopModel shop : shopList){
            sumMinLimit += shop.getMinOrderLimit();
        }
    }

    public static Solution getInstance(List<ShopModel> shopList, List<OrderModel> orderList)
    {
        if (solution_instance == null)
            solution_instance = new Solution(shopList, orderList);

        return solution_instance;
    }

    public void mapProcessedOrders(){

        for(OrderModel order : orderList){
            for(ShopModel shop : shopList) {
                orderCases.add(new OrderCase(shop, order.getOrderNumber(), calculateDistance(shop, order)));
            }
        }

        Collections.sort(orderCases, new Comparator<OrderCase>() {
            @Override
            public int compare(OrderCase o1, OrderCase o2) {
                return Double.compare(o1.getDistance(), o2.getDistance());
            }
        });

        int leftOrderNumber = orderList.size();
        for(OrderCase orderCase : orderCases){

            if(assignedOrders.get(orderCase.getOrderNumber()) == null) {

                if (orderCase.getShop().getCurrentOrderState() < orderCase.getShop().getMaxOrderLimit() && leftOrderNumber > (sumMinLimit - orderCase.getShop().getMaxOrderLimit())) {
                    assignedOrders.put(orderCase.getOrderNumber(), orderCase.getShop().getShopName());
                    leftOrderNumber--;
                    orderCase.getShop().setCurrentOrderState(orderCase.getShop().getCurrentOrderState() + 1);
                }
            }
        }


    }

    public double calculateDistance(ShopModel shop, OrderModel order){

        Double shopLat = Double.parseDouble(shop.getLatitudeInteger()+"."+shop.getLatitudeDecimal());
        Double orderLat = Double.parseDouble(order.getLatitudeInteger()+"."+order.getLatitudeDecimal());
        Double shopLong = Double.parseDouble(shop.getLongitudeInteger()+"."+shop.getLongitudeDecimal());
        Double orderLong = Double.parseDouble(order.getLongitudeInteger()+"."+order.getLongitudeDecimal());
        return Math.pow(shopLat - orderLat, 2) + Math.pow(shopLong - orderLong, 2);
    }

    public HashMap<Integer, String> getAssignedOrders() {
        return assignedOrders;
    }
}





