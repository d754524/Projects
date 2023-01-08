package com.example.project2_dzegarra;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class doaworkout extends AppCompatActivity {

    Button btn;
    MyDbHelper mHelper;
    Cursor mCursor;
    ListView list;
    ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.doaworkout);

        btn = findViewById(R.id.backbtn);
        list = (ListView) findViewById(R.id.exercisesList);
        mAdapter = new ArrayAdapter<String>(doaworkout.this, R.layout.line);
        list.setAdapter(mAdapter);

        mHelper = new MyDbHelper(this);
        mCursor = mHelper.getReadableDatabase().query("exercises",new String[]{"exercise"},null,null,null,null,null);
        while(mCursor.moveToNext()){
            mAdapter.add(mCursor.getString(0));
        }



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("Range")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mCursor = mHelper.getReadableDatabase().query("exercises",null,"exercise=?",new String[]{parent.getItemAtPosition(position).toString()},null,null,null);

                while(mCursor.moveToNext()) {
                    Toast toast = new Toast(doaworkout.this);

                    toast =Toast.makeText(doaworkout.this, mCursor.getString(0)+"\n\n"+"Reps: "+mCursor.getString(1)+"  Sets: "+mCursor.getString(2)+"  Weight: "+mCursor.getString(3)+"\n\nNotes: "+mCursor.getString(4)+"\t\t\t", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();

                }
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                mAdapter.remove(parent.getItemAtPosition(position));

                return false;
            }
        });





    }

    public void back(View v){
        Intent intent = new Intent(doaworkout.this, MainActivity.class);
        startActivity(intent);
    }


}
