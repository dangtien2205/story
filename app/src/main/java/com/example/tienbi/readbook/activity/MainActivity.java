package com.example.tienbi.readbook.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.example.tienbi.readbook.adapter.FragmentStatePagerAdapter.MainPagerAdapter;
import com.example.tienbi.readbook.R;

public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    private MainPagerAdapter mainPagerAdapter;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deleteDatabase("dbReadStory1.sqlite");
        addControls();
        addEvents();
    }

    private void addEvents() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void addControls() {
        viewPager = (ViewPager) findViewById(R.id.vqMain);
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.setPageTransformer(true,new CubeOutTransformer());

        tabLayout = (TabLayout) findViewById(R.id.tab_layout1);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(mainPagerAdapter);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_love);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_search);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_rank);
    }
}
