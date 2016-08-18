package com.example.user.simpleui;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by user on 2016/8/10.
 */
@ParseClassName("Order")
public class Order extends ParseObject {
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
                if(e == null)
                    pinAllInBackground("Order",list );
                callback.done(list,e);
            }
        });
    }

}