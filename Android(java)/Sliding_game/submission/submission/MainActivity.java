package com.example.project1_dzegarra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void playThree(View v){
        Intent intent = new Intent(MainActivity.this, threebythree.class);
        startActivity(intent);

    }
    public void playFour(View v){
        Intent intent = new Intent(MainActivity.this, fourbyfour.class);
        startActivity(intent);

    }

}