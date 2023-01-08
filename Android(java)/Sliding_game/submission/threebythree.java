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

public class threebythree extends MainActivity{

    HashMap<Integer,Integer> btns = new HashMap<>();
    HashMap<Integer,Integer> valsToButtons = new HashMap<>();
    HashMap<Integer,Integer> ids = new HashMap<>();
    ArrayList<ArrayList<Integer>> startingVals = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> tracker = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threebythree);


        startingVals.add(new ArrayList<>());
        startingVals.add(new ArrayList<>());
        startingVals.add(new ArrayList<>());

        Random rand = new Random();

        //Create 2d array
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++) {
                int x = rand.nextInt(8);
                x++;
                if (tracker.contains(x)) {
                    j--;
                } else {

                    tracker.add(x);
                    startingVals.get(i).add(x);
//                    System.out.print( startingVals.get(i).get(j)+", ");
                }
                if(tracker.size()==8){
                    break;
                }
            }
//            System.out.println();
        }


        //Set the starting white tile
        int whiteTile = rand.nextInt(9)+1;
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


        //Assign the random numbers from the 2d array to the tiles
        int c = 1;
        for(int i =0; i<3; i++){
            for(int j=0; j<3; j++) {
                if(c == whiteTile) {
                    c++;
                    startingVals.get(i).add(j,99);

                    if(i!=2) {

                        startingVals.get(2).add(startingVals.get(i).get(startingVals.get(i).size() - 1));
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
        valsToButtons.put(startingVals.get(0).get(2),R.id.b3);valsToButtons.put(startingVals.get(1).get(0),R.id.b4);
        valsToButtons.put(startingVals.get(1).get(1),R.id.b5);valsToButtons.put(startingVals.get(1).get(2),R.id.b6);
        valsToButtons.put(startingVals.get(2).get(0),R.id.b7);valsToButtons.put(startingVals.get(2).get(1),R.id.b8);
        valsToButtons.put(startingVals.get(2).get(2),R.id.b9);

        //Map IDs to their values
        ids.put(R.id.b1,1);ids.put(R.id.b2,2);ids.put(R.id.b3,3);ids.put(R.id.b4,4);ids.put(R.id.b5,5);ids.put(R.id.b6,6);
        ids.put(R.id.b7,7);ids.put(R.id.b8,8);ids.put(R.id.b9,9);

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
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){

                if(startingVals.get(i).get(j)==Integer.parseInt(b.getText().toString()) && !b.getText().toString().contains("X")){
                    x=i;
                    y=j;
                }

            }
        }
//        System.out.print("X: "+x+" Y: "+y);

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
        if(y+1<=2){

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
        if(x+1<=2){

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
        if(startingVals.get(0).get(0)+startingVals.get(0).get(1)+startingVals.get(0).get(2)==6 && startingVals.get(0).get(0)==1 && startingVals.get(0).get(1)==2){
            if(startingVals.get(1).get(0)+startingVals.get(1).get(1)+startingVals.get(1).get(2)==15 && startingVals.get(1).get(0)==4 && startingVals.get(1).get(1)==5){
                if(startingVals.get(2).get(0)+startingVals.get(2).get(1)==15 && startingVals.get(2).get(0)==7){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(threebythree.this);
                    dialog.setTitle("YOU WON THE GAME!\nDo you want to play again?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    Intent intent1 = new Intent(threebythree.this, MainActivity.class);
                                    startActivity(intent1);
                                }})
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    Intent intent2 = new Intent(threebythree.this, threebythree.class);
                                    startActivity(intent2);
                                }
                            }).show();
                }
            }
        }
    }


}
