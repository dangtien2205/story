package com.example.tienbi.readbook.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;

import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.example.tienbi.readbook.adapter.FragmentStatePagerAdapter.TopicPagerAdapter;
import com.example.tienbi.readbook.custom.MyCallBack;
import com.example.tienbi.readbook.R;
import com.zys.brokenview.BrokenTouchListener;
import com.zys.brokenview.BrokenView;

/**
 * Created by TienBi on 18/09/2016.
 */
public class TopicActivity extends FragmentActivity {
    private ViewPager viewPager1;
    private TopicPagerAdapter topicPagerAdapter;
    private TabLayout tabLayout1;
    private android.support.v7.widget.LinearLayoutCompat layout1;
    private Button btnViewAll;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);


        addControls();
        addEvents();
    }

    private void addEvents() {
        //viewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout1));
        viewPager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                index = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopicActivity.this, StoryInTopicActivity.class);
                intent.putExtra("INDEX", index);
                startActivity(intent);
                overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
            }
        });
    }

    private void addControls() {
        Intent inten = getIntent();
        index = inten.getIntExtra("INDEX", -1);

        viewPager1 = (ViewPager) findViewById(R.id.vqTopic);
        topicPagerAdapter = new TopicPagerAdapter(getSupportFragmentManager());
        viewPager1.setAdapter(topicPagerAdapter);
        viewPager1.setCurrentItem(index);
        viewPager1.setPageTransformer(true, new ForegroundToBackgroundTransformer());

        tabLayout1 = (TabLayout) findViewById(R.id.tab_layout2);
        tabLayout1.setupWithViewPager(viewPager1);
        tabLayout1.setTabsFromPagerAdapter(topicPagerAdapter);

        btnViewAll = (Button) findViewById(R.id.btnViewAll);

        layout1 = (LinearLayoutCompat) findViewById(R.id.layout1);
        MyCallBack myCallBack = new MyCallBack();
        BrokenView brokenView = BrokenView.add2Window(this);
        brokenView.setCallback(myCallBack);
        BrokenTouchListener listener = new BrokenTouchListener.Builder(brokenView).
                setComplexity(15).
                setBreakDuration(800).
                setFallDuration(1800).
                setCircleRiftsRadius(20).
                build();
        layout1.setOnTouchListener(listener);
    }

    @Override
    public void onBackPressed() {
        finish();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            overridePendingTransition(R.anim.appear_bottom_right_in, R.anim.appear_bottom_right_out);
        } else {
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }
}
