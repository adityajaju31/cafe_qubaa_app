package com.example.cafequbaa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class Customadapterviewalltablebook  extends BaseAdapter {

    Context            context;
    ArrayList<Tablebookingdetail> arrayList;

    Customadapterviewalltablebook(Context context, ArrayList<Tablebookingdetail> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
            v = layoutInflater.inflate(R.layout.customtable, null);
            TextView tv1 = v.findViewById(R.id.txt4);
            TextView tv2 = v.findViewById(R.id.txt5);
            TextView tv4 = v.findViewById(R.id.txt6);
            TextView tv3 = v.findViewById(R.id.txt7);
            tv1.setText("USER NAME : " + arrayList.get(i).getUsername());
            tv2.setText("TOTAL TABLE : " + arrayList.get(i).getTotaltable());
            tv3.setText("TOTAL PAYMENT : " + arrayList.get(i).getTotalpayment());
            tv4.setText("MOBILE NUMBER : " + arrayList.get(i).getMobile());
        } catch (Exception e) {

        }
        return v;
    }
}