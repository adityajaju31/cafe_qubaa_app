package com.example.cafequbaa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cafequbaa.Database.User;

import java.util.ArrayList;

public class Customadaptervieworder extends BaseAdapter {
    Context            context;
    ArrayList<Integer> arrayList;
    ArrayList<User>    arrayList1;

    Customadaptervieworder(Context context, ArrayList<Integer> arrayList, ArrayList<User> arrayList1) {
        this.context = context;
        this.arrayList = arrayList;
        this.arrayList1 = arrayList1;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = null;
        try {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.customid, null);
            TextView tv = v.findViewById(R.id.textView10);
            tv.setText("ORDER ID : " + arrayList.get(i) + "" + "\n\n" + arrayList1.get(i).getDate());
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int           id = arrayList.get(i);
                    String        totalitem;
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < arrayList1.size(); i++) {
                        if (id == arrayList1.get(i).getId()) {
                            try {
                                totalitem = arrayList1.get(i).getName() + " : " + arrayList1.get(i).getTotal() + "\n" + arrayList1.get(i).getDate();
                                sb.append(totalitem);
                            } catch (Exception e) {

                            }
                        }
                    }
                    Log.e("TOTAL", sb.toString());
                    String                   finalorder  = sb.toString();
                    SharedPreferences        preferences = context.getSharedPreferences("odi", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed          = preferences.edit();
                    ed.putString("order", finalorder);
                    ed.apply();
                    Intent intent = new Intent(context, Orderdetail.class);
                    context.startActivity(intent);
                }
            });
        } catch (Exception e) {

        }
        return v;
    }
}
