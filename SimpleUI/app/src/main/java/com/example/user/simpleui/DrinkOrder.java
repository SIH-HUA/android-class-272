package com.example.user.simpleui;

/**
 * Created by user on 2016/8/15.
 */
public class DrinkOrder {
    Drink drink;
    int mNumber = 0;
    int lNumber = 0;
    String ice = "REGULAR";
    String sugar = "REGULAR";
    String note = "";

    public DrinkOrder(Drink drink)
    {
        this.drink = drink; //儲存飲料
    }

}
