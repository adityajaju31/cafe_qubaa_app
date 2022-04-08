package com.example.cafequbaa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class Youraccount extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View                     v    = inflater.inflate(R.layout.fragment_youraccount, null);
        try {

            SharedPreferences        pref = getActivity().getSharedPreferences("profile", Context.MODE_PRIVATE);
            String name=pref.getString("name",null);
            String email=pref.getString("email",null);
            String password=pref.getString("password",null);
            String phone=pref.getString("phone",null);
            TextView tv1=v.findViewById(R.id.textView5);
            TextView tv2=v.findViewById(R.id.textView6);
            TextView tv3=v.findViewById(R.id.textView8);
            TextView tv4=v.findViewById(R.id.textView9);
            tv1.setText(name);
            tv2.setText(email);
            tv3.setText(password);
            tv4.setText(phone);
        }
        catch (Exception e)
        {

        }
        return v;
    }
}
