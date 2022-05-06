package com.example.quizzie;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AdapterLogin extends FragmentStatePagerAdapter {
    private Context context;
    int numTabs;

    public AdapterLogin(FragmentManager fm, Context context, int numTabs) {
        super(fm);
        this.context = context;
        this.numTabs = numTabs;
    }

    @Override
    public int getCount() {
        return numTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragLogin();
            case 1:
                return new FragSignup();
            default:
                return null;
        }
    }
}
