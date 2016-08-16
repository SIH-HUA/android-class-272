package com.example.user.simpleui;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
}
