package com.example.assignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "EXPENSES";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "EXPENSES";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String DES = "des";
    public static final String INFORMATION = "infor";
    public static final String AMOUNT = "amount";
    public static final String DATE = "date";
    public static final String CATEGORY = "category";



    public DBHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " CREATE TABLE " + TABLE_NAME +"("+
                ID+" INTEGER PRIMARY KEY, " +
                NAME + "TEXT, "+
                DES + "TEXT, "+
                INFORMATION + "TEXT, "+
                AMOUNT + "TEXT, "+
                DATE + "TEXT, "+
                CATEGORY + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {
        String sql  = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
    public String addExpenses(String expenses , String infor , String des,String amount, String date,String category){
        SQLiteDatabase  db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,expenses);
        contentValues.put(INFORMATION,infor);
        contentValues.put(DES,des);
        contentValues.put(AMOUNT,amount);
        contentValues.put(DATE,date);
        contentValues.put(CATEGORY,category);
        long isAdd = db.insert(TABLE_NAME,null,contentValues);
        if (isAdd == -1){
            return "ADD Fail";
        }
        db.close();
        return "Add Success";
    }
    public Cursor getAllExpenses(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  = "SELECT * FROM " + TABLE_NAME;
        Cursor c = db.rawQuery(sql,null);
        return c;
    }
}
