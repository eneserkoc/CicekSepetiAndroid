package com.cilekler.ciceksepetiandroid.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderModel {

    @SerializedName("orderNumber")
    @Expose
    private int orderNumber;

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

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getLatitudeInteger() {
        return latitudeInteger;
    }

    public void setLatitudeInteger(String latitudeInteger) {
        this.latitudeInteger = latitudeInteger;
    }

    public String getLatitudeDecimal() {
        return latitudeDecimal;
    }

    public void setLatitudeDecimal(String latitudeDecimal) {
        this.latitudeDecimal = latitudeDecimal;
    }

    public String getLongitudeInteger() {
        return longitudeInteger;
    }

    public void setLongitudeInteger(String longitudeInteger) {
        this.longitudeInteger = longitudeInteger;
    }

    public String getLongitudeDecimal() {
        return longitudeDecimal;
    }

    public void setLongitudeDecimal(String longitudeDecimal) {
        this.longitudeDecimal = longitudeDecimal;
    }
}