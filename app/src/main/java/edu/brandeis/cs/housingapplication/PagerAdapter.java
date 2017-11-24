package edu.brandeis.cs.housingapplication;

/**
 * Created by Kevin on 11/23/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                UserListFragment userfragment = new UserListFragment();
                return userfragment;
            case 1:
                AddressFragment addressFragment = new AddressFragment();
                return addressFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 2;
    }
}