package com.example.cafequbaa;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cafequbaa.Database.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;

public class Viewcustomer extends AppCompatActivity {
    ListView          lv;
    DatabaseReference reference;
    ArrayList<User>   arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcustomer);
        lv = findViewById(R.id.lstview1);
        arrayList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("order");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                Log.e("ERROR", user.getName());
                arrayList.add(user);
                ArrayList<Integer> arrayList1 = new ArrayList<>();
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList1.add(arrayList.get(i).getId());
                }

                HashSet<Integer> integers = new HashSet<>();
                integers.addAll(arrayList1);
                arrayList1.clear();
                arrayList1.addAll(integers);

                Customadaptervieworder customadaptervieworder = new Customadaptervieworder(Viewcustomer.this, arrayList1,arrayList);
                lv.setAdapter(customadaptervieworder);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<User> cities = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    User                user      =new User();
                    Map<String, String> map       = dataSnapshot.getValue(Map.class);
                    user.
                    String               name  = Double.parseDouble(map.get("Lat"));
                    double              longitude = Double.parseDouble(map.get("Lon"));
                    LatLng              location  = new LatLng(latitude, longitude);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });*/
    }
}
