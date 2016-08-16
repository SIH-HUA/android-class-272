package com.example.user.simpleui;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by user on 2016/8/11.
 */

@ParseClassName("Drink") //表示物件上傳到網路上的名稱

public class Drink extends ParseObject implements Parcelable //使drink可打包
{
//    String name; //不符合ParseObject型態，需刪除
//    int lPrice;
//    int mPrice;
    static  final String NAME_COL = "name"; //標明會用哪些欄位
    static  final String MPRICE_COL = "mPrice";
    static  final String LPRICE_COL = "lPrice";
    int imageId;

    @Override
    public int describeContents() { //打包後的包裹序碼
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) { //物件要如何打包，寫的順序等於拿的順序
        dest.writeString(this.getName());
        dest.writeInt(this.getmPrice());
        dest.writeInt(this.getlPrice()); //dest.writeInt(this.lPrice);拿值
        dest.writeInt(this.imageId);

    }

    public Drink() {
    }

    protected Drink(Parcel in) { //從包果讀出
        this.setName(in.readString());
        this.setmPrice(in.readInt());
        this.setlPrice(in.readInt());
        this.imageId = in.readInt();

    }

    public static final Parcelable.Creator<Drink> CREATOR = new Parcelable.Creator<Drink>() { //幫包裹在復原成資料結構
        @Override
        public Drink createFromParcel(Parcel source) {
            return new Drink(source); //建構子
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };

    public String getName() {
        return getString(NAME_COL); //跟ParseObject內NAME_COL的欄位拿到值
    }

    public void setName(String name) {
        this.put(NAME_COL,name);
    }

    public int getlPrice() {
        return getInt(LPRICE_COL);
    }

    public void setlPrice(int lPrice) {
        this.put(LPRICE_COL,lPrice);
    }

    public int getmPrice() {
        return getInt(MPRICE_COL);
    }

    public void setmPrice(int mPrice) {
        this.put(MPRICE_COL,mPrice);
    }
}
