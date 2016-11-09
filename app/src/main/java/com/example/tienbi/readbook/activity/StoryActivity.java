package com.example.tienbi.readbook.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tienbi.readbook.adapter.ArrayAdapter.BottomSheetAdapter;
import com.example.tienbi.readbook.adapter.FragmentStatePagerAdapter.CardFragmentPagerAdapter;
import com.example.tienbi.readbook.custom.ShadowTransformer;
import com.example.tienbi.readbook.manager.TopicManager;
import com.example.tienbi.readbook.mode.Story;
import com.example.tienbi.readbook.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by TienBi on 22/09/2016.
 */
public class StoryActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TextView txtNumberPager;
    private int total;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    private int numberPager;

    private GridView bottomSheet;
    private ArrayAdapter<Integer> bottomSheetAdapter;
    private BottomSheetBehavior sheetBehavior;
    private Integer[] bottomItems = {R.drawable.ic_back, R.drawable.ic_like, R.drawable.ic_share};

    private ArrayList<Story> stories;
    private FloatingActionButton btnLike;
    private FloatingActionButton btnShare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        addControl();
        addEvents();
    }

    private void addEvents() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                numberPager = position + 1;
                txtNumberPager.setText((numberPager) + "/" + total);
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomSheet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        finish();
                        break;
                    case 1:
                        if (stories.get(numberPager - 1).isLike() == 0) {
                            Toast.makeText(getBaseContext(), "Đã chuyển vào mục yêu thích", Toast.LENGTH_SHORT).show();
                            TopicManager.getInstance().xulyLike(stories.get(numberPager - 1).getIdstory(), 1);
                            stories.get(numberPager - 1).setIsLike(1);
                        }
                        break;
                    case 2:
                        Toast.makeText(getBaseContext(), "Đã share trên Facebook", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stories.get(numberPager - 1).isLike() == 0) {
                    Toast.makeText(getBaseContext(), "Đã chuyển vào mục yêu thích", Toast.LENGTH_SHORT).show();
                    TopicManager.getInstance().xulyLike(stories.get(numberPager - 1).getIdstory(), 1);
                    stories.get(numberPager - 1).setIsLike(1);
                }
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Đã share trên Facebook", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addControl() {
        Intent intent = getIntent();
        int index = intent.getIntExtra("INDEX", -1);
        int topic = intent.getIntExtra("TOPIC", -1);

        mViewPager = (ViewPager) findViewById(R.id.vpStory);
        if (topic == -1) {
            stories = (ArrayList<Story>) getIntent().getSerializableExtra("STORY");
        } else {
            stories = TopicManager.getInstance().getTopics().get(topic).getStories();
        }
        mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, this), stories);
        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setAdapter(mFragmentCardAdapter);
        mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(index);

        txtNumberPager = (TextView) findViewById(R.id.txtNumberPager2);
        total = stories.size();
        numberPager = index + 1;
        txtNumberPager.setText((numberPager) + "/" + total);

        bottomSheet = (GridView) findViewById(R.id.bottom_sheet);
        bottomSheetAdapter = new BottomSheetAdapter(this, R.layout.item_grid, bottomItems);
        bottomSheet.setAdapter(bottomSheetAdapter);

        bottomSheet.setTranslationY(getStatusBarHeight());
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            boolean first = true;

            @Override
            public void onStateChanged(View bottomSheet, int newState) {
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                if (first) {
                    first = false;
                    bottomSheet.setTranslationY(0);
                }
            }
        });

        btnLike = (FloatingActionButton) findViewById(R.id.action_like);
        btnShare = (FloatingActionButton) findViewById(R.id.action_share);
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                overridePendingTransition(R.anim.unzoom_in, R.anim.unzoom_out);
            } else {
                overridePendingTransition(R.anim.flip_vertical_in, R.anim.flip_vertical_out);
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
