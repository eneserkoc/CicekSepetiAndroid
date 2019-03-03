package com.cilekler.ciceksepetiandroid.services;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://cilekler-ciceksepeti-hackathon.azurewebsites.net/api/";

    public static RestInterface getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(RestInterface.class);
    }
}
