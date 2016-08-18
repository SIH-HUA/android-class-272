package com.example.user.simpleui;

import android.os.AsyncTask;

/**
 * Created by user on 2016/8/18.
 */
public class GeoCodingTask extends AsyncTask<String,Void,double[]> //要做的事情，需要的變數是什麼；傳地址；經緯度
{

    @Override
    protected double[] doInBackground(String... params) //型態變數與上面相同，事background做，不是mainthread做，要等待data回來
    {
        return new double[0];
    }

    @Override
    protected void onPostExecute(double[] doubles) //doInBackground的回傳值會直接傳過來，等資料回來時，就會在mainthread做
    {
        super.onPostExecute(doubles);
    }
}
