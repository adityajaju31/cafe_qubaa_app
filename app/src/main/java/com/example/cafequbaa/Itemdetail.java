package com.example.cafequbaa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cafequbaa.Database.DBHelper;
import com.example.cafequbaa.Database.User;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Itemdetail extends AppCompatActivity {
    ListView lv;
    public static TextView tv;
    public static Button   btn, btn2;
    public static String formattedDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_itemdetail);
        lv = findViewById(R.id.lstview);
        tv = findViewById(R.id.totalprice);
        btn = findViewById(R.id.button3);
        btn2 = findViewById(R.id.button4);
        Toast.makeText(Itemdetail.this, "There is something error", Toast.LENGTH_LONG).show();
        final SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        Gson                    gson              = new Gson();
        String                  json              = sharedPreferences.getString("Set", "");
        /*if (json.isEmpty()) {
            Toast.makeText(Itemdetail.this, "There is something error", Toast.LENGTH_LONG).show();
        } else {
            Type type = new TypeToken<List<User >>() {
            }.getType();
            ArrayList<User > arrPackageData = gson.fromJson(json, type);
            ArrayList<User>arrayList=new ArrayList<>();
            for(User data:arrPackageData) {
                Log.e("DATA is",data.getName());
                User user=new User();
                user.setName(data.getName());
                user.setPrice(data.getPrice());
                user.setTotal(data.getTotal());
                arrayList.add(user);
                Toast.makeText(this, "Name is"+data.getName(), Toast.LENGTH_SHORT).show();
            }*/
        Date             c  = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c);
        ArrayList<User> arrayList = new ArrayList<>();
        DBHelper        dbHelper  = new DBHelper(Itemdetail.this);
        arrayList = (ArrayList<User>) dbHelper.show();
        ShowCustomadapter showCustomadapter = new ShowCustomadapter(Itemdetail.this, arrayList);
        lv.setAdapter(showCustomadapter);
    }
}