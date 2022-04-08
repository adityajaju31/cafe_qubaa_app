package com.example.cafequbaa.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cafequbaa.R;
import com.example.cafequbaa.Yourorder;
import com.example.cafequbaa.ui.menulist.MenulistFragment;
import com.example.cafequbaa.ui.tablebooking.TablebookingFragment;

public class HomeFragment extends Fragment {
    TextView table, order, menubtn;

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState
    ) {
        View v = inflater.inflate(R.layout.fragment_home, null);
        table = v.findViewById(R.id.table);
        order = v.findViewById(R.id.order);
        menubtn = v.findViewById(R.id.menubtn);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TablebookingFragment tablebookingFragment = new TablebookingFragment();
                FragmentTransaction  ft                   = getFragmentManager().beginTransaction();
                ft.replace(R.id.frmid, tablebookingFragment);
                ft.addToBackStack("hello");
                ft.commit();
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Yourorder           menulistFragment = new Yourorder();
                FragmentTransaction ft               = getFragmentManager().beginTransaction();
                ft.replace(R.id.frmid, menulistFragment);
                ft.addToBackStack("hello");
                ft.commit();
            }
        });
        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenulistFragment    menulistFragment = new MenulistFragment();
                FragmentTransaction ft               = getFragmentManager().beginTransaction();
                ft.replace(R.id.frmid, menulistFragment);
                ft.addToBackStack("hello");
                ft.commit();
            }
        });
        return v;
    }
}