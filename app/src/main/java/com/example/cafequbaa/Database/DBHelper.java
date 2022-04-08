package com.example.cafequbaa.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static       String DBNAME  = "mydb";
    public static       String TBLNAME = "tblname";
    public static final String NAME    = "uname";
    public static final String ID      = "id";
    public static final String TOTAL   = "total";
    public static final int    VERSION = 1;
    public static final String PRICE   = "price";

    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TBLNAME + "(" + ID + " integer primary key autoincrement,"
                + NAME + " text," + TOTAL + " text," + PRICE + " text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertdata(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues  cv = new ContentValues();
        cv.put(NAME, user.getName());
        cv.put(TOTAL, user.getTotal());
        cv.put(PRICE, user.getPrice());
        db.insert(TBLNAME, ID, cv);
        db.close();
    }

    public List<User> show() {
        ArrayList<User> arrayList = new ArrayList<>();
        SQLiteDatabase  db        = getReadableDatabase();
        String          column[]  = {ID, NAME, TOTAL, PRICE};
        Cursor          c         = db.query(TBLNAME, column, null, null, null, null, null);
        while (c.moveToNext()) {
            int    id    = c.getInt(0);
            String name  = c.getString(1);
            String totla = c.getString(2);
            long   price = c.getLong(3);
            User   user  = new User();
            user.setId(id);
            user.setName(name);
            user.setTotal(totla);
            user.setPrice(price);
            arrayList.add(user);
        }
        return arrayList;
    }

    public void update(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues  cv = new ContentValues();
        cv.put(NAME, user.getName());
        cv.put(PRICE, user.getPrice());
        cv.put(TOTAL, user.getTotal());
        String where = ID + "=" + user.getId();
        db.update(TBLNAME, cv, ID + " = ?",
                  new String[]{String.valueOf(user.getId())}
        );
        //db.execSQL("UPDATE "+TBLNAME+" SET "+TOTAL+"="+user.getTotal()+" WHERE "+ID +"="+user.getId());
        db.close();
    }

    public final void clearDatabase() {
        SQLiteDatabase db           = getWritableDatabase();
        String         clearDBQuery = "DELETE FROM " + TBLNAME;
        db.execSQL(clearDBQuery);
    }
}
