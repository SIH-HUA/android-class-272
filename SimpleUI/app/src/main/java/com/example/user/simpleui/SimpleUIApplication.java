package com.example.user.simpleui;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by user on 2016/8/16.
 */
//若要更改Application的class，需到android manifest做設定
public class SimpleUIApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("76ee57f8e5f8bd628cc9586e93d428d5") //辨識app位置
                .server("http://parseserver-ps662-env.us-east-1.elasticbeanstalk.com/parse/")
                .clientKey("iJrmtc3mr1o2sga0i3hxp75z65QRff67jqxCA7oA")
                .build()
        );
    }
}
