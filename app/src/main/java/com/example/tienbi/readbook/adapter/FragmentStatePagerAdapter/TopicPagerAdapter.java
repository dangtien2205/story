package com.example.tienbi.readbook.adapter.FragmentStatePagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tienbi.readbook.fragment.FragmentTopic;
import com.example.tienbi.readbook.manager.TopicManager;

/**
 * Created by TienBi on 21/09/2016.
 */
public class TopicPagerAdapter extends FragmentStatePagerAdapter {
    public TopicPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        FragmentTopic f = new FragmentTopic();
        f.setIndex(position);
        return f;
    }

    @Override
    public int getCount() {
        return TopicManager.getInstance().getTopics().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TopicManager.getInstance().getTopics().get(position).getTopic();
    }
}
