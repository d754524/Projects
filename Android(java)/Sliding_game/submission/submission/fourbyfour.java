package com.example.project1_dzegarra;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class fourbyfour extends MainActivity{

    HashMap<Integer,Integer> btns = new HashMap<>();
    HashMap<Integer,Integer> valsToButtons = new HashMap<>();
    HashMap<Integer,Integer> ids = new HashMap<>();
    ArrayList<ArrayList<Integer>> startingVals = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> tracker = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourbyfour);


        startingVals.add(new ArrayList<>());
        startingVals.add(new ArrayList<>());
        startingVals.add(new ArrayList<>());
        startingVals.add(new ArrayList<>());

        Random rand = new Random();

        //Create 2d array
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++) {
                int x = rand.nextInt(15);
                x++;
                if (tracker.contains(x)) {
                    j--;
                } else {

                    tracker.add(x);
                    startingVals.get(i).add(x);

                }
                if(tracker.size()==15){
                    break;
                }
            }

        }


        //Set the starting white tile
        int whiteTile = rand.nextInt(15)+1;
        switch(whiteTile){
            case 1:Button but1 = findViewById(R.id.b1);
                but1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but1.setText("X");but1.setTextColor(Color.BLACK);break;
            case 2: Button but2 = findViewById(R.id.b2);
                but2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but2.setText("X");but2.setTextColor(Color.BLACK);break;
            case 3: Button but3 = findViewById(R.id.b3);
                but3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but3.setText("X");but3.setTextColor(Color.BLACK);break;
            case 4:Button but4 = findViewById(R.id.b4);
                but4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white))); but4.setText("X");but4.setTextColor(Color.BLACK);break;
            case 5: Button but5 = findViewById(R.id.b5);
                but5.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but5.setText("X");but5.setTextColor(Color.BLACK);break;
            case 6: Button but6 = findViewById(R.id.b6);
                but6.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but6.setText("X");but6.setTextColor(Color.BLACK);break;
            case 7: Button but7 = findViewById(R.id.b7);
                but7.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but7.setText("X");but7.setTextColor(Color.BLACK);break;
            case 8: Button but8 = findViewById(R.id.b8);
                but8.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but8.setText("X");but8.setTextColor(Color.BLACK);break;
            case 9: Button but9 = findViewById(R.id.b9);
                but9.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but9.setText("X");but9.setTextColor(Color.BLACK);break;
            case 10: Button but10 = findViewById(R.id.b10);
                but10.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but10.setText("X");but10.setTextColor(Color.BLACK);break;
            case 11: Button but11 = findViewById(R.id.b11);
                but11.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but11.setText("X");but11.setTextColor(Color.BLACK);break;
            case 12: Button but12 = findViewById(R.id.b12);
                but12.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but12.setText("X");but12.setTextColor(Color.BLACK);break;
            case 13: Button but13 = findViewById(R.id.b13);
                but13.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but13.setText("X");but13.setTextColor(Color.BLACK);break;
            case 14: Button but14 = findViewById(R.id.b14);
                but14.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but14.setText("X");but14.setTextColor(Color.BLACK);break;
            case 15: Button but15 = findViewById(R.id.b15);
                but15.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but15.setText("X");but15.setTextColor(Color.BLACK);break;
            case 16: Button but16 = findViewById(R.id.b16);
                but16.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));but16.setText("X");but16.setTextColor(Color.BLACK);break;
            default:
        }

        //tiles mapped by numbers
        btns.put(1,R.id.b1);
        btns.put(2,R.id.b2);
        btns.put(3,R.id.b3);
        btns.put(4,R.id.b4);
        btns.put(5,R.id.b5);
        btns.put(6,R.id.b6);
        btns.put(7,R.id.b7);
        btns.put(8,R.id.b8);
        btns.put(9,R.id.b9);
        btns.put(10,R.id.b10);
        btns.put(11,R.id.b11);
        btns.put(12,R.id.b12);
        btns.put(13,R.id.b13);
        btns.put(14,R.id.b14);
        btns.put(15,R.id.b15);
        btns.put(16,R.id.b16);


        //Assign the random numbers from the 2d array to the tiles
        int c = 1;
        for(int i =0; i<4;i++){
            for(int j=0; j<4; j++) {
                if(c == whiteTile) {
                    c++;
                    startingVals.get(i).add(j,99);

                    if(i!=3) {

                        startingVals.get(3).add(startingVals.get(i).get(startingVals.get(i).size() - 1));
                        startingVals.get(i).remove(startingVals.get(i).size()-1);
                    }




                } else {

                    if(btns.get(c)==null){
                        return;
                    }
                    else {

                        Button b = findViewById(btns.get(c));
                        b.setText(startingVals.get(i).get(j).toString());

                    }

                    c++;
                }
            }

        }

        //Map values to buttons
        valsToButtons.put(startingVals.get(0).get(0),R.id.b1);valsToButtons.put(startingVals.get(0).get(1),R.id.b2);
        valsToButtons.put(startingVals.get(0).get(2),R.id.b3);valsToButtons.put(startingVals.get(0).get(3),R.id.b4);
        valsToButtons.put(startingVals.get(1).get(0),R.id.b5);valsToButtons.put(startingVals.get(1).get(1),R.id.b6);
        valsToButtons.put(startingVals.get(1).get(2),R.id.b7);valsToButtons.put(startingVals.get(1).get(3),R.id.b8);
        valsToButtons.put(startingVals.get(2).get(0),R.id.b9);valsToButtons.put(startingVals.get(2).get(1),R.id.b10);
        valsToButtons.put(startingVals.get(2).get(2),R.id.b11);valsToButtons.put(startingVals.get(2).get(3),R.id.b12);
        valsToButtons.put(startingVals.get(3).get(0),R.id.b13);valsToButtons.put(startingVals.get(3).get(1),R.id.b14);
        valsToButtons.put(startingVals.get(3).get(2),R.id.b15);valsToButtons.put(startingVals.get(3).get(3),R.id.b16);

        //Map IDs to their values
        ids.put(R.id.b1,1);ids.put(R.id.b2,2);ids.put(R.id.b3,3);ids.put(R.id.b4,4);ids.put(R.id.b5,5);ids.put(R.id.b6,6);
        ids.put(R.id.b7,7);ids.put(R.id.b8,8);ids.put(R.id.b9,9);
        ids.put(R.id.b10,10);ids.put(R.id.b11,11);ids.put(R.id.b12,12);ids.put(R.id.b13,13);ids.put(R.id.b14,14);ids.put(R.id.b15,15);
        ids.put(R.id.b16,16);

    }

    public void tileClicked(View v){

        if(btns.get(ids.get(v.getId()))==null){
            return;
        }
        Button b = findViewById(btns.get(ids.get(v.getId())));

        if(b.getText()=="X"){
            return;
        }

        int x=0,y=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){

                if(startingVals.get(i).get(j)==Integer.parseInt(b.getText().toString()) && !b.getText().toString().contains("X")){
                    x=i;
                    y=j;
                }

            }
        }


        if(y-1>=0){
            if(startingVals.get(x).get(y-1)==99){

                startingVals.get(x).set(y-1, Integer.parseInt(b.getText().toString()));
                startingVals.get(x).set(y,99);
                b.setText("X");
                b.setTextColor(Color.BLACK);
                b.setBackgroundColor(Color.WHITE);
                Button bt = findViewById(valsToButtons.get(99));
                bt.setText(startingVals.get(x).get(y-1).toString());
                bt.setTextColor(Color.BLACK);
                bt.setBackgroundColor(Color.GREEN);
                int t = valsToButtons.get(startingVals.get(x).get(y-1));
                valsToButtons.put(startingVals.get(x).get(y-1),valsToButtons.get(99));
                valsToButtons.put(startingVals.get(x).get(y), t);
                didYouWin();
                return;
            }

        }
        if(y+1<=3){

            if(startingVals.get(x).get(y+1)==99){
                startingVals.get(x).set(y+1, Integer.parseInt(b.getText().toString()));
                startingVals.get(x).set(y,99);
                b.setText("X");
                b.setTextColor(Color.BLACK);
                b.setBackgroundColor(Color.WHITE);
                Button bt = findViewById(valsToButtons.get(99));
                bt.setText(startingVals.get(x).get(y+1).toString());
                bt.setTextColor(Color.BLACK);
                bt.setBackgroundColor(Color.GREEN);
                int t = valsToButtons.get(startingVals.get(x).get(y+1));
                valsToButtons.put(startingVals.get(x).get(y+1),valsToButtons.get(99));
                valsToButtons.put(startingVals.get(x).get(y), t);
                didYouWin();
                return;
            }

        }
        if(x-1>=0){

            if(startingVals.get(x-1).get(y)==99){
                startingVals.get(x-1).set(y, Integer.parseInt(b.getText().toString()));
                startingVals.get(x).set(y,99);
                b.setText("X");
                b.setTextColor(Color.BLACK);
                b.setBackgroundColor(Color.WHITE);
                Button bt = findViewById(valsToButtons.get(99));
                bt.setText(startingVals.get(x-1).get(y).toString());
                bt.setTextColor(Color.BLACK);
                bt.setBackgroundColor(Color.GREEN);
                int t = valsToButtons.get(startingVals.get(x-1).get(y));
                valsToButtons.put(startingVals.get(x-1).get(y),valsToButtons.get(99));
                valsToButtons.put(startingVals.get(x).get(y), t);
                didYouWin();
                return;
            }
        }
        if(x+1<=3){
            if(startingVals.get(x+1).get(y)==99){
                startingVals.get(x+1).set(y, Integer.parseInt(b.getText().toString()));
                startingVals.get(x).set(y,99);
                b.setText("X");
                b.setTextColor(Color.BLACK);
                b.setBackgroundColor(Color.WHITE);
                Button bt = findViewById(valsToButtons.get(99));
                bt.setText(startingVals.get(x+1).get(y).toString());
                bt.setTextColor(Color.BLACK);
                bt.setBackgroundColor(Color.GREEN);
                int t = valsToButtons.get(startingVals.get(x+1).get(y));
                valsToButtons.put(startingVals.get(x+1).get(y),valsToButtons.get(99));
                valsToButtons.put(startingVals.get(x).get(y), t);
                didYouWin();
                return;
            }
        }




    }

    public void didYouWin(){
        if(startingVals.get(0).get(0)+startingVals.get(0).get(1)+startingVals.get(0).get(2)+startingVals.get(0).get(3)==10 && startingVals.get(0).get(0)==1 && startingVals.get(0).get(1)==2 && startingVals.get(0).get(2)==3){
            if(startingVals.get(1).get(0)+startingVals.get(1).get(1)+startingVals.get(1).get(2)+startingVals.get(1).get(3)==26 && startingVals.get(1).get(0)==5 && startingVals.get(1).get(1)==6 && startingVals.get(1).get(2)==7){
                if(startingVals.get(2).get(0)+startingVals.get(2).get(1)+startingVals.get(2).get(2)+startingVals.get(2).get(3)==42 && startingVals.get(2).get(0)==9 && startingVals.get(2).get(1)==10 && startingVals.get(2).get(2)==11){
                    if(startingVals.get(3).get(0)+startingVals.get(3).get(1)+startingVals.get(3).get(2)+startingVals.get(3).get(3)==58 && startingVals.get(3).get(0)==13 && startingVals.get(3).get(1)==14 && startingVals.get(3).get(2)==15) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(fourbyfour.this);
                        dialog.setTitle("YOU WON THE GAME!\nDo you want to play again?")
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialoginterface, int i) {
                                        Intent intent1 = new Intent(fourbyfour.this, MainActivity.class);
                                        startActivity(intent1);
                                    }
                                })
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialoginterface, int i) {
                                        Intent intent2 = new Intent(fourbyfour.this, threebythree.class);
                                        startActivity(intent2);
                                    }
                                }).show();
                    }
                }
            }
        }
    }


}
