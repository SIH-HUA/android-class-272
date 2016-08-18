package com.example.user.simpleui;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import android.os.Handler;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        TextView noteTxetView = (TextView)findViewById(R.id.noteTextView);
        TextView storeInfoTxetView = (TextView)findViewById(R.id.storeInfoTextView);
        TextView drinkOrderResultsTxetView = (TextView)findViewById(R.id.drinkOrderResultsTextView);
        final TextView latlngTextView = (TextView)findViewById(R.id.latlngTextView);

        Intent intent = getIntent();
        Order order = intent.getParcelableExtra("order");
        noteTxetView.setText(order.getNote());
        storeInfoTxetView.setText(order.getStoreInfo());
        String resultText = "";
        for(DrinkOrder drinkOrder : order.getDrinkOrderList())
        {
            String lNumber = String.valueOf(drinkOrder.getlNumber());
            String mNumber = String.valueOf(drinkOrder.getmNumber());
            String drinkName = drinkOrder.getDrink().getName();
            resultText +=drinkName+" M:"+mNumber+" L:"+lNumber+"\n";
        }
        drinkOrderResultsTxetView.setText(resultText);

        (new GeoCodingTask()).execute(""); //執行方法

    }
}
