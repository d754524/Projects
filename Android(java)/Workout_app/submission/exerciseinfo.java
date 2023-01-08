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

import androidx.appcompat.app.AppCompatActivity;

public class exerciseinfo extends AppCompatActivity {

    MyDbHelper mHelper;
    Cursor mCursor;
    TextView title;
    EditText name, reps, sets ,weight, notes;
    String exerci;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exerciseinfo);

        mHelper = new MyDbHelper(this);
        Intent intent = getIntent();
            exerci = intent.getStringExtra("ar");
            mCursor = mHelper.getReadableDatabase().query("exercises", null, "exercise=?", new String[]{intent.getStringExtra("ar")}, null, null, null);
            title = findViewById(R.id.title);
            title.setText(title.getText()+" "+exerci);
            while (mCursor.moveToNext()) {

                name = findViewById(R.id.nameText);
                name.setText(mCursor.getString(mCursor.getColumnIndexOrThrow("exercise")));
                reps = findViewById(R.id.repsText);
                reps.setText(mCursor.getString(mCursor.getColumnIndexOrThrow("reps")));
                sets = findViewById(R.id.setsText);
                sets.setText(mCursor.getString(mCursor.getColumnIndexOrThrow("sets")));
                weight = findViewById(R.id.weightText);
                weight.setText(mCursor.getString(mCursor.getColumnIndexOrThrow("weight")));
                notes = findViewById(R.id.notesText);
                notes.setText(mCursor.getString(mCursor.getColumnIndexOrThrow("Notes")));
            }

    }


    public void update(View v){

        ContentValues contentValues = new ContentValues();
        contentValues.put("exercise", name.getText().toString());
        contentValues.put("reps", reps.getText().toString());
        contentValues.put("sets", sets.getText().toString());
        contentValues.put("weight", weight.getText().toString());
        contentValues.put("Notes", notes.getText().toString());

        mHelper.getWritableDatabase().update("exercises", contentValues,"exercise=?",new String[]{exerci});

        Intent intent = new Intent(exerciseinfo.this, editmode.class);
        startActivity(intent);
    }



    public void back(View v){

        Intent intent = new Intent(exerciseinfo.this, editmode.class);
        startActivity(intent);

    }
}


