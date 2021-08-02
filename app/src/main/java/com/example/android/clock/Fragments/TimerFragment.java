package com.example.android.clock.Fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.clock.R;

import java.util.Locale;

public class TimerFragment extends Fragment {

    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView timerTextView;
    private Button buttonStartPause;
    private Button buttonReset;
    private boolean timerRunning;

    private CountDownTimer countDownTimer;

    private long timeLeftInMillis = START_TIME_IN_MILLIS;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View timerRootView = inflater.inflate(R.layout.timer_list_layout, container, false);

        timerTextView = timerRootView.findViewById(R.id.timer_text_view);
        buttonStartPause = timerRootView.findViewById(R.id.start_pause);
        buttonReset = timerRootView.findViewById(R.id.reset);

        buttonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerRunning){
                    pauseTimer();
                }else{
                    startTimer();
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownTimer();

        return timerRootView;
    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownTimer();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                buttonStartPause.setText("Start");
                buttonStartPause.setVisibility(View.INVISIBLE);
                buttonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        timerRunning = true;
        buttonStartPause.setText("Pause");
        buttonReset.setVisibility(View.INVISIBLE);

    }

    private void pauseTimer(){
        countDownTimer.cancel();
        timerRunning = false;
        buttonStartPause.setText("Start");
        buttonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer(){
        timeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownTimer();
        buttonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownTimer(){
        int minutes = (int) (timeLeftInMillis/1000)/60;
        int seconds = (int) (timeLeftInMillis/1000)%60;

        String timerTextString = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerTextView.setText(timerTextString);
    }
}
