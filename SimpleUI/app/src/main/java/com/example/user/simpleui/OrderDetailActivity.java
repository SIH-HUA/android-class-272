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

        final Handler handler = new Handler(new Handler.Callback() //當有人傳訊息給handler，handler會叫mainthread去做事情
        {
            @Override
            public boolean handleMessage(Message msg) {
                latlngTextView.setText("123456"); //更改UI的事情只能用handler叫mainthread做，不能直接放到thread\做
                return false;
            }
        }); //屬於android

        Thread thread = new Thread(new Runnable()//Runnable表示一件事情，一個Thread可以做很多件事情
        {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000); // 10秒
                    handler.sendMessage(new Message());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //thread.run(); // 會先等MainThread作，在做我們的thread會延遲10秒，會變黑10秒
        thread.start(); //在background做，因此畫面不會變黑

    }
}
