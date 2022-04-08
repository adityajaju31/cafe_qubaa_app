package com.example.cafequbaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cafequbaa.ui.tablebooking.TablebookingFragment;

public class tablebooking extends AppCompatActivity {

    Button t1=(Button)findViewById(R.id.table1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablebooking_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TablebookingFragment.newInstance())
                    .commitNow();
        }
    }

    public void cafeView000(View view) {
        Intent intent=new Intent(this,cafeView.class);
        startActivity(intent);
    }
}
