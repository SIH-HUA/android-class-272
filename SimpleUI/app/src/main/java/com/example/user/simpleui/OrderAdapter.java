package com.example.user.simpleui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 2016/8/10.
 */
/*希望能把資料轉成itemview*/
public class OrderAdapter extends BaseAdapter //繼承BaseAdapter時，會自動新增一些function，是必須實作的function
{

    List<Order> orders;
    LayoutInflater layoutInflater;

    public OrderAdapter(Context context,List<Order> orderList) //List<Order> orderList 拿資料Context context是一個activity，需要他幫我們拿去layout
    {
        this.orders = orderList;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return orders.size();
    }// 顯示幾個item

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }//給我第幾筆item
    @Override
    public long getItemId(int position) {
        return position;
    }//給我第幾筆的itemID，若對上網路，資料會跟ID相對，較好找資料

    @Override
    public View getView(int position, View convertView, ViewGroup parent) //跟位置拿itemView；若以拿過，只需要重新拿出上次拿過的；幫助把itemView group起來在分類
    {
        Holder holder; //若convertView == null的情況下，須創建一個holder去存，反之，則是去holder的cache拿東西

        if(convertView == null) // 表示之前沒給過itemView
        {
            convertView = layoutInflater.inflate(R.layout.listview_order_item,null); //layout出一個layout的view
            TextView noteTextView = (TextView)convertView.findViewById(R.id.noteTextView);  //拿出UI conponent
            TextView drinkTextView = (TextView)convertView.findViewById(R.id.drinkTextView);
            TextView storeInfoTextView = (TextView)convertView.findViewById(R.id.storeInfoTextView);

            holder = new Holder();

            holder.drinkTextView = drinkTextView; //表示holder有UI元件
            holder.noteTextView = noteTextView;
            holder.storeInfoTextView = storeInfoTextView;

            convertView.setTag(holder); //將holder的東西放在convertView的cache內
        }
        else
        {
            holder = (Holder)convertView.getTag(); //從convertView的cache拿出東西放入holder
        }

        Order order = orders.get(position); //去拿資料
        holder.noteTextView.setText(order.note); //在放到UI conponent內
        holder.drinkTextView.setText(order.drink);
        holder.storeInfoTextView.setText(order.storeInfo);

        return convertView;

    }//將data的東西給view
    class Holder
    {
        TextView noteTextView;
        TextView storeInfoTextView;
        TextView drinkTextView;
    }
}
