package com.example.tienbi.readbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tienbi.readbook.adapter.ArrayAdapter.BottomSheetAdapter;
import com.example.tienbi.readbook.adapter.ArrayAdapter.StoryInTopicAdapter;
import com.example.tienbi.readbook.manager.TopicManager;
import com.example.tienbi.readbook.R;
import link.fls.swipestack.SwipeStack;

/**
 * Created by TienBi on 21/09/2016.
 */
public class StoryInTopicActivity extends Activity {
    private SwipeStack swipeStack;
    private StoryInTopicAdapter storyInTopicAdapter;
    private int total = 0;
    private int num = 0;
    private TextView txt;

    private GridView bottomSheet;
    private ArrayAdapter<Integer> bottomSheetAdapter;
    private BottomSheetBehavior sheetBehavior;
    private Integer[] bottomItems = {R.drawable.ic_back, R.drawable.ic_like, R.drawable.ic_share};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storyintopic);

        addControls();
        addEvents();
    }

    private void addEvents() {
        swipeStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                updateNumberPager();
            }

            @Override
            public void onViewSwipedToRight(int position) {
                updateNumberPager();
            }

            @Override
            public void onStackEmpty() {
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
                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        Toast.makeText(getBaseContext(), "truyện thứ " + (total - num + 1), Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getBaseContext(), "Share this on Facebook", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void updateNumberPager() {
        num--;
        txt.setText(num + "/" + total);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void addControls() {
        Intent inten = getIntent();
        int index = inten.getIntExtra("INDEX", -1);

        storyInTopicAdapter = new StoryInTopicAdapter(TopicManager.getInstance().getTopics().get(index).getStories());
        num = total = TopicManager.getInstance().getTopics().get(index).getStories().size();
        swipeStack = (SwipeStack) findViewById(R.id.swipeStack);
        swipeStack.setAdapter(storyInTopicAdapter);

        txt = (TextView) findViewById(R.id.txtNumberPager1);
        txt.setText(num + "/" + total);

        bottomSheet = (GridView) findViewById(R.id.bottom_sheet1);
        //set bottom sheet(GridView) adapter
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
            overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
