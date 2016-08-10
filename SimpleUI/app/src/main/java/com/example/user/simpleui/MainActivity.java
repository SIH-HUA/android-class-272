package com.example.user.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    ListView listView;
    Spinner spinner;

    String drink = "Black Tea"; //初始飲料

    List<Order> data = new ArrayList<>();

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
                Order order = (Order)parent.getAdapter().getItem(position);
                Toast.makeText(MainActivity.this,order.note,Toast.LENGTH_LONG).show();//若直接打this會直接指向包住的listener，不會指向Main activity //出現顯示框
            }
        }); //當item被點選時會觸發的事件

        setupListView();
        setupSpinner();
    }

    private void setupListView() {
        //String[] data = new String[]{"1","2","3","4","5","6","7","8"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data); //轉換成每筆有用的ITEM
        /*List<Map<String,String>> mapList = new ArrayList<>();
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
        OrderAdapter adapter = new OrderAdapter(this,data); //是一個activity；資料
        listView.setAdapter(adapter);
    }

    private void setupSpinner()
    {
        String[] storeInfo = getResources().getStringArray(R.array.storeInfo); //重resource裡拿出定義檔
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,storeInfo);
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
        order.storeInfo = (String)spinner.getSelectedItem(); //物件型態轉String，給被選取的item資料
        data.add(order); //將東西丟入list內
        setupListView(); ///重整listview
    }


}