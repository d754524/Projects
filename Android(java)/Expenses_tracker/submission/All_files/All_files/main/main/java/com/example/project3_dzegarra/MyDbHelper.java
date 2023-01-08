package com.example.project3_dzegarra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ExpenseTracker";


    public static final String TABLE_NAME = "expenses";

    public static final String COL_RESTAURANT = "restaurant";
    public static final String COL_MONEYSPENT = "moneyspent";
    public static final String COL_MEAL = "meal";
    public static final String COL_REVIEW = "review";
    public static final String COL_DATE = "date";

    public static int version = 2;

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE expenses (restaurant TEXT, moneyspent TEXT, meal TEXT, review TEXT, date TEXT, PRIMARY KEY (restaurant, meal, date) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS expenses");
        onCreate(db);
    }


    public boolean insertItem(String restaurant, String moneyspent, String meal, String review, String date){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_RESTAURANT, restaurant);
        contentValues.put(COL_MONEYSPENT, moneyspent);
        contentValues.put(COL_MEAL, meal);
        contentValues.put(COL_REVIEW, review);
        contentValues.put(COL_DATE, date);

        long result = DB.insert("expenses", null, contentValues);
        return result != -1;

    }

    public boolean deleteItem(String date, String restaurant, String meal){

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from expenses where date=? and restaurant=? and meal=?",new String[]{date, restaurant, meal});



        if(cursor.getCount()>0){

            long result = DB.delete("expenses", "date=? and restaurant=? and meal=? ", new String[]{date, restaurant, meal});

            if(result==0){
                return false;
            }
            else{
                return true;
            }
        }else{
            return false;
        }
    }


    public Cursor getData(String date, String restaurant, String meal){

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from expenses where meal=? and restaurant=? and date=?",new String[]{meal,restaurant,date});
        return cursor;
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(oldVersion);
    }

    public boolean emptyTable(){
        SQLiteDatabase DB = this.getWritableDatabase();
        long delet = DB.delete("expenses",null,null);
        if(delet==0){
            return false;
        }
        else{
            return true;
        }
    }


}
