//package edu.brandeis.cs.housingapplication;
//
//import android.content.Context;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.PagerAdapter;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
///**
// * Created by eureyuri on 2017/11/27.
// */
//
//public class CustomPagerAdapter extends FragmentPagerAdapter {
//
//    private static int NUM_ITEMS = 2;
//
//    public CustomPagerAdapter(FragmentManager fragmentManager) {
//        super(fragmentManager);
//    }
//
//    @Override
//    public int getCount() {
//        return NUM_ITEMS;
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//                return SlideshowImageFragment.newInstance(R.drawable.slide1);
//            case 1:
//                return SlideshowImageFragment.newInstance(R.drawable.slide2);
//            default:
//                return null;
//        }
//    }
//
//    // Returns the page title for the top indicator
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return "Tab " + position;
//    }
//
//}
