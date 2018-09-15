package com.example.nilehenry.braintrainer;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class BrainTrainer {
    int[] tableArray;
    Random random;
    int firstNumber;
    int secondNumber;
    int answerNumber;
    int correct;
    int guessed;


    //todo make take guess method

    public BrainTrainer(){
        tableArray=new int[4];
        random=new Random();
        generateCurrentNumber();
        generateTableSequence();
        correct=0;
        guessed=0;
    }

    public int generateRandomNumber(){
        return random.nextInt(50);
    }
    public void generateCurrentNumber(){
        firstNumber=generateRandomNumber();
        secondNumber=generateRandomNumber();
        answerNumber=firstNumber+secondNumber;
    }
    public void generateTableSequence(){
        int chosenIndex= random.nextInt(4);
        tableArray[chosenIndex]=answerNumber;
        for (int i=0;i<4;i=i+1){
            if (i!=chosenIndex){
                int tableNumber=generateRandomNumber();
                while((tableNumber==firstNumber)||(tableNumber==secondNumber)||(tableNumber==answerNumber)){
                    tableNumber=generateRandomNumber();
                }
                tableArray[i]=tableNumber;
            }
        }
    }

    public int[] getTableArray(){
        return tableArray;
    }
    public int getFirstNumber(){
        return firstNumber;
    }
    public int getSecondNumber(){
        return secondNumber;
    }
    public int getAnswerNumber(){
        return answerNumber;
    }
    public String getFractionCorrect(){
        return Integer.toString(correct)+ "/"+ Integer.toString(guessed);
    }
    public String getEquation(){
        return Integer.toString(firstNumber)+ "+" + Integer.toString(secondNumber);
    }

    //todo finish this method
    //todo update buttons in table using tableArray
    //todo implement countdown timer
    //todo change colors of button/stylization
    //todo play again method

    public void makeGuess(int guess, TextView feedbackTextView){
        guessed=guessed+1;
        if (guess==answerNumber){
            correct=correct+1;
            feedbackTextView.setText("Correct");
        }
        else{
            feedbackTextView.setText("Incorrect");
        }
        generateCurrentNumber();
        generateTableSequence();
        feedbackTextView.setVisibility(View.VISIBLE);
    }

}
