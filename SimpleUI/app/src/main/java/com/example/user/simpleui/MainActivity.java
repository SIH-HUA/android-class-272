package com.example.user.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    String drink = "Black Tea"; //初始飲料

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView); //將東西找出來，須強制轉型，因型態不同
        editText = (EditText) findViewById(R.id.editText);
        radioGroup = (RadioGroup) findViewById(R.id.radio123);
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
    }
            public void click(View view) //因為view是所有原件的parent，因此用view
            {
                String text = editText.getText().toString(); //將轉成字串，去除其他屬性
                text = text + " Order: " + drink;
                textView.setText(text);
            }
        }