package com.example.user.simpleui;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by user on 2016/8/10.
 */
@ParseClassName("Order")
public class Order extends ParseObject implements Parcelable{
    static final  String NOTE_COL = "note"; //不管什麼型態都要用String
    static final String STOREINFO_COL = "storeInfo";
    static final String DRINKORDERLIST_COL = "drinkOrderList";

    //把變數ParseObject化
//    String note;
//    String storeInfo;
//    List<DrinkOrder> drinkOrderList;

    public int getTotal()
    {
        int total = 0;
        for(DrinkOrder drinkOrder : getDrinkOrderList())
        {
            total += drinkOrder.getlNumber()*drinkOrder.getDrink().getlPrice()+drinkOrder.getmNumber()*drinkOrder.getDrink().getmPrice();
        }
        return  total;
    }

    public String getNote() {
        return getString(NOTE_COL);
    }

    public void setNote(String note) {
        this.put(NOTE_COL,note);
    }

    public String getStoreInfo() {
        return getString(STOREINFO_COL);
    }

    public void setStoreInfo(String storeInfo) {
        this.put(STOREINFO_COL,storeInfo);
    }

    public List<DrinkOrder> getDrinkOrderList() {
        return getList(DRINKORDERLIST_COL);
    }

    public void setDrinkOrderList(List<DrinkOrder> drinkOrderList) {
        this.put(DRINKORDERLIST_COL,drinkOrderList);
    }
    public static ParseQuery<Order>getQuery()
    {
        return ParseQuery.getQuery(Order.class)
                .include(DRINKORDERLIST_COL) //表示包含什麼欄位
                .include(DRINKORDERLIST_COL + "." + DrinkOrder.DRINK_COL); //表示DRINKORDERLIST_COL下面的DRINK_COL
    }

    public static void getOrderFromLocalThenRemote(final FindCallback<Order> callback)
    {
        getQuery().fromLocalDatastore().findInBackground(callback);
        getQuery().findInBackground(new FindCallback<Order>() {
            @Override
            public void done(List<Order> list, ParseException e) {
                if (e == null)
                    pinAllInBackground("Order", list);
                callback.done(list, e);
            }
        });
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(getObjectId() == null) //先判斷有無getObjectId
        {
            dest.writeInt(0);
            dest.writeString(getNote());
            dest.writeString(getStoreInfo());
            dest.writeParcelableArray((Parcelable[]) getDrinkOrderList().toArray(),flags); //需用array，需轉型
        }
        else
        {
            dest.writeInt(1);
            dest.writeString(getObjectId());
        }
    }

    protected Order(Parcel in) {
        super();
        this.setNote(in.readString());
        this.setStoreInfo(in.readString());
        this.setDrinkOrderList(Arrays.asList((DrinkOrder[]) in.readArray(DrinkOrder.class.getClassLoader()))); //將array讀出，再轉成DrinkOrder的array，再用readArray讀成list
    }

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            int isFromRemote = source.readInt();
            if(isFromRemote == 0)
            {
                return  new Order(source);
            }
            else
            {
                String objectId = source.readString(); //從server端拿到資料
                return getOrderFromCache(objectId);
            }
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public static Order getOrderFromCache(String objectId) //從cache拿到drinkOrder資料
    {
        //希望可以把objectId換成drinkOrder
        try { //會先從cache找，若找不到在去網路上找
            return getQuery().fromLocalDatastore().get(objectId);//會給我一個objectId
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Order.createWithoutData(Order.class,objectId);

    }
}