package com.example.project2_dzegarra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button workoutBtn;
    MyDbHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workoutBtn = findViewById(R.id.doWorkout);
//        mHelper = new MyDbHelper(this);

    }

    public void editMode(View v){
        Intent intent = new Intent(MainActivity.this, editmode.class);
        startActivity(intent);
    }

    public void doAWorkOut(View v){
        Intent intent = new Intent(MainActivity.this, doaworkout.class);
        startActivity(intent);
    }



}