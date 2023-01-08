package com.example.project3_dzegarra;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class visits extends AppCompatActivity {

    MyDbHelper mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;

    Button showtotal;
    TextView restaurant_detail, date_detail, money_detail, review_detail;
    ArrayAdapter myAdapter;
    ListView ml;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visits);

        showtotal = findViewById(R.id.showtotal);

        restaurant_detail = findViewById(R.id.detail_restaurant);
        date_detail = findViewById(R.id.detail_date);
        money_detail = findViewById(R.id.detail_money);
        review_detail = findViewById(R.id.detail_review);

        myAdapter = new ArrayAdapter<String>(this, R.layout.line);
        ml = findViewById(R.id.listOfVisits);
        ml.setAdapter(myAdapter);

        mHelper = new MyDbHelper(this);
        mCursor = mHelper.getReadableDatabase().query("expenses",new String[]{"restaurant","date", "meal"},null,null,null,null,null);

        while(mCursor.moveToNext()){

            myAdapter.add(mCursor.getString(mCursor.getColumnIndex("date"))+"           "+mCursor.getString(mCursor.getColumnIndex("restaurant"))+": "+mCursor.getString(mCursor.getColumnIndex("meal")));
        }


        ml.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(visits.this);
                dialog.setTitle( "Are you sure you want to delete this entry" )
                        .setIcon(R.drawable.ic_launcher_background)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                dialoginterface.cancel();
                            }})
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                String[] tempArray = parent.getItemAtPosition(position).toString().split("          ");
                                String[] tempArray2 = tempArray[1].split(":");
                                boolean x =mHelper.deleteItem(tempArray[0], tempArray2[0].trim(), tempArray2[1].trim());
                                if(x){
                                    Toast.makeText(visits.this, tempArray[0]+"  "+tempArray2[0].trim()+":"+tempArray2[1]+" has been deleted!", Toast.LENGTH_SHORT).show();
                                    myAdapter.remove(myAdapter.getItem(position));
                                }
                                else{
                                    Toast.makeText(visits.this, tempArray[0]+"  "+tempArray2[0].trim()+":"+tempArray2[1]+" could not be deleted!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).show();

                return false;
            }
        });

        ml.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] tempArray = parent.getItemAtPosition(position).toString().split("          ");
                String[] tempArray2 = tempArray[1].split(":");
                Cursor tempCur = mHelper.getReadableDatabase().query("expenses",new String[]{"restaurant","date","meal","moneyspent","review"},"date=? and restaurant=? and meal=?",new String[]{tempArray[0], tempArray2[0].trim(), tempArray2[1].trim()},null,null,null);

                while(tempCur.moveToNext()){
                    restaurant_detail.setText("Restaurant:\t"+tempCur.getString(tempCur.getColumnIndex("restaurant")));
                    date_detail.setText("Date:\t"+tempCur.getString(tempCur.getColumnIndex("date")));
                    money_detail.setText("Total spent:\t $"+tempCur.getString(tempCur.getColumnIndex("moneyspent")));
                    review_detail.setText("Review:\t "+tempCur.getString(tempCur.getColumnIndex("review")));

                }
                tempCur.close();
            }
        });

        showtotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = 0;
                if(myAdapter.getCount()==0){
                    restaurant_detail.setText("\t\t\t\t\t\t\t\t\tThe list is empty.");
                    return;
                }
                Cursor tempCur = mHelper.getReadableDatabase().query("expenses",new String[]{"moneyspent"},null,null,null,null,null);
                while(tempCur.moveToNext()){
                    total += Integer.parseInt(tempCur.getString(tempCur.getColumnIndex("moneyspent")));
                    restaurant_detail.setText("");
                    date_detail.setText("");
                    money_detail.setText("");
                    review_detail.setText("");
                    restaurant_detail.setText("You have spent a total of $"+Integer.toString(total));

                }
            }
        });
    }

    public void clearList(View v){
       boolean r = mHelper.emptyTable();
       if(myAdapter.getCount()==0){
           Toast.makeText(visits.this, "There are no entries in the list!", Toast.LENGTH_SHORT).show();
            return;
       }
       if(r){
           myAdapter.clear();
           Toast.makeText(visits.this, "All entries deleted!", Toast.LENGTH_SHORT).show();
           restaurant_detail.setText("");
           date_detail.setText("");
           money_detail.setText("");
       }
       else{
           Toast.makeText(visits.this, "Error deleting entries!", Toast.LENGTH_SHORT).show();

       }
    }

    public void back(View v){

        Intent intent = new Intent(visits.this, MainActivity.class);
        startActivity(intent);
    }

}
