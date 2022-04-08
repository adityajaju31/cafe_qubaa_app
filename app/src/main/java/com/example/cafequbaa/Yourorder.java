package com.example.cafequbaa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Yourorder extends Fragment {
    String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View              v           = inflater.inflate(R.layout.fragment_yourorder, null);
        try {
            TextView          tv          = v.findViewById(R.id.text);
            SharedPreferences preferences = getActivity().getSharedPreferences("odi", Context.MODE_PRIVATE);
            String            id          = preferences.getString("order", null);
            tv.setText(id);
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), "Order not avaialbale", Toast.LENGTH_SHORT).show();
        }
        return v;
    }
}
