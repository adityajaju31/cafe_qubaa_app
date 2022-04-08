package com.example.cafequbaa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.cafequbaa.Database.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;

public class Viewalluser extends AppCompatActivity {
    ListView          lv;
    DatabaseReference reference;
    ArrayList<Profile>   arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewalluser);
        lv = findViewById(R.id.lstviewalluser);
        arrayList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Profile profile = dataSnapshot.getValue(Profile.class);
                arrayList.add(profile);
                ArrayList<Integer> arrayList1 = new ArrayList<>();
                Customadapterviewalluser customadaptervieworder = new Customadapterviewalluser(Viewalluser.this,arrayList);
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
    }
}
