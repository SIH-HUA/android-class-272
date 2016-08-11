package com.example.user.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DrinkMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);
        Log.d("DEBUG", "DrinkMenuActivityOnCreat");

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
