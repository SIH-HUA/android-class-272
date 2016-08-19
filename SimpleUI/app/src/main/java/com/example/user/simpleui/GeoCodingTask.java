package com.example.user.simpleui;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

/**
 * Created by user on 2016/8/18.
 */
public class GeoCodingTask extends AsyncTask<String,Void,double[]> //要做的事情，需要的變數是什麼；傳地址；經緯度
{
    WeakReference<GeocodingCallBack>geocodingCallBackWeakReference; //不希望卡住activity的支援，當activity被回收時，他也會正常被回收

    @Override
    protected double[] doInBackground(String... params) //型態變數與上面相同，事background做，不是mainthread做，要等待data回來
    {
        return Utils.getLatlngFromAddress(params[0]);
    }

    @Override
    protected void onPostExecute(double[] doubles) //doInBackground的回傳值會直接傳過來，等資料回來時，就會在mainthread做
    {
        super.onPostExecute(doubles);
        if(geocodingCallBackWeakReference.get()!=null)
        {
            GeocodingCallBack geocodingCallBack = geocodingCallBackWeakReference.get();
            geocodingCallBack.done(doubles);
        }
    }

    public GeoCodingTask(GeocodingCallBack geocodingCallBack)
    {
        geocodingCallBackWeakReference = new WeakReference<GeocodingCallBack>(geocodingCallBack);
    }

    interface GeocodingCallBack //萬一拿到null表示沒拿到東西
    {
        void done(double[] latlng);
    }
}
