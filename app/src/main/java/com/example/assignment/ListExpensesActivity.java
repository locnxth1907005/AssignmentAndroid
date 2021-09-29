package com.example.assignment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.database.DBHelper;

public class ListExpensesActivity extends AppCompatActivity {
    private DBHelper db;
    private Cursor c;
    private SimpleCursorAdapter adapter;
    private String name;
    private String information;
    private String des;
    private String amount;
    private String category;
    private String date;
    private int _id;

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_list_expenses);
        db = new DBHelper(this);
        ListView lvUser = (ListView) findViewById(R.id.lvExpenses);
        c = db.getAllExpenses();
        adapter = new SimpleCursorAdapter(this,R.layout.item_expenses,c,new String[]{
                DBHelper.ID,DBHelper.NAME,DBHelper.DES}, new int[]{R.id.tvId,R.id.tvName,
                R.id.tvDes}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lvUser.setAdapter(adapter);
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);
                _id = cursor.getInt(cursor.getColumnIndex(DBHelper.ID));
                name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
                information = cursor.getString(cursor.getColumnIndex(DBHelper.INFORMATION));
                des = cursor.getString(cursor.getColumnIndex(DBHelper.DES));
                amount = cursor.getString(cursor.getColumnIndex(DBHelper.AMOUNT));
                category = cursor.getString(cursor.getColumnIndex(DBHelper.CATEGORY));
                date = cursor.getString(cursor.getColumnIndex(DBHelper.DATE));
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        c = db.getAllExpenses();
        adapter.changeCursor(c);
        adapter.notifyDataSetChanged();
        db.close();
    }
}
