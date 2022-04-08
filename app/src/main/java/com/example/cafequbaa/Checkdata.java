package com.example.cafequbaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Checkdata extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkdata);
        tv=findViewById(R.id.textView7);
        SharedPreferences        p      = getSharedPreferences("id", Context.MODE_PRIVATE);
        String confirmid=p.getString("id",null);
        tv.setText("Your Order Confirm ID is :"+p.getString("id",confirmid));
    }
}
