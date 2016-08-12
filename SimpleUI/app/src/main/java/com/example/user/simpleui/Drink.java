package com.example.user.simpleui;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2016/8/11.
 */
public class Drink implements Parcelable //使drink可打包
{
    String name;
    int lPrice;
    int mPrice;
    int imageId;

    @Override
    public int describeContents() { //打包後的包裹序碼
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) { //物件要如何打包，寫的順序等於拿的順序
        dest.writeString(this.name);
        dest.writeInt(this.mPrice);
        dest.writeInt(this.lPrice);
        dest.writeInt(this.imageId);

    }

    public Drink() {
    }

    protected Drink(Parcel in) { //從包果讀出
        this.name = in.readString();
        this.mPrice = in.readInt();
        this.lPrice = in.readInt();
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
}
