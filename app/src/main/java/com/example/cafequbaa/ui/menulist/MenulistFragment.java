package com.example.cafequbaa.ui.menulist;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cafequbaa.Customadapter;
import com.example.cafequbaa.Itemdetail;
import com.example.cafequbaa.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class MenulistFragment extends Fragment {

    private static final int               REQUEST_CODE_SPEECH_INPUT = 4000;
    private              MenulistViewModel mViewModel;
    ListView             listView;
    FirebaseDatabase     database;
    DatabaseReference    reference;
    DatabaseReference    reference1;
    ArrayList<String>    list;
    ArrayList<Long>    list1;
    ArrayAdapter<String> adapter;
    EditText             search;
    ImageButton          mic;
    Customadapter        customadapter;

    public static MenulistFragment newInstance() {
        return new MenulistFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.menulist_fragment, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getView().findViewById(R.id.menuitems);
        database = FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom, R.id.textView, list);
        reference = database.getReference("menu");
        reference1 = database.getReference("price");
        search = getView().findViewById(R.id.search);
        mic = getView().findViewById(R.id.mic);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String string = ds.getValue(String.class);
                    list.add(string.toString());
                }
                reference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            long string = ds.getValue(Long.class);
                            list1.add(string);
                            Log.e("List is", String.valueOf(list1.get(0)));
                        }
                        customadapter = new Customadapter(getActivity(), list,list1);
                        listView.setAdapter(customadapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                //listView.setAdapter(adapter);
                search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        customadapter.getFilter().filter(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                mic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        search();
                    }
                });
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void search() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        );
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(
                RecognizerIntent.EXTRA_PROMPT,
                "speak here"
        );
        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    search.setText(result.get(0));
                }
                break;
            }

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(0, 0, 0, "View Cart").setIcon(R.drawable.cart).setShowAsAction(1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            /*Itemdetail         itemdetail =new Itemdetail();
            FragmentTransaction ft         = getFragmentManager().beginTransaction();
            ft.replace(R.id.frmid,itemdetail);
            ft.commit();*/
            Intent intent=new Intent(getActivity(), Itemdetail.class);
            getActivity().startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}