package com.example.mtg.gui;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;


public class GameTimer {
    private static String TAG = "TASKTIMER";
    private long START_TIME_IN_MILLIS;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long TimeLeftInMillis;
    private TextView TextViewCountDown;
    private long mEndTime;

    private int starthours;
    private int startminutes;
    private int startseconds;

    public GameTimer(TextView TextViewCountDown){
        this.starthours = 0;
        this.startminutes = 1;
        this.startseconds = 0;
        this.TextViewCountDown = TextViewCountDown;
        START_TIME_IN_MILLIS = convertTimeToMilliseconds();
        TimeLeftInMillis = START_TIME_IN_MILLIS;


    }


    private long convertTimeToMilliseconds(){
        double total_minutes = (starthours *60) + (startseconds *0.0166666667) + (startminutes);
        long millis = Math.round(total_minutes*60000);
        return millis;
    }


    public void startTimer() {

        mEndTime = System.currentTimeMillis() + TimeLeftInMillis;
        Log.d("TIMER", String.valueOf(mEndTime));
        mCountDownTimer = new CountDownTimer(TimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                TextViewCountDown.setText("DONE");
                //TODO: endGame
            }
        }.start();


        mTimerRunning = true;

    }

    public void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    public void resetTimer() {
        pauseTimer();
        TimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
    }

    public void updateCountDownText() {
        int hours = (int) (TimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((TimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (TimeLeftInMillis / 1000) % 60;
        TextViewCountDown.setText(formatTime(hours, minutes, seconds));

    }

    public void showStartTime(){
        TextViewCountDown.setText(formatTime(starthours, startminutes, startseconds));
    }

    private String formatTime(int hours, int minutes, int seconds){
        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }
        return timeLeftFormatted;

    }

}
