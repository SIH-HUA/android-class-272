package com.example.user.simpleui;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import android.os.Handler;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class OrderDetailActivity extends AppCompatActivity implements GeoCodingTask.GeocodingCallBack {

    TextView latlngTextView;
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        TextView noteTxetView = (TextView)findViewById(R.id.noteTextView);
        TextView storeInfoTxetView = (TextView)findViewById(R.id.storeInfoTextView);
        TextView drinkOrderResultsTxetView = (TextView)findViewById(R.id.drinkOrderResultsTextView);
        latlngTextView = (TextView)findViewById(R.id.latlngTextView); //會直接指到外面class的變數

        Intent intent = getIntent();
        Order order = intent.getParcelableExtra("order");
        noteTxetView.setText(order.getNote());
        storeInfoTxetView.setText(order.getStoreInfo());
//        String address = order.getStoreInfo().split(",")[1]; //去網路上拿地址
        String resultText = "";
        for(DrinkOrder drinkOrder : order.getDrinkOrderList())
        {
            String lNumber = String.valueOf(drinkOrder.getlNumber());
            String mNumber = String.valueOf(drinkOrder.getmNumber());
            String drinkName = drinkOrder.getDrink().getName();
            resultText +=drinkName+" M:"+mNumber+" L:"+lNumber+"\n";
        }
        drinkOrderResultsTxetView.setText(resultText);

        MapFragment fragment = (MapFragment)getFragmentManager().findFragmentById(R.id.mapFragment); //去拿mapFragment
        fragment.getMapAsync(new OnMapReadyCallback() {//從google拿到地圖;
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                (new GeoCodingTask(OrderDetailActivity.this)).execute("台北市大安區羅斯福路四段一號"); //執行方法，假設googlemap可ˊ執行的狀態下
            }
        });


    }

    @Override
    public void done(double[] latlng) {
        if(latlng != null)
        {
            String latlngString = String.valueOf(latlng[0]+","+latlng[1]);
            latlngTextView.setText(latlngString);
            LatLng latLng = new LatLng(latlng[0],latlng[1]); //移動地圖得視窗到店家位置 ，先緯度在經度
            //相機放大到17倍
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,17); //移動map的相機，可移動到我們給定的位置
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("NTU"); //使用者點到這個marker之後會顯是什麼東西
            map.moveCamera(cameraUpdate);
            map.addMarker(markerOptions); //把marker加入到map
        }
    }
}
