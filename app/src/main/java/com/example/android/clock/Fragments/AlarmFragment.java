package com.example.android.clock.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.clock.PickerActivity;
import com.example.android.clock.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment {

    private static String timeToSet;
    private static final int PICKER_REQUEST_CODE = 0;
    private TextView textView;


    public AlarmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View alarmRootView = inflater.inflate(R.layout.alarm_list_layout, container, false);

        Button setBtn = alarmRootView.findViewById(R.id.fab);
        textView = alarmRootView.findViewById(R.id.text_view_alarm);

        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickerIntent = new Intent(getContext(), PickerActivity.class);
                startActivityForResult(pickerIntent, PICKER_REQUEST_CODE);
            }
        });

        return alarmRootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICKER_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                timeToSet = data.getStringExtra("TIME_TO_SET");
                textView.setText(timeToSet);
            }
        }
    }

}
