package com.example.user.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    ListView listView;
    Spinner spinner;

    String drink = "Black Tea"; //初始飲料

    List<String> data = new ArrayList<>();

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

        setupListView();
        setupSpinner();
    }

    private void setupListView() {
        //String[] data = new String[]{"1","2","3","4","5","6","7","8"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data); //轉換成每筆有用的ITEM
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
        text = text + " Order: " + drink;
        textView.setText(text);
        //editText.setText(" ");
        data.add(text); //將東西丟入list內
        setupListView(); ///重整listview
    }


}