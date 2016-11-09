package com.example.tienbi.readbook.adapter.FragmentStatePagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tienbi.readbook.fragment.MainFragmentFavourite;
import com.example.tienbi.readbook.fragment.MainFragmentSreach;
import com.example.tienbi.readbook.fragment.MainFragmentTop;
import com.example.tienbi.readbook.fragment.MainFragmentTopic;

/**
 * Created by TienBi on 21/09/2016.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter{
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MainFragmentTopic f1= new MainFragmentTopic();
                return f1;
            case 1:
                MainFragmentFavourite f2=new MainFragmentFavourite();
                return f2;
            case 2:
                MainFragmentSreach f3=new MainFragmentSreach();
                return f3;
            case 3:
                MainFragmentTop f4= new MainFragmentTop();
                return f4;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
