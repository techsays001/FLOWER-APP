package com.msg91.sendotp.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by abdalla on 2/18/18.
 */

public class PageAdapter2 extends FragmentPagerAdapter {

    private int numOfTabs;

    PageAdapter2(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StatusFragment2 ();
            case 1:
//                return new CallFragment();
//            case 2:
                return new StatusFragment22();


            case 2:

                return new StatusFragment222();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
