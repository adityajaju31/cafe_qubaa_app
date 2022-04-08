package com.example.cafequbaa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cafequbaa.ui.menulist.MenulistFragment;

public class menulist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menulist_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MenulistFragment.newInstance())
                    .commitNow();
        }
    }
}
