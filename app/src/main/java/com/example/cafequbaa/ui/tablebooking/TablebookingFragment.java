package com.example.cafequbaa.ui.tablebooking;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cafequbaa.PanoramicViewActivity;
import com.example.cafequbaa.R;
import com.example.cafequbaa.Tablebookingdetail;
import com.example.cafequbaa.Waiting;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import io.github.pierry.progress.Progress;

public class TablebookingFragment extends Fragment implements View.OnClickListener, PaymentResultListener {

    public static TablebookingFragment newInstance() {
        return new TablebookingFragment();
    }

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btncafeview, btnpayment;
    int payment[] = {400};
    int price     = 400;
    private static final String TAG = TablebookingFragment.class.getSimpleName();
    ArrayList<Integer> arrayList;
    private DatabaseReference mDatabase, mDatabase1;
    String name, phone;
    String             TBLBOOK = "bookd";
    ArrayList<Waiting> waitingArrayList;
    ArrayList<Waiting> pos;
    TextView t1, t2, t3, t4, t5, t6, t7, t8;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View              v    = inflater.inflate(R.layout.tablebooking_fragment, null);
        SharedPreferences pref = getActivity().getSharedPreferences("profile", Context.MODE_PRIVATE);
        name = pref.getString("name", null);
        phone = pref.getString("phone", null);
        mDatabase = FirebaseDatabase.getInstance().getReference("tablebooking");
        mDatabase1 = FirebaseDatabase.getInstance().getReference("waiting");
        setHasOptionsMenu(true);
        btn1 = v.findViewById(R.id.table1);
        btn2 = v.findViewById(R.id.table2);
        btn3 = v.findViewById(R.id.table3);
        btn4 = v.findViewById(R.id.table4);
        btn5 = v.findViewById(R.id.table5);
        btn6 = v.findViewById(R.id.table6);
        btn7 = v.findViewById(R.id.table7);
        btn8 = v.findViewById(R.id.table8);
        t1 = v.findViewById(R.id.t1);
        t2 = v.findViewById(R.id.t2);
        t3 = v.findViewById(R.id.t3);
        t4 = v.findViewById(R.id.t4);
        t5 = v.findViewById(R.id.t5);
        t6 = v.findViewById(R.id.t6);
        t7 = v.findViewById(R.id.t7);
        t8 = v.findViewById(R.id.t8);
        btncafeview = v.findViewById(R.id.CafeView000);
        btnpayment = v.findViewById(R.id.checkout);
        arrayList = new ArrayList<>();
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btncafeview.setOnClickListener(this);
        btnpayment.setOnClickListener(this);
        waitingArrayList = new ArrayList<>();
        pos=new ArrayList<>();
        try {
            gettable();
        } catch (Exception e) {

        }
        return v;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        Waiting waiting=new Waiting();
        int counter = 0;
        if (view == btn1) {
            if (btn1.getText().toString().equalsIgnoreCase("Table 1")) {
                if (waitingArrayList.get(0).getType().equals("FREE")) {
                    btn1.setText(TBLBOOK);
                    btn1.setBackgroundColor(R.color.btnselect);
                    counter++;
                    arrayList.add(counter);
                    waiting.setPos(String.valueOf(0));
                    waiting.setType("OCCUPIED");
                    pos.add(waiting);
                } else {
                    Toast.makeText(getActivity(), "Table already Booked", Toast.LENGTH_SHORT).show();
                }
            } else {
                btn1.setText("Table 1");
                btn1.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                t1.setText(waitingArrayList.get(0).getType());
                arrayList.remove(0);
                pos.remove(0);
                counter--;
            }

        } else if (view == btn2) {
            if (btn2.getText().toString().equalsIgnoreCase("Table 2")) {
                if (waitingArrayList.get(1).getType().equals("FREE")) {
                    btn2.setText(TBLBOOK);
                    btn2.setBackgroundColor(R.color.btnselect);
                    counter++;
                    arrayList.add(counter);
                    waiting.setPos(String.valueOf(1));
                    waiting.setType("OCCUPIED");
                    pos.add(waiting);
                } else {
                    Toast.makeText(getActivity(), "Table already Booked", Toast.LENGTH_SHORT).show();
                }
            } else {
                btn2.setText("Table 2");
                btn2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                arrayList.remove(counter);
                pos.remove(counter);
                counter--;
            }
        } else if (view == btn3) {
            if (btn3.getText().toString().equalsIgnoreCase("Table 3")) {
                if (waitingArrayList.get(2).getType().equals("FREE")) {
                    btn3.setText(TBLBOOK);
                    btn3.setBackgroundColor(R.color.btnselect);
                    counter++;
                    arrayList.add(counter);
                    waiting.setPos(String.valueOf(2));
                    waiting.setType("OCCUPIED");
                    pos.add(waiting);
                } else {
                    Toast.makeText(getActivity(), "Table already Booked", Toast.LENGTH_SHORT).show();
                }
            } else {
                btn3.setText("Table 3");
                btn3.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                arrayList.remove(counter);
                pos.remove(counter);
                counter--;
            }
        } else if (view == btn4) {
            if (btn4.getText().toString().equalsIgnoreCase("Table 4")) {
                if (waitingArrayList.get(3).getType().equals("FREE")) {
                    btn4.setText(TBLBOOK);
                    btn4.setBackgroundColor(R.color.btnselect);
                    counter++;
                    arrayList.add(counter);
                    waiting.setPos(String.valueOf(3));
                    waiting.setType("OCCUPIED");
                    pos.add(waiting);
                } else {
                    Toast.makeText(getActivity(), "Table already Booked", Toast.LENGTH_SHORT).show();
                }
            } else {
                btn4.setText("Table 4");
                btn4.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                arrayList.remove(counter);
                pos.remove(counter);
                counter--;
            }
        } else if (view == btn5) {
            if (btn5.getText().toString().equalsIgnoreCase("Table 5")) {
                if (waitingArrayList.get(4).getType().equals("FREE")) {
                    btn5.setText(TBLBOOK);
                    btn5.setBackgroundColor(R.color.btnselect);
                    counter++;
                    arrayList.add(counter);
                    waiting.setPos(String.valueOf(4));
                    waiting.setType("OCCUPIED");
                    pos.add(waiting);
                } else {
                    Toast.makeText(getActivity(), "Table already Booked", Toast.LENGTH_SHORT).show();
                }
            } else {
                btn5.setText("Table 5");
                btn5.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                arrayList.remove(counter);
                pos.remove(counter);
                counter--;
            }
        } else if (view == btn6) {
            if (btn6.getText().toString().equalsIgnoreCase("Table 6")) {
                if (waitingArrayList.get(5).getType().equals("FREE")) {
                    btn6.setText(TBLBOOK);
                    btn6.setBackgroundColor(R.color.btnselect);
                    counter++;
                    arrayList.add(counter);
                    waiting.setPos(String.valueOf(5));
                    waiting.setType("OCCUPIED");
                    pos.add(waiting);
                } else {
                    Toast.makeText(getActivity(), "Table already Booked", Toast.LENGTH_SHORT).show();
                }
            } else {
                btn6.setText("Table 6");
                btn6.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                arrayList.remove(counter);
                pos.remove(counter);
                counter--;
            }
        } else if (view == btn7) {
            if (btn7.getText().toString().equalsIgnoreCase("Table 7")) {
                if (waitingArrayList.get(6).getType().equals("FREE")) {
                    btn7.setText(TBLBOOK);
                    btn7.setBackgroundColor(R.color.btnselect);
                    counter++;
                    arrayList.add(counter);
                    waiting.setPos(String.valueOf(6));
                    waiting.setType("OCCUPIED");
                    pos.add(waiting);
                } else {
                    Toast.makeText(getActivity(), "Table already Booked", Toast.LENGTH_SHORT).show();
                }
            } else {
                btn7.setText("Table 7");
                btn7.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                arrayList.remove(counter);
                counter--;
                pos.remove(counter);
            }
        } else if (view == btn8) {
            if (btn8.getText().toString().equalsIgnoreCase("Table 8")) {
                if (waitingArrayList.get(7).getType().equals("FREE")) {
                    btn8.setText(TBLBOOK);
                    btn8.setBackgroundColor(R.color.btnselect);
                    counter++;
                    arrayList.add(counter);
                    waiting.setPos(String.valueOf(7));
                    waiting.setType("OCCUPIED");
                    pos.add(waiting);
                } else {
                    Toast.makeText(getActivity(), "Table already Booked", Toast.LENGTH_SHORT).show();
                }
            } else {
                btn8.setText("Table 8");
                btn8.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                arrayList.remove(counter);
                counter--;
                pos.remove(counter);
            }
        } else if (view == btncafeview) {
            Intent intent = new Intent(getActivity(), PanoramicViewActivity.class);
            startActivity(intent);
        } else if (view == btnpayment) {
            Toast.makeText(getActivity(), "Toatal Selected Table :" + arrayList.size(), Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(getActivity())
                    .setTitle("Proceed Payment")
                    .setMessage("Total Table is :" + arrayList.size())
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            double             amount             = payment[0] * arrayList.size();
                            Tablebookingdetail tablebookingdetail = new Tablebookingdetail();
                            tablebookingdetail.setUsername(name);
                            tablebookingdetail.setMobile(phone);
                            tablebookingdetail.setTotaltable(String.valueOf(arrayList.size()));
                            tablebookingdetail.setTotalpayment(String.valueOf(amount));
                            mDatabase.push().setValue(tablebookingdetail);
                            Log.e("POSSIZE", String.valueOf(pos.size()));
                            for(int i1=0;i1<pos.size();i1++) {
                                //mDatabase1.child(String.valueOf(pos.get(i1).getPos())).updateChildren(result);
                                mDatabase1.child(pos.get(i1).getPos()).setValue("OCCUPIED");
                            }
                            startPayment();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
        Log.e("Counter Value is", String.valueOf(arrayList.size()));
    }

    public void startPayment() {
        final Activity activity = getActivity();

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Cafe Qubaa");
            options.put("description", "Restuarnt Payment");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            double amount = payment[0] * arrayList.size() * 100;
            options.put("amount", amount);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "adityajaju1031@gmail.com");
            preFill.put("contact", "9587101099");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        try {
            Toast.makeText(getActivity(), "Payment Successful: " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(getActivity(), "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }

    public void gettable() {
        final Progress progress = new Progress(getActivity());
        progress.setBackgroundColor(getResources().getColor(R.color.pp_white))
                .setMessage("Please Wait")
                .setMessageColor(getResources().getColor(R.color.colorPrimary))
                .setProgressColor(getResources().getColor(R.color.colorAccent))
                .show();
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String  string  = ds.getValue(String.class);
                    String  key     = ds.getKey();
                    Waiting waiting = new Waiting();
                    waiting.setPos(key);
                    waiting.setType(string);
                    waitingArrayList.add(waiting);
                    Log.e("Waiting", string);
                    Log.e("Key", key);
                    progress.dismiss();
                }
                t1.setText(waitingArrayList.get(0).getType());
                t2.setText(waitingArrayList.get(1).getType());
                t3.setText(waitingArrayList.get(2).getType());
                t4.setText(waitingArrayList.get(3).getType());
                t5.setText(waitingArrayList.get(4).getType());
                t6.setText(waitingArrayList.get(5).getType());
                t7.setText(waitingArrayList.get(6).getType());
                t8.setText(waitingArrayList.get(7).getType());
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progress.dismiss();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(0, 0, 0, "Refresh").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==0)
        {
            gettable();
        }
        return super.onOptionsItemSelected(item);
    }
}
