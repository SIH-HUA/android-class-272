package com.example.user.simpleui;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2016/8/15.
 */
public class DrinkOrder implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.drink, flags);
        dest.writeInt(this.mNumber);
        dest.writeInt(this.lNumber);
        dest.writeString(this.ice);
        dest.writeString(this.sugar);
        dest.writeString(this.note);
    }

    protected DrinkOrder(Parcel in) {
        this.drink = in.readParcelable(Drink.class.getClassLoader());
        this.mNumber = in.readInt();
        this.lNumber = in.readInt();
        this.ice = in.readString();
        this.sugar = in.readString();
        this.note = in.readString();
    }

    public static final Parcelable.Creator<DrinkOrder> CREATOR = new Parcelable.Creator<DrinkOrder>() {
        @Override
        public DrinkOrder createFromParcel(Parcel source) {
            return new DrinkOrder(source);
        }

        @Override
        public DrinkOrder[] newArray(int size) {
            return new DrinkOrder[size];
        }
    };
}
