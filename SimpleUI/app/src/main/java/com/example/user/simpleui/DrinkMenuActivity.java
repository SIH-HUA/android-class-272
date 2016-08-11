package com.example.user.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DrinkMenuActivity extends AppCompatActivity {

    ListView drinkMenuListView;//拿UI元件
    TextView totalTextView;

    String[] names = {"冬瓜紅茶","玫瑰鹽奶蓋紅茶","珍珠紅茶拿鐵","紅茶拿鐵"};
    int[] lPrices = {35,45,55,45};
    int[] mPrices = {25,35,45,35};
    int[] imageIds = {R.drawable.drink1,R.drawable.drink2,R.drawable.drink3,R.drawable.drink4}; //給圖片ID

    List<Drink> drinkList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);
        setdata(); //將資料丟入drink內
        drinkMenuListView = (ListView)findViewById(R.id.drinkMenuListView); //藉由這個找id
        totalTextView = (TextView)findViewById(R.id.totalTextView);

        setupDrinkMenu();
        Log.d("DEBUG", "DrinkMenuActivityOnCreat");
    }

    private void setdata()
    {
        for(int i = 0; i<names.length ;i++)
        {
            Drink drink = new Drink();
            drink.name = names[i];
            drink.lPrice = lPrices [i];
            drink.mPrice = mPrices[i];
            drink.imageId = imageIds[i];
            drinkList.add(drink); //將drink丟入drinkList內
        }
    }

    private void setupDrinkMenu()
    {
        DrinkAdapter adapter = new DrinkAdapter(this,drinkList);
        drinkMenuListView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("REBUG", "DrinkMenuActivityOnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("REBUG", "DrinkMenuActivityOnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("REBUG", "DrinkMenuActivityOnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("REBUG", "DrinkMenuActivityOnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("REBUG", "DrinkMenuActivityOnDestroy");
    }
}
