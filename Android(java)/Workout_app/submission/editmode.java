package com.example.project2_dzegarra;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class editmode extends AppCompatActivity {

    Button add;
    ListView listOfExercises;
    ArrayAdapter myAdapter;

    MyDbHelper mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editmode);

        add = (Button) findViewById(R.id.addExercise);
        listOfExercises = (ListView) findViewById(R.id.mylist);
        myAdapter = new ArrayAdapter<String>(this, R.layout.line);
        listOfExercises.setAdapter(myAdapter);

        mHelper = new MyDbHelper(this);

        mCursor = mHelper.getReadableDatabase().query("exercises",new String[]{"exercise"},null,null,null,null,null);

        while(mCursor.moveToNext()){
            myAdapter.add(mCursor.getString(mCursor.getColumnIndex("exercise")));

        }



        listOfExercises.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(editmode.this, exerciseinfo.class);
                intent.putExtra("ar",parent.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

        listOfExercises.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Exercise clicked: "+parent.getItemAtPosition(position).toString());
                AlertDialog.Builder dialog = new AlertDialog.Builder(editmode.this);
                dialog.setTitle("Remove exercise?" )
                        .setIcon(R.drawable.ic_launcher_background)
                        .setMessage("Are you sure you want to remove "+parent.getItemAtPosition(position).toString()+" from the list?")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                dialoginterface.cancel();
                            }})
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {

                                boolean x =mHelper.deleteItem(parent.getItemAtPosition(position).toString());
                                if(x){
                                    Toast.makeText(editmode.this, parent.getItemAtPosition(position).toString()+" has been deleted!", Toast.LENGTH_SHORT).show();
                                    myAdapter.remove(myAdapter.getItem(position));
                                    System.out.println("Number of items in adapter: "+myAdapter.getCount());
                                }
                                else{
                                    Toast.makeText(editmode.this, parent.getItemAtPosition(position).toString()+" could not be deleted!", Toast.LENGTH_SHORT).show();
                                }


                            }
                        }).show();

                return false;
            }
        });



    }

    public void back(View v){
        Intent intent = new Intent(editmode.this, MainActivity.class);
        int[] ar2 = new int[] {1};
        intent.putExtra("ar2", ar2);
        startActivity(intent);
    }

    public void newExercise(View v){

        Intent intent = new Intent(editmode.this, newexercise.class);
        startActivity(intent);
    }


}
