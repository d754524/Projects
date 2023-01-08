package com.example.project2_dzegarra;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Workout";


    public static final String TABLE_NAME = "exercises";
    public static final String COL_NAME = "exercise";
    public static final String COL_REPS = "reps";
    public static final String COL_SETS = "sets";
    public static final String COL_WEIGHT = "weight";
    public static final String COL_NOTES = "Notes";
    public static int version = 1;

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE exercises (exercise TEXT PRIMARY KEY, reps TEXT, sets TEXT, weight TEXT, Notes TEXT)");
        initializeDb("SitUp", "8", "4", "0", "Align shoulders and feet",db);
        initializeDb("PushUp", "12", "4", "0", "This is a good warmup before bench pressing",db);
        initializeDb("Bicep curl", "8", "4", "0", "Keep your body static as you perform this exercise",db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS exercises");
        onCreate(db);
    }


    public boolean insertItem(String exerciseName, String reps, String sets, String weight, String notes){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("exercise", exerciseName);
        contentValues.put(COL_REPS, reps);
        contentValues.put(COL_SETS, sets);
        contentValues.put(COL_WEIGHT, weight);
        contentValues.put(COL_NOTES, notes);

        long result = DB.insert("exercises", null, contentValues);
        if(result==-1){

            return false;
        }
        else{
            return true;
        }

    }

    public boolean deleteItem(String item){

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from exercises where exercise=?",new String[]{item});



        if(cursor.getCount()>0){

            long result = DB.delete("exercises", "exercise=?", new String[]{item});

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


    public Cursor getData(String item){

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from exercises where exercise=?",new String[]{item});
        return cursor;
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(oldVersion);
    }


    public boolean initializeDb(String exerciseName, String reps, String sets, String weight, String notes, SQLiteDatabase db){


        ContentValues contentValues = new ContentValues();
        contentValues.put("exercise", exerciseName);
        contentValues.put(COL_REPS, reps);
        contentValues.put(COL_SETS, sets);
        contentValues.put(COL_WEIGHT, weight);
        contentValues.put(COL_NOTES, notes);

        long result = db.insert("exercises", null, contentValues);
        if(result==-1){

            return false;
        }
        else{
            return true;
        }

    }

}
