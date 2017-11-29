
package edu.brandeis.cs.housingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

/**
 * Created by Kevin on 11/23/17.
 */

public class SearchFragmentTabs extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_tabs);
        TabLayout tabLayout=setTags();


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (this.getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener((new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        }));

        BottomNavigationView  bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.search_bottom:
                                startActivity(new Intent(getBaseContext(), SearchFragmentTabs.class));
                                overridePendingTransition(0, 0);
                                break;

                            case R.id.account_bottom:
                                //   startActivity(new Intent(getBaseContext(), SearchFragmentTabs.class));
                                overridePendingTransition(0, 0);
                                break;
                            case R.id.home_bottom:
                                startActivity(new Intent(getBaseContext(), HomeActivity.class));
                                overridePendingTransition(0, 0);
                                break;
                        }
                        return true;
                    }});
    }

    public TabLayout setTags() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_manager);
        tabLayout.addTab(tabLayout.newTab().setText("Users"));
        tabLayout.addTab(tabLayout.newTab().setText("Address"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        return tabLayout;

    }






}