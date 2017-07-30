package com.example.android.dicegame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    final Handler handler = new Handler();
    int userOverallScore;
    int userTurnScore;
    int computerOverallScore;
    int computerTurnScore;


    final Runnable r = new Runnable() {
        public void run() {
            // do something after delay time
            Button hold = (Button) (findViewById(R.id.holdid));
            Button roll = (Button) (findViewById(R.id.rollid));

            hold.setEnabled(false);
            roll.setEnabled(false);

            int numb, flag;

            flag = 0;
            numb = roll();

            if (numb == 1) {
                computerTurnScore = 0;
                flag = 1;
            } else {
                computerTurnScore = numb + computerTurnScore;
                displayComputerTurnScore();
            }

            if (computerTurnScore > 20)
                flag = 1;

            if (flag == 1) {
                computerOverallScore = computerOverallScore + computerTurnScore;
                displayComputerOverallScore();
                computerTurnScore = 0;
                displayComputerTurnScore();


                hold.setEnabled(true);
                roll.setEnabled(true);

                checkWinner();

            } else
                handler.postDelayed(r, 2000);

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void userRoll(View view) {
        int num;
        num = roll();
        if (num == 1) {
            userTurnScore = 0;
            displayUserTurnScore();
            computerTurn();
        } else {
            userTurnScore = userTurnScore + num;

            displayUserTurnScore();
        }
    }

    private void displayUserTurnScore() {
        TextView userTurnScore1 = (TextView) findViewById(R.id.userTurnScore);
        userTurnScore1.setText("User Turn Score : " + userTurnScore);
    }

    private void displayUserOverallScore() {
        TextView userOverallScore1 = (TextView) findViewById(R.id.userOverallScore);
        userOverallScore1.setText("User Overall Score : " + userOverallScore);
    }

    private void displayComputerTurnScore() {
        TextView computerTurnScore1 = (TextView) findViewById(R.id.computerTurnScore);
        computerTurnScore1.setText("Computer Turn Score : " + computerTurnScore);
    }

    private void displayComputerOverallScore() {
        TextView computerOverallScore1 = (TextView) findViewById(R.id.computerOverallScore);
        computerOverallScore1.setText("Computer Overall Score : " + computerOverallScore);
    }

    public void hold(View view) {
        boolean check;
        userOverallScore = userOverallScore + userTurnScore;
        userTurnScore = 0;
        displayUserTurnScore();
        displayUserOverallScore();
        check = checkWinner();
        if (!check)
            computerTurn();
    }

    public void reset(View view) {
        userOverallScore = 0;
        userTurnScore = 0;
        computerOverallScore =
                computerTurnScore = 0;
        displayUserOverallScore();
        displayUserTurnScore();
        displayComputerOverallScore();
        displayComputerTurnScore();
    }

    private int roll() {                           /* HELPER FUNCTION */

        int number;
        Random rand = new Random();
        number = rand.nextInt(6) + 1;

        ImageView dice = (ImageView) (findViewById(R.id.imageView));
        switch (number) {
            case 1:
                dice.setImageResource(R.drawable.dice1);
                break;

            case 2:
                dice.setImageResource(R.drawable.dice2);
                break;

            case 3:
                dice.setImageResource(R.drawable.dice3);
                break;

            case 4:
                dice.setImageResource(R.drawable.dice4);
                break;

            case 5:
                dice.setImageResource(R.drawable.dice5);
                break;

            case 6:
                dice.setImageResource(R.drawable.dice6);
        }

        return number;
    }

    private void computerTurn() {

        Button hold = (Button) (findViewById(R.id.holdid));
        Button roll = (Button) (findViewById(R.id.rollid));

        hold.setEnabled(false);
        roll.setEnabled(false);
        handler.postDelayed(r, 2000);

    }

    private boolean checkWinner() {
        if (userOverallScore > 100) {
            Toast.makeText(getApplicationContext(), "YOU WON" , Toast.LENGTH_SHORT).show();
            userOverallScore = 0;
            userTurnScore = 0;
            computerOverallScore = 0;
            computerTurnScore = 0;
            displayUserOverallScore();
            displayUserTurnScore();
            displayComputerOverallScore();
            displayComputerTurnScore();
            return true;
        }
        if (computerOverallScore > 100) {
            Toast.makeText(getApplicationContext(), "COMPUTER WON", Toast.LENGTH_SHORT).show();
            userOverallScore = 0;
            userTurnScore = 0;
            computerOverallScore = 0;
            computerTurnScore = 0;
            displayUserOverallScore();
            displayUserTurnScore();
            displayComputerOverallScore();
            displayComputerTurnScore();
            return true;
        }
        return false;
    }


}
