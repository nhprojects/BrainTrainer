package com.example.nilehenry.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    BrainTrainer brainTrainer;
    TextView feedbackTextView;
    TextView finalScoreTextView;
    TextView timeLeftTextView;
    TextView equationTextView;
    TextView fractionRightTextView;
    TextView timesUpNotificationTextView;

    Button playAgainButton;
    Button[] answerButtons;
    CountDownTimer countDownTimer;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer= MediaPlayer.create(this,R.raw.airhorn);
        Log.i("info","started");
        setUp();
    }

    public void startGame(){
       updateBoard(brainTrainer);
        for (Button button: answerButtons){
            button.setVisibility(View.VISIBLE);
        }
        timeLeftTextView.setVisibility(View.VISIBLE);
        equationTextView.setVisibility(View.VISIBLE);
        fractionRightTextView.setVisibility(View.VISIBLE);
        timesUpNotificationTextView.setVisibility(View.INVISIBLE);
        finalScoreTextView.setVisibility(View.INVISIBLE);

        countDownTimer= new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftTextView.setText(Integer.toString((int) (millisUntilFinished/1000)-1));
               if (((int) millisUntilFinished/1000)==1){
                   Log.i("info","time done");
                  endGame();

               }
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    /**
     * initializes all the buttons in game and makes things invisible
     */
    public void setUp(){
        brainTrainer=new BrainTrainer();
        timesUpNotificationTextView=(TextView) findViewById(R.id.timesUpNotification);
        timesUpNotificationTextView.setVisibility(View.INVISIBLE);
        finalScoreTextView= (TextView) findViewById(R.id.finalScoreTextView);
        finalScoreTextView.setVisibility(View.INVISIBLE);
        playAgainButton= (Button) findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);
        feedbackTextView= (TextView) findViewById(R.id.feedbackTextView);
        feedbackTextView.setVisibility(View.INVISIBLE);
        timeLeftTextView=(TextView) findViewById(R.id.timeLeftTextView);
        timeLeftTextView.setVisibility(View.INVISIBLE);
        equationTextView=(TextView) findViewById(R.id.equationTextView);
        equationTextView.setVisibility(View.INVISIBLE);
        fractionRightTextView= (TextView) findViewById(R.id.fractionRightTextView);
        fractionRightTextView.setVisibility(View.INVISIBLE);
        Button button1= (Button) findViewById(R.id.button);
        Button button2= (Button) findViewById(R.id.button2);
        Button button3= (Button) findViewById(R.id.button3);
        Button button4= (Button) findViewById(R.id.button4);
        answerButtons=new Button[] {button1,button2,button3,button4};
        updateBoard(brainTrainer);
        for (Button button: answerButtons){
            button.setVisibility(View.INVISIBLE);
        }
        equationTextView.setText(brainTrainer.getEquation());
    }

    public void endGame(){
        mediaPlayer.start();
        finalScoreTextView.setText("Final Score: "+ brainTrainer.getFractionCorrect());
        setUp();
        feedbackTextView.setVisibility(View.INVISIBLE);
        finalScoreTextView.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.VISIBLE);
        timesUpNotificationTextView.setVisibility(View.VISIBLE);
    }
    public void updateBoard(BrainTrainer brainTrainer){
        int[] tableArray= brainTrainer.getTableArray();
        for (int i=0; i<tableArray.length;i=i+1){
            Button button= answerButtons[i];
            button.setText(Integer.toString(tableArray[i]));
        }
        equationTextView.setText(brainTrainer.getEquation());
        fractionRightTextView.setText(brainTrainer.getFractionCorrect());
    }
    public void OnClick(View view){
        Button button= (Button) view;
        int buttonNumber= Integer.parseInt(button.getText().toString());
        brainTrainer.makeGuess(buttonNumber,feedbackTextView);

        updateBoard(brainTrainer);


    }

    public void goOnClick(View view){
        startGame();
        view.setVisibility(View.INVISIBLE);
    }

}
