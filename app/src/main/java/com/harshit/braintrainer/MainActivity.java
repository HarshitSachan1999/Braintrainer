package com.harshit.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton, playAgainButton;
    TextView timerTextView, resultTextView, pointTextView, problemTextView;
    CountDownTimer countDownTimer;
    int a, b, ansTag, score = 0, questions = 0;
    Random rand = new Random();
    Button button0, button1, button2, button3;
    ArrayList answers = new ArrayList<Integer>();
    Boolean status = true;
    ConstraintLayout gameLayout;

    public void update(){
        a = rand.nextInt(51);
        b = rand.nextInt(51);
        ansTag = rand.nextInt(4);
        for(int i = 0; i<4; i++){
            if(i == ansTag)
                answers.add(a+b);
            else
                answers.add(rand.nextInt(101));
        }
        button0.setText(answers.get(0).toString());
        button1.setText(answers.get(1).toString());
        button2.setText(answers.get(2).toString());
        button3.setText(answers.get(3).toString());
        problemTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        answers.clear();
    }


    public void startTimer(){
        countDownTimer = new CountDownTimer(30000 + 300, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished/1000 < 10)
                    timerTextView.setText("0"+Long.toString(millisUntilFinished/1000)+"s");
                else
                    timerTextView.setText(Long.toString(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("00s");
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setVisibility(View.VISIBLE);
                resultTextView.setText("Your Score : "+pointTextView.getText().toString());
                status = false;
                score = 0;
                questions = 0;
            }
        }.start();
    }


    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        startTimer();
    }

    public void answer(View view){
        if(status) {
            if (Integer.toString(ansTag).equals(view.getTag().toString()))
                score++;
            questions++;
            pointTextView.setText(Integer.toString(score) + "/" + Integer.toString(questions));
            update();
        }
        else
            Toast.makeText(MainActivity.this, "TIMES UP!!", Toast.LENGTH_SHORT).show();
    }

    public void playAgain(View view){
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        startTimer();
        status = true;
        pointTextView.setText("0/0");
        update();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout = findViewById(R.id.gameLayout);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        startButton = findViewById(R.id.startButton);
        playAgainButton = findViewById(R.id.playAgainButton);
        timerTextView = findViewById(R.id.timerTextView);
        resultTextView = findViewById(R.id.resultTextView);
        pointTextView = findViewById(R.id.pointTextView);
        problemTextView = findViewById(R.id.problemTextView);
        update();
    }
}
