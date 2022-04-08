package com.example.cafequbaa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafequbaa.Database.DBHelper;
import com.example.cafequbaa.Database.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Customadapter extends BaseAdapter implements Filterable {
    Context           context;
    ArrayList<String> list;
    homepage          homepage;
    private List<String> originalData = null;
    private ItemFilter   mFilter      = new ItemFilter();
    ArrayList<Long> list1;
    ArrayList<User> arrayList = new ArrayList<>();

    public Customadapter(Context context, ArrayList<String> list, ArrayList<Long> list1) {
        this.context = context;
        homepage = (com.example.cafequbaa.homepage) context;
        this.list = list;
        this.list1 = list1;
        this.originalData = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final SharedPreferences          sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        LayoutInflater                   layoutInflater    = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View v                 = layoutInflater.inflate(R.layout.custom, null);
        TextView                         textView          = v.findViewById(R.id.textView);
        textView.setText(list.get(i));
        TextView textView2 = v.findViewById(R.id.textView1);
        textView2.setText("Rs." + list1.get(i) + "");
        Button btn = v.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setName(String.valueOf(list.get(i)));
                user.setPrice(list1.get(i));
                DBHelper dbHelper = new DBHelper(context);
                dbHelper.insertdata(user);
                arrayList.add(user);
                Gson                     gson   = new Gson();
                String                   json   = gson.toJson(arrayList);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Set", json);
                editor.apply();
                Toast.makeText(context, "Item Added Successfully into Cart", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String                  filterString = constraint.toString().toLowerCase();
            FilterResults           results      = new FilterResults();
            final List<String>      list         = originalData;
            int                     count        = list.size();
            final ArrayList<String> nlist        = new ArrayList<String>(count);
            String                  filterableString;
            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }
            results.values = nlist;
            results.count = nlist.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }
    }
}
