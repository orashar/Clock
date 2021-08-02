package com.example.android.clock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.Nullable;


public class PickerActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_picker_layout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.8), (int) (height*0.6));

        Button okBtn = findViewById(R.id.ok_picker);
        Button cancelBtn = findViewById(R.id.cancel_picker);
        final TimePicker timePicker = findViewById(R.id.time_picker);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hourToSet = timePicker.getCurrentHour();
                int minToSet = timePicker.getCurrentMinute();
                String ampm = "am";

                if(hourToSet == 0){
                    hourToSet = 12;
                }
                else if(hourToSet > 12){
                    hourToSet -= 12;
                    ampm = "pm";
                }

                String timeString = hourToSet+":"+minToSet+" "+ampm;

                Intent mainIntent = new Intent();
                mainIntent.putExtra("TIME_TO_SET", timeString);
                setResult(RESULT_OK, mainIntent);
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
