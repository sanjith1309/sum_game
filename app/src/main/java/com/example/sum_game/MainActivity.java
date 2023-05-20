package com.example.sum_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startbt;
    boolean CanPlayGame;
    TextView timer,showq,display,count;
    Button b0,b1,b2,b3;
    int locationOfAnswer,totquestion=0,crtquestions=0;
    ArrayList<Integer> arr = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbt = findViewById(R.id.button5);
        timer = findViewById(R.id.textView);
        showq = findViewById(R.id.textView4);
        b0=findViewById(R.id.button);
        b1=findViewById(R.id.button2);
        b2=findViewById(R.id.button3);
        b3=findViewById(R.id.button4);
        display = findViewById(R.id.textView6);
        count=findViewById(R.id.textView5);



        startbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startbt.setVisibility(View.INVISIBLE);
                startgame();

            }
        });
        play();

    }
    public void startgame(){
        CanPlayGame=true;
        CountDownTimer countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf((int)l/1000)+"s");
            }

            @Override
            public void onFinish() {
                startbt.setVisibility(View.VISIBLE);
                crtquestions=0;
                totquestion=0;
                count.setText("0/0");
                CanPlayGame=false;
            }
        }.start();
    }

    public void play(){
        if(CanPlayGame) {
            Random randd = new Random();
            int a = randd.nextInt(21);
            int b = randd.nextInt(21);

            showq.setText(String.valueOf(a) + "+" + String.valueOf(b));

            locationOfAnswer = randd.nextInt(4);

            for (int i = 0; i < 4; i++) {
                if (i == locationOfAnswer) {
                    arr.add(a + b);
                } else {
                    int wrongAns = randd.nextInt(41);
                    while (wrongAns == (a + b)) {
                        wrongAns = randd.nextInt();
                    }
                    arr.add(wrongAns);
                }
            }
            b0.setText(String.valueOf(arr.get(0)));
            b1.setText(String.valueOf(arr.get(1)));
            b2.setText(String.valueOf(arr.get(2)));
            b3.setText(String.valueOf(arr.get(3)));
        }

    }
    public void abcd(View view){

        if (CanPlayGame) {
            String tag = view.getTag().toString();
            if (locationOfAnswer == Integer.parseInt(tag)) {
                display.setText("Correct");
                crtquestions++;
            } else {
                display.setText("Wrong");
            }
            play();
            arr.clear();
            totquestion++;
            count.setText(String.valueOf(crtquestions) + "/" + String.valueOf(totquestion));
        }

    }

}