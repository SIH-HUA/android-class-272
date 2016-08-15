package com.example.user.simpleui;

import java.util.List;

/**
 * Created by user on 2016/8/10.
 */
public class Order {
    String note;
    String storeInfo;
    List<DrinkOrder> drinkOrderList;

    public int getTotal()
    {
        int total = 0;
        for(DrinkOrder drinkOrder : drinkOrderList)
        {
            total += drinkOrder.lNumber*drinkOrder.drink.lPrice+drinkOrder.mNumber*drinkOrder.drink.mPrice;
        }
        return  total;
    }
}
