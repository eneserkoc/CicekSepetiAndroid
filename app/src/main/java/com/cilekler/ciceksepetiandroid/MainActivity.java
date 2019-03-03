package com.cilekler.ciceksepetiandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cilekler.ciceksepetiandroid.algorithm.Solution;
import com.cilekler.ciceksepetiandroid.models.OrderModel;
import com.cilekler.ciceksepetiandroid.models.ShopModel;
import com.cilekler.ciceksepetiandroid.services.ApiUtils;
import com.cilekler.ciceksepetiandroid.services.RestInterface;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private RestInterface restInterface;
    List<OrderModel> orderList = new ArrayList<>();
    List<ShopModel> shopList = new ArrayList<>();
    HashMap<Integer, String> results = new HashMap<>();
    ConstraintLayout progressLayout;
    ConstraintLayout mapLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressLayout = findViewById(R.id.progressLayout);
        mapLayout = findViewById(R.id.mapLayout);

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        restInterface = ApiUtils.getAPIService();

        Call<List<ShopModel>> shopCall = restInterface.getShops();
        try {
            shopList = shopCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Call<List<OrderModel>> orderCall = restInterface.getOrders();

        try {
            orderList = orderCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

/*        Call<HashMap<Integer, String>> resultsCall = restInterface.getResults();

        try {
            results = resultsCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        progressLayout.setVisibility(View.INVISIBLE);
        mapLayout.setVisibility(View.VISIBLE);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MainActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        Solution solution = Solution.getInstance(shopList, orderList);

        //solution.assignLimitsOfShops();
        solution.mapProcessedOrders();

        HashMap<Integer, String> assignedOrders = solution.getAssignedOrders();

        LatLng point = null;


        for(OrderModel order : orderList){
            if(assignedOrders.get(order.getOrderNumber()).equals("Yesil")){
                point = new LatLng(Double.parseDouble(order.getLatitudeInteger()+"."+order.getLatitudeDecimal()), Double.parseDouble(order.getLongitudeInteger()+"."+order.getLongitudeDecimal()));
                System.out.println(Double.parseDouble(order.getLatitudeInteger()+"."+order.getLatitudeDecimal())+"///////"+ Double.parseDouble(order.getLongitudeInteger()+"."+order.getLongitudeDecimal()));
                googleMap.addMarker(new MarkerOptions().position(point)
                        .title("Yeşil Magazaya Atanmis Siparis")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            }

            if(assignedOrders.get(order.getOrderNumber()).equals("Kirmizi")){
                point = new LatLng(Double.parseDouble(order.getLatitudeInteger()+"."+order.getLatitudeDecimal()), Double.parseDouble(order.getLongitudeInteger()+"."+order.getLongitudeDecimal()));
                googleMap.addMarker(new MarkerOptions().position(point)
                        .title("Kirmizi  Magazaya Atanmis Siparis")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }

            if(assignedOrders.get(order.getOrderNumber()).equals("Mavi")){
                point = new LatLng(Double.parseDouble(order.getLatitudeInteger()+"."+order.getLatitudeDecimal()), Double.parseDouble(order.getLongitudeInteger()+"."+order.getLongitudeDecimal()));
                googleMap.addMarker(new MarkerOptions().position(point)
                        .title("Mavi Magazaya Atanmis Siparis")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            }
        }

        Double latSum = 0.0;
        Double longSum = 0.0;
        for(ShopModel shop : shopList){
            point = new LatLng(Double.parseDouble(shop.getLatitudeInteger()+"."+shop.getLatitudeDecimal()), Double.parseDouble(shop.getLongitudeInteger()+"."+shop.getLongitudeDecimal()));
            latSum += point.latitude ;
            longSum  += point.longitude;
            if(shop.getShopName().equals("Kirmizi")){
                googleMap.addMarker(new MarkerOptions().position(point)
                        .title("Kırmızı Magaza")
                        .icon(bitmapDescriptorFromVector(MainActivity.this, R.drawable.ic_store_mall_directory_red_24dp)));
            }

            if(shop.getShopName().equals("Mavi")){
                googleMap.addMarker(new MarkerOptions().position(point)
                        .title("Mavi Magaza")
                        .icon(bitmapDescriptorFromVector(MainActivity.this, R.drawable.ic_store_mall_directory_blue_24dp)));
            }

            if(shop.getShopName().equals("Yesil")){
                googleMap.addMarker(new MarkerOptions().position(point)
                        .title("Yesil Magaza")
                        .icon(bitmapDescriptorFromVector(MainActivity.this, R.drawable.ic_store_mall_directory_green_24dp)));
            }



        }


        LatLng centerPoint = new LatLng(latSum/3, longSum/3);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(centerPoint));
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }




}
