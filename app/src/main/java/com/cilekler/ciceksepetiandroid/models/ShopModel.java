package com.cilekler.ciceksepetiandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopModel {

    @SerializedName("name")
    @Expose
    private String shopName;

    @SerializedName("latitudeInteger")
    @Expose
    private String latitudeInteger;

    @SerializedName("latitudeDecimal")
    @Expose
    private String latitudeDecimal;

    @SerializedName("longitudeInteger")
    @Expose
    private String longitudeInteger;

    @SerializedName("longitudeDecimal")
    @Expose
    private String longitudeDecimal;

    @SerializedName("maxOrderLimit")
    @Expose
    private int maxOrderLimit;

    @SerializedName("minOrderLimit")
    @Expose
    private int minOrderLimit;

    private int currentOrderState=0;


    public String getShopName() {
        return shopName;
    }

    public String getLatitudeInteger() {
        return latitudeInteger;
    }

    public String getLatitudeDecimal() {
        return latitudeDecimal;
    }

    public String getLongitudeInteger() {
        return longitudeInteger;
    }

    public String getLongitudeDecimal() {
        return longitudeDecimal;
    }

    public int getCurrentOrderState() {
        return currentOrderState;
    }

    public void setCurrentOrderState(int currentOrderState) {
        this.currentOrderState = currentOrderState;
    }

    public int getMaxOrderLimit() {
        return maxOrderLimit;
    }

    public void setMaxOrderLimit(int maxOrderLimit) {
        this.maxOrderLimit = maxOrderLimit;
    }

    public int getMinOrderLimit() {
        return minOrderLimit;
    }

    public void setMinOrderLimit(int minOrderLimit) {
        this.minOrderLimit = minOrderLimit;
    }
}