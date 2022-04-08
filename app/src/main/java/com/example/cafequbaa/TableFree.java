package com.example.cafequbaa;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.github.pierry.progress.Progress;

public class TableFree extends AppCompatActivity {
    public static ListView           lv;
    public static DatabaseReference  reference1;
    public static ArrayList<Waiting> waitingArrayList;
    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_free);
        reference1 = FirebaseDatabase.getInstance().getReference("waiting");
        lv = findViewById(R.id.listcustomfree);
        waitingArrayList = new ArrayList<>();
        context=this;
        gettable();
    }

    public static void gettable() {
        final Progress progress = new Progress(context);
        progress.setBackgroundColor(context.getResources().getColor(R.color.pp_white))
                .setMessage("Please Wait")
                .setMessageColor(context.getColor(R.color.colorPrimary))
                .setProgressColor(context.getColor(R.color.colorAccent))
                .show();
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                waitingArrayList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String  string  = ds.getValue(String.class);
                    String  key     = ds.getKey();
                    Waiting waiting = new Waiting();
                    waiting.setPos(key);
                    waiting.setType(string);
                    waitingArrayList.add(waiting);
                    Log.e("Waiting", string);
                    Log.e("Key", key);
                    CustomTablefree customTablefree = new CustomTablefree(context, waitingArrayList);
                    lv.setAdapter(customTablefree);
                    customTablefree.notifyDataSetChanged();
                    progress.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progress.dismiss();
            }
        });
    }
}
