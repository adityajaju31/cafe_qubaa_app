package com.example.cafequbaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminhomepage extends AppCompatActivity {

    Button btnvieworder,btnuserdetail,btntablebooked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhomepage);
        btnvieworder=findViewById(R.id.vieworders);
        btnuserdetail=findViewById(R.id.userdeatils);
        btntablebooked=findViewById(R.id.viewtablebookings);
        btnvieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(adminhomepage.this,Viewcustomer.class);
                startActivity(intent);
            }
        });
        btnuserdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(adminhomepage.this,Viewalluser.class);
                startActivity(intent);
            }
        });
        btntablebooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(adminhomepage.this,ViewTablebooking.class);
                startActivity(intent);
            }
        });
    }
}
