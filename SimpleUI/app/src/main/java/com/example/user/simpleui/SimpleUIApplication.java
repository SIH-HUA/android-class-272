package com.example.user.simpleui;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by user on 2016/8/16.
 */
//若要更改Application的class，需到android manifest做設定
public class SimpleUIApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Drink.class); //沒有事先註冊客製化物件，因此須跟他說
        ParseObject.registerSubclass(DrinkOrder.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Vc3T45tQZj6tU2xHmpvfVom9mNdz9UIZYLtoghbj") //辨識app位置
                .server("https://parseapi.back4app.com/")
                .clientKey("iJrmtc3mr1o2sga0i3hxp75z65QRff67jqxCA7oA")
                .build()
        );
    }
}
