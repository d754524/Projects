package com.example.project2_dzegarra;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class newexercise extends AppCompatActivity {

    MyDbHelper mHelper;
    EditText name, reps, sets ,weight, notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newexercise);


        mHelper = new MyDbHelper(this);

        name = findViewById(R.id.nameText);
        reps = findViewById(R.id.repsText);
        sets = findViewById(R.id.setsText);
        weight = findViewById(R.id.weightText);
        notes = findViewById(R.id.notesText);
    }


    public void create(View v){
        if(name.getText().toString().length()==0){
            Toast.makeText(newexercise.this,"Exercise name is missing!",Toast.LENGTH_SHORT).show();
        }
        else {
            ContentValues contentValues = new ContentValues();

            boolean r = mHelper.insertItem(name.getText().toString(), reps.getText().toString(), sets.getText().toString(), weight.getText().toString(), notes.getText().toString());
            if (r) {
                Toast.makeText(newexercise.this, "New exercise added!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(newexercise.this, editmode.class);
                startActivity(intent);
            } else {
                Toast.makeText(newexercise.this, "New exercise could not be created!", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public void back(View v){

        Intent intent = new Intent(newexercise.this, editmode.class);
        startActivity(intent);

    }
}


