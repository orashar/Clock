package com.example.android.clock;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.clock.Fragments.AlarmFragment;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.container_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new AlarmFragment()).commit();

    }
}
