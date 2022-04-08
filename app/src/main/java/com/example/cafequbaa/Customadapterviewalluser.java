package com.example.cafequbaa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class Customadapterviewalluser extends BaseAdapter {

    Context            context;
    ArrayList<Profile> arrayList;

    Customadapterviewalluser(Context context, ArrayList<Profile> arrayList) {
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
            v = layoutInflater.inflate(R.layout.customuser, null);
            TextView tv1 = v.findViewById(R.id.txt1);
            TextView tv2 = v.findViewById(R.id.txt2);
            TextView tv3 = v.findViewById(R.id.txt3);
            tv1.setText("USER NAME : " + arrayList.get(i).getUsername());
            tv2.setText("EMAIL ID : " + arrayList.get(i).getEmail());
            tv3.setText("MOBILE NUMBER : " + arrayList.get(i).getPhone());
        } catch (Exception e) {

        }
        return v;
    }
}
