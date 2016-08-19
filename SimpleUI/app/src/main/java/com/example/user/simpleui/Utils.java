package com.example.user.simpleui;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by user on 2016/8/16.
 */
public class Utils {
    public static void writeFile(Context context,String fileName,String content) //activity，檔案名稱，寫入的內容
    {
        try { //用try-catch包住
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_APPEND); //把在content的東西貫道FileOutputStream，就會由FileOutputStream放入資料夾
            fos.write(content.getBytes()); //去抓IOException，須轉成bytes
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(Context context,String fileName) //input String，檔案名稱
    {
        try {
            FileInputStream fis = context.openFileInput(fileName); //檔案
            byte[] bytes = new byte[2048]; //創造一個byte-array
            fis.read(bytes, 0, bytes.length); //從一開始開始讀，讀到byte寫完
            fis.close();
            String content = new String(bytes);
            return content;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";//若沒讀到東西，救回傳空字串
    }

    public static double[] getLatlngFromAddress(String address)
    {
        try{
            address = URLEncoder.encode(address,"UTF-8"); //避免中文出現亂碼
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //geocode經緯度json格式?address後面為給的變數
        String apiURL = "http://maps.google.com/maps/api/geocode/json?address="+address; //送一個網址給他，他會回傳東西給你

        byte[] data = Utils.urlToBytes(apiURL); //將收到的資料放入byte-array
        if(data == null)
            return null;

        String result = new String(data);
        try {
            JSONObject jsonObject = new JSONObject(result);
            if(jsonObject.getString("status").equals("OK"))
            {
                JSONObject location = jsonObject.getJSONArray("results") //一層一層往下拿
                                                .getJSONObject(0)
                                                .getJSONObject("geometry")
                                                .getJSONObject("location");
                double lat = location.getDouble("lat");
                double lng = location.getDouble("lng");
                return new double[]{lat,lng}; //回傳經緯度
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null; //什麼都沒做的話就回傳null


    }

    public static byte[] urlToBytes(String urlString) //連接到server把資料載回來
    {
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); //去接收傳過來的東西
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buffer))!=-1) //把東西讀到buffer裡面
            {
                byteArrayOutputStream.write(buffer,0,len);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
