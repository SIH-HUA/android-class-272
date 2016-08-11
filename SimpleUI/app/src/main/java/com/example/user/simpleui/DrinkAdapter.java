package com.example.user.simpleui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 2016/8/11.
 */
public class DrinkAdapter extends BaseAdapter
{
    List<Drink> drinkList;
    LayoutInflater inflater;

    public DrinkAdapter(Context context,List<Drink> drinks) //可從context拿到LayoutInflater
    {
        this.drinkList = drinks;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return drinkList.size();
    }

    @Override
    public Object getItem(int position) {
        return drinkList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Holder hold;

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_drink_item,null);
            hold = new Holder(); //找UI元件暫存在holder內
            hold.drinkNameTextView = (TextView)convertView.findViewById(R.id.drinkNameTextView); //將原件放入holder
            hold.lPriceTextView = (TextView)convertView.findViewById(R.id.lPriceTextView);
            hold.mPriceTextView = (TextView)convertView.findViewById(R.id.mPriceTextView);
            hold.imageVIew = (ImageView)convertView.findViewById(R.id.imageView);

            convertView.setTag(hold); //將holder放入tag內
        }
        else
        {
            hold = (Holder)convertView.getTag(); //可以從getTag拿到holder
        }

        Drink drink = drinkList.get(position); //從drinkList去拿資料
        hold.drinkNameTextView.setText(drink.name); //在放到UI conponent內
        hold.lPriceTextView.setText(String.valueOf(drink.lPrice));
        hold.mPriceTextView.setText(String.valueOf(drink.mPrice));
        hold.imageVIew.setImageResource(drink.imageId);//是去resource拿id

        return convertView;
    }
    class Holder
    {
        TextView drinkNameTextView;
        TextView lPriceTextView;
        TextView mPriceTextView;
        ImageView imageVIew;
    }
}
