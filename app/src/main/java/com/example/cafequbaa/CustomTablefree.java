package com.example.cafequbaa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomTablefree extends BaseAdapter {
    Context            context;
    ArrayList<Waiting> waitingArrayList;
    private DatabaseReference mDatabase1;

    public CustomTablefree(Context context, ArrayList<Waiting> waitingArrayList) {
        this.context = context;
        this.waitingArrayList = waitingArrayList;
        mDatabase1 = FirebaseDatabase.getInstance().getReference("waiting");
    }

    @Override
    public int getCount() {
        return waitingArrayList.size();
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
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View           v              = layoutInflater.inflate(R.layout.customtablefree, null);
        TextView       tv             = v.findViewById(R.id.textView14);
        Button         btn            = v.findViewById(R.id.button6);
        final int      pos            = i + 1;
        tv.setText("TBL NO :" + pos + "");
        btn.setText(waitingArrayList.get(i).getType());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase1.child(waitingArrayList.get(i).getPos()).setValue("FREE");
                Toast.makeText(context, "Successfully Changed", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}
