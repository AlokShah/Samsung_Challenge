package com.example.alokshah.samsung_movieapp;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by alokshah on 3/17/18.
 */

public class Adapter extends FragmentStatePagerAdapter {
    int mTotalTabs;

    public Adapter(FragmentManager fm, int mTotalTabs) {
        super(fm);
        this.mTotalTabs = mTotalTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                NowPlayingFragment tab1 = new NowPlayingFragment();
                return tab1;
            case 1:
                UpcomingMovieFragment tab2 = new UpcomingMovieFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTotalTabs;
    }
}