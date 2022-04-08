package com.example.cafequbaa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.github.pierry.progress.Progress;

public class ViewTablebooking extends AppCompatActivity {
    ListView          lv;
    DatabaseReference reference, reference1;
    ArrayList<Tablebookingdetail> arrayList;
    ArrayList<Waiting>            waitingArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tablebooking);
        lv = findViewById(R.id.lstviewalltable);
        arrayList = new ArrayList<>();
        waitingArrayList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("tablebooking");
        reference1 = FirebaseDatabase.getInstance().getReference("waiting");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Tablebookingdetail tablebookingdetail = dataSnapshot.getValue(Tablebookingdetail.class);
                arrayList.add(tablebookingdetail);
                Customadapterviewalltablebook customadaptervieworder = new Customadapterviewalltablebook(ViewTablebooking.this, arrayList);
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "View Table Status").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            Intent intent=new Intent(ViewTablebooking.this,TableFree.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
