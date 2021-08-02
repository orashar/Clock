package com.example.android.clock.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.clock.R;

import java.text.DateFormat;
import java.util.Date;

public class ClockFragment extends Fragment {

    public ClockFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View clockRootView = inflater.inflate(R.layout.clock_list_layout, container, false);

        Date date = new Date();
        String currentTime = DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);

        TextView clockTextView = clockRootView.findViewById(R.id.clock_date_view);
        clockTextView.setText(currentTime);

        final EditText timeet = clockRootView.findViewById(R.id.timeout_et);
        Button btn = clockRootView.findViewById(R.id.set_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();

                // Check whether has the write settings permission or not.
                boolean settingsCanWrite = Settings.System.canWrite(context);

                if(!settingsCanWrite) {
                    // If do not have write settings permission then open the Can modify system settings panel.
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    startActivity(intent);
                }else {
                    setTimeout(timeet.getText().toString());
                    Toast.makeText(context, "Success.", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return clockRootView;

    }

    private void setTimeout(String screenOffTimeout) {

        if(screenOffTimeout.isEmpty()) screenOffTimeout = "0";
        int time = Integer.parseInt(screenOffTimeout);
        switch (time) {
            case 15000:
                time = 15000;
                break;
            case 30000:
                time = 30000;
                break;
            case 60000:
                time = 60000;
                break;
            case 120000:
                time = 120000;
                break;
            case 300000:
                time = 300000;
                break;
            case 600000:
                time = 600000;
                break;
            case 1800000:
                time = 1800000;
                break;
            default:
                Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                time = -1;
        }
        android.provider.Settings.System.putInt(getContext().getContentResolver(),
                Settings.System.SCREEN_OFF_TIMEOUT, time);
    }

}
