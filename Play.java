package com.example.swetasamaddar.scarnesdice;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import android.os.Handler;

public class Play extends AppCompatActivity {

    private static int User_Score = 0;
    private static int Computer_Score = 0;
    private static int Computer_Turn = 0;
    private static int User_Turn = 1;
    private  int Tscore=0;
    private int dice=0;
    private static String User_String = "Your Score :";
    private static String Computer_String = "Computer Score:";
    Button play;
    Button hold;
    Button reset;
    TextView score ;
    Random ran = new Random();
    ImageView img;
    TextView turn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        play = (Button) findViewById(R.id.play);
        hold = (Button) findViewById(R.id.hold);
        reset=(Button)findViewById(R.id.reset);
        score = (TextView) findViewById(R.id.score);
        img=(ImageView)findViewById(R.id.image);
        turn=(TextView)findViewById(R.id.turn);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(User_Score>=100) {
                    Toast.makeText(getBaseContext(), "You Win!!", Toast.LENGTH_SHORT).show();
                    hold.setEnabled(false);
                    play.setEnabled(false);
                }
                else
                {
                    dice = ran.nextInt(6) +1;
                    System.out.println(dice);
                    if(dice==1)
                    {
                        System.out.println("in");
                        img.setImageResource(R.drawable.dice1);
                        play.setEnabled(false);
                        hold.setEnabled(false);
                        score.setText(User_String+User_Score+Computer_String+Computer_Score);
                        turn.setText("Computer's Turn");
                        Toast.makeText(getBaseContext(),"Computer Turn",Toast.LENGTH_SHORT).show();
                        turn.setText("Score in this turn :"+Tscore);
                        Tscore = 0;
                        PlayComputer();
                    }
                    else {
                        System.out.println("in2");
                        Tscore += dice;
                        rollDice(dice);
                        turn.setText("Score in this turn :"+Tscore);
                    }
                }
                if(User_Score>=100) {
                    Toast.makeText(getBaseContext(), "You Win!!", Toast.LENGTH_SHORT).show();
                    hold.setEnabled(false);
                    play.setEnabled(false);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Computer_Score=0;
                User_Score=0;
                Tscore=0;
                hold.setEnabled(true);
                play.setEnabled(true);
                score.setText(User_String+User_Score+Computer_String+Computer_Score);
            }
        });
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User_Score>=100) {
                    Toast.makeText(getBaseContext(), "You Win!!", Toast.LENGTH_SHORT).show();
                    hold.setEnabled(false);
                    play.setEnabled(false);
                }
                else {
                    play.setEnabled(false);
                    User_Score += Tscore;
                    Tscore = 0;
                    hold.setEnabled(false);
                    score.setText(User_String + User_Score + Computer_String + Computer_Score);
                    Toast.makeText(getBaseContext(), "Computer Turn", Toast.LENGTH_SHORT).show();
                    PlayComputer();
                }
                //

            }
        });

    }
    void rollDice(int dice)
    {
        switch (dice) {
            case 2: img.setImageResource(R.drawable.dice2); break;
            case 3: img.setImageResource(R.drawable.dice3); break;
            case 4: img.setImageResource(R.drawable.dice4); break;
            case 5: img.setImageResource(R.drawable.dice5); break;
            case 6: img.setImageResource(R.drawable.dice6);break;
        }
    }
    void PlayComputer() {

        if (Computer_Score >= 100) {
            score.setText(User_String + User_Score + Computer_String + Computer_Score);
            Toast.makeText(getBaseContext(), "Computer Wins!!", Toast.LENGTH_SHORT).show();
            hold.setEnabled(false);
            play.setEnabled(false);

        }
        else {
            int dice = ran.nextInt(6) + 1;
            if (dice == 1) {
                play.setEnabled(true);
                hold.setEnabled(true);
                img.setImageResource(R.drawable.dice1);
                turn.setText("Your Turn");
                turn.setText("Score in this dice :"+dice);
                Tscore=0;
                Toast.makeText(getBaseContext(), "Your Turn=>", Toast.LENGTH_SHORT).show();
            } else {
                Tscore += dice;
                rollDice(dice);
                turn.setText("Score in this dice :"+dice);
                if (Tscore < 15) {

                    Handler han = new Handler();
                    han.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            PlayComputer();
                        }
                    }, 2000);
                } else {
                    Computer_Score += Tscore;
                    score.setText(User_String + User_Score + Computer_String + Computer_Score);
                    play.setEnabled(true);
                    hold.setEnabled(true);
                    Tscore=0;
                    turn.setText("Your Turn");
                    Toast.makeText(getBaseContext(), "Your Turn=>", Toast.LENGTH_SHORT).show();

                }
            }
        }
          /*  while (dice != 1 && Tscore <= 10) {

                try
                { dice = ran.nextInt(6)+1;

                       if (dice == 1) {
                           play.setEnabled(true);
                           hold.setEnabled(true);
                           img.setImageResource(R.drawable.dice1);
                           break;
                       }
                       else {
                           Tscore += dice;
                           switch(dice)
                           {

                               case 2:img.setImageResource(R.drawable.dice2);Thread.sleep(1000);break;
                               case 3:img.setImageResource(R.drawable.dice3);Thread.sleep(1000);break;
                               case 4:img.setImageResource(R.drawable.dice4);Thread.sleep(1000);break;
                               case 5:img.setImageResource(R.drawable.dice5);Thread.sleep(1000);break;
                               case 6:img.setImageResource(R.drawable.dice6);Thread.sleep(1000);break;
                           }
                       }

               }catch(Exception e){}




            }
        if(dice!=1)
        {
            Computer_Score+=Tscore;
            play.setEnabled(true);
            hold.setEnabled(true);
            score.setText(User_String+User_Score+Computer_String+Computer_Score);
        }
        else {
            play.setEnabled(true);
            hold.setEnabled(true);
            score.setText(User_String+User_Score+Computer_String+Computer_Score);

        }
        score.setText(User_String+User_Score+Computer_String+Computer_Score);
        if(Computer_Score>=100) {
            Toast.makeText(getBaseContext(), "Computer Wins!!", Toast.LENGTH_SHORT).show();
            hold.setEnabled(false);
            play.setEnabled(false);
        }
*/
    }

}