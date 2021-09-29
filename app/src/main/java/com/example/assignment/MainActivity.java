package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.database.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edName;
    private EditText edDes;
    private EditText edInfo;
    private EditText edAmount;
    private EditText edDate;
    private EditText edCategory;
    private Button btAdd;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        db = new DBHelper(this);
        db.getReadableDatabase();


    }

    private void initView() {
        edName = (EditText) findViewById(R.id.edName);
        edDes = (EditText) findViewById(R.id.edDes);
        edInfo = (EditText) findViewById(R.id.edInfo);
        edAmount = (EditText) findViewById(R.id.edAmount);
        edDate = (EditText) findViewById(R.id.edDate);
        edCategory = (EditText) findViewById(R.id.edCategory);
        btAdd = (Button) findViewById(R.id.btAdd);
        btAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btAdd){
            onAddExpenses();
        }
    }

    private void onAddExpenses() {
        if (edName.getText().toString().isEmpty()){
            Toast.makeText(this, "Please endter the name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edDes.getText().toString().isEmpty()){
            Toast.makeText(this, "Please endter the description", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edInfo.getText().toString().isEmpty()){
            Toast.makeText(this, "Please endter the infor", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edAmount.getText().toString().isEmpty()){
            Toast.makeText(this, "Please endter the amount", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edDate.getText().toString().isEmpty()){
            Toast.makeText(this, "Please endter the date", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edCategory.getText().toString().isEmpty()){
            Toast.makeText(this, "Please endter the category", Toast.LENGTH_SHORT).show();
            return;
        }
        String isAdd = db.addExpenses(edName.getText().toString(),edInfo.getText().toString(),edDes.getText().toString()
                ,edAmount.getText().toString(),edDate.getText().toString(),edCategory.getText().toString());
        Toast.makeText(this, isAdd, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(MainActivity.this,ListUserActivity.class);
//        startActivity(intent);


    }
}