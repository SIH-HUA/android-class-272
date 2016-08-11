package com.example.user.simpleui;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE_DRINK_MENU_ACTIVITY = 0;

    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    ListView listView;
    Spinner spinner;

    String drink = "Black Tea"; //初始飲料

    List<Order> data = new ArrayList<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView); //將東西找出來，須強制轉型，因型態不同
        editText = (EditText) findViewById(R.id.editText);
        radioGroup = (RadioGroup) findViewById(R.id.radio123);
        listView = (ListView) findViewById(R.id.listView);
        spinner = (Spinner) findViewById(R.id.spinner);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.blackTeaRadioButton) {
                    drink = "Black Tea";
                }
                if (checkedId == R.id.greenTeaRadioButton) {
                    drink = "Green Tea";
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order order = (Order) parent.getAdapter().getItem(position);
                Toast.makeText(MainActivity.this, order.note, Toast.LENGTH_LONG).show();//若直接打this會直接指向包住的listener，不會指向Main activity //出現顯示框
            }
        }); //當item被點選時會觸發的事件

        setupListView();
        setupSpinner();

        Log.d("REBUG", "MainActivityOnCreate");

    }

    private void setupListView() {
        //String[] data = new String[]{"1","2","3","4","5","6","7","8"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data); //轉換成每筆有用的ITEM
        /*List<Map<String,String>> mapList = new ArrayList<>();   //第一個客製化itemView的方法
        for(Order order : data)
        {
            Map<String,String> item = new HashMap<>();
            item.put("note",order.note); //拿出order的資訊
            item.put("storeInfo",order.storeInfo);
            item.put("drink",order.drink);
            mapList.add(item); // 將東西丟入maplist
        }

        String[] from = {"note","storeInfo","drink"}; //從哪拿來
        int[] to = {R.id.noteTextView,R.id.storeInfoTextView,R.id.drinkTextView};
        SimpleAdapter adapter = new SimpleAdapter(this,mapList,R.layout.listview_order_item,from,to); //所有資料，item，從哪拿，拿去哪*/
        OrderAdapter adapter = new OrderAdapter(this, data); //是一個activity；資料
        listView.setAdapter(adapter);
    }

    private void setupSpinner() {
        String[] storeInfo = getResources().getStringArray(R.array.storeInfo); //重resource裡拿出定義檔
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, storeInfo);
        spinner.setAdapter(adapter);
    }

    public void click(View view) //因為view是所有原件的parent，因此用view
    {
        String text = editText.getText().toString(); //將轉成字串，去除其他屬性
        String result = text + " Order: " + drink;
        textView.setText(result);
        editText.setText(" ");
        Order order = new Order();
        order.note = text;
        order.drink = drink;
        order.storeInfo = (String) spinner.getSelectedItem(); //物件型態轉String，給被選取的item資料
        data.add(order); //將東西丟入list內
        setupListView(); ///重整listview

    }

    public void goToMenu(View view) //誰呼叫誰進來
    {
        Intent intent = new Intent(); //呼叫第二個頁面，兩個頁面互傳東西
        intent.setClass(this, DrinkMenuActivity.class);//當前的activity；想呼叫到哪個activity
        startActivityForResult(intent, REQUEST_CODE_DRINK_MENU_ACTIVITY); //會把DrinkMenuActivity呼叫出來，並進行lifecycle，新的頁面會直接疊在舊的頁面上會形成一個stack
        //須給他REQUEST_CODE_DRINK_MENU_ACTIVITY，即可知道攜帶出去與回來是否相同
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) //辨別從哪個activity回來；表示o不ok；攜帶回的資料
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_DRINK_MENU_ACTIVITY)
        {
            if(resultCode == RESULT_OK) //成功的從那個頁面帶回資料
            {
                String result = data.getStringExtra("result"); //所對應到的key值為result
                Toast.makeText(this,result,Toast.LENGTH_LONG).show(); //哪個activity；印出什麼；顯示時間長短
            }
        }
    }

    @Override
   protected void onStart() {
       super.onStart();
       Log.d("REBUG", "MainActivityOnStart");
   }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("REBUG", "MainActivityOnResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("REBUG", "MainActivityOnPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d("REBUG", "MainActivityOnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("REBUG", "MainActivityOnDestroy");
    }


}