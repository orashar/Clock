package com.example.android.clock.Adpater;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android.clock.Fragments.AlarmFragment;
import com.example.android.clock.Fragments.ClockFragment;
import com.example.android.clock.Fragments.TimerFragment;
import com.example.android.clock.R;

public class SimpleFragmentPageAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPageAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new AlarmFragment();
        }
        else if(position == 1){
            return new ClockFragment();
        }else{
            return new TimerFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Alarm";
        } else if(position == 1){
            return "clock";
        }else{
            return "Timer";
        }
    }
}
