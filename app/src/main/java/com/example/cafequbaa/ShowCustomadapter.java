package com.example.cafequbaa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.cafequbaa.Database.DBHelper;
import com.example.cafequbaa.Database.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ShowCustomadapter extends BaseAdapter implements PaymentResultListener {
    Context          context;
    ArrayList<User>  arrayList;
    ArrayList<User>  arrayList1    = new ArrayList<>();
    private DatabaseReference mDatabase;
    DBHelper dbHelper;
    public static        double carttotal;
    private static final String TAG = ShowCustomadapter.class.getSimpleName();

    public ShowCustomadapter(Context context, ArrayList<User> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        dbHelper = new DBHelper(context);
        mDatabase = FirebaseDatabase.getInstance().getReference("order");
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        LayoutInflater          layoutInflater    = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View                    v                 = layoutInflater.inflate(R.layout.customshow, null);
        TextView                textView          = v.findViewById(R.id.textView2);
        TextView                textView2         = v.findViewById(R.id.textView3);
        textView.setText(arrayList.get(i).getName());
        textView2.setText(arrayList.get(i).getPrice() + "");
        final ElegantNumberButton btn = v.findViewById(R.id.button2);
        btn.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                User user = new User();
                user.setId(i);
                user.setTotal(String.valueOf(newValue));
                /*DBHelper dbHelper=new DBHelper(context);
                dbHelper.insertdata(user);*/
                arrayList1.add(user);
                Gson                     gson   = new Gson();
                String                   json   = gson.toJson(arrayList1);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Set", json);
                editor.apply();
               /* String value = String.valueOf(newValue);
                User user=new User();
                user.setId(arrayList.get(i).getId());
                user.setName(arrayList.get(i).getName());
                user.setTotal(value);
                user.setPrice(arrayList.get(i).getPrice());
                dbHelper.update(user);
                Log.e("ID", String.valueOf(arrayList.get(i).getId()));*/
                if (json.isEmpty()) {
                } else {
                    Type type = new TypeToken<List<User>>() {
                    }.getType();
                    ArrayList<User> arrPackageData = gson.fromJson(json, type);
                    ArrayList<User> arrayListnew   = new ArrayList<>();
                    for (User data : arrPackageData) {
                        User user1 = new User();
                        user1.setName(data.getName());
                        user1.setPrice(data.getPrice());
                        user1.setTotal(data.getTotal());
                        arrayListnew.add(user1);
                    }
                    double                   totalCost = 0;
                    SharedPreferences        pref      = context.getSharedPreferences("mytotal", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed        = pref.edit();

                    SharedPreferences        pref1 = context.getSharedPreferences("orderdetail", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed1   = pref1.edit();
                    for (int i1 = 0; i1 < arrayListnew.size(); i1++) {
                        double d = Double.parseDouble(arrayListnew.get(i1).getTotal());
                        totalCost = arrayList.get(i).getPrice();
                        totalCost = totalCost * d;
                        double temp = totalCost;
                        Log.e("total", String.valueOf(totalCost));
                        ed1.putString(String.valueOf(i), String.valueOf(newValue));
                        ed1.apply();
                        ed.putString(String.valueOf(i), String.valueOf(totalCost));
                        ed.apply();
                        Log.e("Shredprefencrece size", String.valueOf(pref.getAll().size()));
                    }
                    double longvalue = 0;
                    for (int i = 0; i < pref.getAll().size(); i++) {
                        double d = Double.parseDouble(pref.getString(String.valueOf(i), null));
                        longvalue = longvalue + d;
                        Log.e("total1", String.valueOf(longvalue));
                        Itemdetail.tv.setText("Total Amount :" + longvalue + "");
                        Itemdetail.btn.setText("Total Amount :" + longvalue);
                        carttotal = longvalue;
                    }
                }
            }
        });
        Itemdetail.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });
        Itemdetail.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Random          random    = new Random();
                    String          id        = String.format("%04d", random.nextInt(10000));
                    DBHelper        dbHelper  = new DBHelper(context);
                    ArrayList<User> arrayList = new ArrayList<>();
                    arrayList = (ArrayList<User>) dbHelper.show();
                    User              user  = null;
                    SharedPreferences pref1 = context.getSharedPreferences("orderdetail", Context.MODE_PRIVATE);
                    for (int i = 0; i < pref1.getAll().size(); i++) {
                        user = new User();
                        user.setId(Integer.parseInt(id));
                        long finalamout = (long) (carttotal);
                        user.setName(arrayList.get(i).getName());
                        user.setTotal(pref1.getString(String.valueOf(i), null));
                        user.setPrice(arrayList.get(i).getPrice());
                        user.setDate(Itemdetail.formattedDate);
                        mDatabase.push().setValue(user);
                    }
                    SharedPreferences        p      = context.getSharedPreferences("id", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = p.edit();
                    editor.putString("id", id);
                    editor.apply();
                    DBHelper dbHelper1 = new DBHelper(context);
                    dbHelper1.clearDatabase();
                    SharedPreferences        p1 = context.getSharedPreferences("mytotal", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed = p1.edit();
                    ed.clear();
                    ed.apply();
                    SharedPreferences        p2  = context.getSharedPreferences("orderdetail", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed1 = p2.edit();
                    ed1.clear();
                    ed1.apply();
                    Intent i = new Intent(context, Checkdata.class);
                    context.startActivity(i);
                    ((Activity) context).finish();
                } catch (Exception e) {

                }
            }
        });
        return v;
    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = (Activity) context;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Cafe Qubaa");
            options.put("description", "Restuarnt Payment");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            double amount = carttotal * 100;
            options.put("amount", amount);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "adityajaju1031@gmail.com");
            preFill.put("contact", "9587101099");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(context, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        try {
            Toast.makeText(context, "Payment Successful: " + s, Toast.LENGTH_SHORT).show();
            SharedPreferences        pref = context.getSharedPreferences("mytotal", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed   = pref.edit();
            ed.clear();
            ed.apply();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(context, "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
}