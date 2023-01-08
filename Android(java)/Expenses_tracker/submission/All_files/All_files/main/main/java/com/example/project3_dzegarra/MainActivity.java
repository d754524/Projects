package com.example.project3_dzegarra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button openMap;

    MyDbHelper mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    EditText restaurant, money, meal, review, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new MyDbHelper(this);
        openMap = findViewById(R.id.openmap);

        restaurant = findViewById(R.id.input_restaurant);
        money = findViewById(R.id.input_money);
        meal = findViewById(R.id.input_meal);
        review = findViewById(R.id.input_review);
        date = findViewById(R.id.input_date);

        SimpleDateFormat dateF = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
        String dat = dateF.format(Calendar.getInstance().getTime());
        date.setText(dat);

        openMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);

            }
        });

    }

    public void create(View v){
        if(restaurant.getText().toString().length()==0 || meal.getText().toString().length()==0 || money.getText().toString().length()==0){
            Toast.makeText(MainActivity.this,"The restaurant, meal and money parameters are needed!",Toast.LENGTH_SHORT).show();
        }
        if(!money.getText().toString().matches("[0-9]+")){
            Toast.makeText(MainActivity.this, "Enter only numbers in the money spent parameter.", Toast.LENGTH_SHORT).show();
        }
        else {
            ContentValues contentValues = new ContentValues();

            boolean r = mHelper.insertItem(restaurant.getText().toString(), money.getText().toString(), meal.getText().toString(), review.getText().toString(), date.getText().toString());
            if (r) {
                restaurant.getText().clear();
                money.getText().clear();
                meal.getText().clear();
                review.getText().clear();


                Toast.makeText(MainActivity.this, "Your visit to "+restaurant.getText().toString()+" was successfully recorded!", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(MainActivity.this, "Your visit to "+restaurant.getText().toString()+" could NOT be recorded!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void history(View v){

        Intent intent = new Intent(MainActivity.this, visits.class);
        startActivity(intent);

    }





}
