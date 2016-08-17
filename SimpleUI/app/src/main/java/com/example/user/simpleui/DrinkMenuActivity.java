package com.example.user.simpleui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class DrinkMenuActivity extends AppCompatActivity implements DrinkOrderDialog.OnFragmentInteractionListener{

    ListView drinkMenuListView;//拿UI元件
    TextView totalTextView;

    String[] names = {"冬瓜紅茶","玫瑰鹽奶蓋紅茶","珍珠紅茶拿鐵","紅茶拿鐵"};
    int[] lPrices = {35,45,55,45};
    int[] mPrices = {25,35,45,35};
    int[] imageIds = {R.drawable.drink1,R.drawable.drink2,R.drawable.drink3,R.drawable.drink4}; //給圖片ID
    int total = 0;
    List<Drink> drinkList = new ArrayList<>();
    ArrayList<DrinkOrder> drinkOrderList = new ArrayList<>(); //儲存飲料訂單
    //若要攜帶到bundle或intent理，須換成arrayList型態
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);
        setdata(); //將資料丟入drink內

        Intent intent = getIntent();
        drinkOrderList = intent.getParcelableArrayListExtra("result");

        drinkMenuListView = (ListView)findViewById(R.id.drinkMenuListView); //藉由這個找id
        totalTextView = (TextView)findViewById(R.id.totalTextView);
        updateTotalTextView();
        drinkMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Drink drink = (Drink)parent.getAdapter().getItem(position);
                /*total+=drink.mPrice;
                                totalTextView.setText(String.valueOf(total));*/
                showDrinkOrderDialog(drink);
            }
        });

       // setupDrinkMenu(); //程式的順序不等於執行順序，下面還沒有setup任何資料，因此沒用
        Log.d("DEBUG", "DrinkMenuActivityOnCreat");
    }

    private void setdata()
    {
        //會把資料放到網路上，因此暫時不用
//        for(int i = 0; i<names.length ;i++)
//        {
//            Drink drink = new Drink();
//            drink.name = names[i];
//            drink.lPrice = lPrices [i];
//            drink.mPrice = mPrices[i];
//            drink.imageId = imageIds[i];
//            drinkList.add(drink); //將drink丟入drinkList內
//        }
        Drink.getQuery().findInBackground(new FindCallback<Drink>() {
            @Override
            public void done(List<Drink> objects, ParseException e) {
                if(e == null) {
                    drinkList = objects;
                    setupDrinkMenu();
                }
            }
        });
    }

    public void done(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("result",drinkOrderList); //將資料丟入intent內
        setResult(RESULT_OK,intent); //當activity退掉時，o不ok；回去狀態ok，回去攜帶的資料為intent
        finish();

    }
    public void cancel(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("result","取消訂單");
        setResult(RESULT_CANCELED,intent);
        finish();
    }

    private void setupDrinkMenu()
    {
        DrinkAdapter adapter = new DrinkAdapter(this,drinkList);
        drinkMenuListView.setAdapter(adapter);
    }

    private void showDrinkOrderDialog(Drink drink)
    {
        DrinkOrder order = null;
        for(DrinkOrder drinkOrder : drinkOrderList)
        {
            if(drinkOrder.drink.getObjectId().equals(drink.getObjectId())) //若有相同訂單，就傳入該筆訂單
            {
                order = drinkOrder;
                break;
            }
        }
        if(order == null) //若沒有相同訂單，須建立
        {
            order = new DrinkOrder(drink);
        }

        FragmentManager fragmentManager = getFragmentManager(); //可以從activity裡拿到東西
        FragmentTransaction ft = fragmentManager.beginTransaction(); //避免每次替換fragment時，會以一個Transaction單位去做，避免卡住；會依據現有狀況，判斷何時做這件事；
        DrinkOrderDialog dialog  = DrinkOrderDialog.newInstance(order); //為了把變數放到BUNDLE內，因為bundle不一定辨識的了每一個

//        ft.replace(R.id.root,dialog); //會替換當前頁面底下的fragment。希望把root底下的fragment換到當前的fragmet
//        ft.commit();
        dialog.show(ft, "DrinkOrderDialog"); //會自動把Transaction作commit
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

    @Override
    public void onDrinkOrderResult(DrinkOrder drinkOrder) //收到資料儲存，並且更新總數的資料
    {
        boolean flag = false; //標記有無找到

        for(int i=0 ; i<drinkOrderList.size();i++)
        {
            if(drinkOrderList.get(i).drink.getObjectId().equals(drinkOrder.drink.getObjectId())) //比對字串用equal
            {
                //不可以order=drinkOrder，因為會沒有下一比連結
                drinkOrderList.set(i,drinkOrder);
                flag = true;
                break;
            }

        }
        if(flag == false)
            drinkOrderList.add(drinkOrder); //將drinkOrder加入drinkOrderList內
        updateTotalTextView();
    }

    private void updateTotalTextView() //將drinkOrderList內的飲料訂單拿出並且做加總
    {
        int total = 0;
        for(DrinkOrder drinkOrder : drinkOrderList)
        {
            total += drinkOrder.lNumber*drinkOrder.drink.getlPrice()+drinkOrder.mNumber*drinkOrder.drink.getmPrice();
        }
        totalTextView.setText(String.valueOf(total));
    }
}
