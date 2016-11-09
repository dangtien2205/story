package com.example.tienbi.readbook.fragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tienbi.readbook.activity.TopicActivity;
import com.example.tienbi.readbook.adapter.ArrayAdapter.TopicAdapter;
import com.example.tienbi.readbook.App;
import com.example.tienbi.readbook.custom.MyListView;
import com.example.tienbi.readbook.manager.TopicManager;
import com.example.tienbi.readbook.R;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;

/**
 * Created by TienBi on 20/09/2016.
 */
public class MainFragmentTopic extends Fragment {
    public static final int STATE_NOMARL=111;
    public static final int STATE_3D=112;
    public static final int TOGGLE_ROTATION_MENU_ITEM = 113;
    public static final int TOGGLE_LIGHTING_MENU_ITEM = 114;
    private MyListView mListView;
    private ListView lvTopic;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view=inflater.inflate(R.layout.layout_fragment_main_topic,container,false);
        lvTopic=(ListView)view.findViewById(R.id.lvTopic);
        TopicAdapter topicAdapter=new TopicAdapter(App.getContext(),R.layout.layout_item_topic, TopicManager.getInstance().getTopics());
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(topicAdapter);
        animationAdapter.setAbsListView(lvTopic);
        lvTopic.setAdapter(animationAdapter);

        TopicAdapter topicAdapter1=new TopicAdapter(App.getContext(),R.layout.layout_item_topic1, TopicManager.getInstance().getTopics());
        mListView = (MyListView)view.findViewById(R.id.mylist);
        mListView.setAdapter(topicAdapter1);
        mListView.setVisibility(View.GONE);

        lvTopic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(App.getContext(),TopicActivity.class);
                intent.putExtra("INDEX",position);
                startActivity(intent);
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                   getActivity().overridePendingTransition(R.anim.appear_bottom_right_in, R.anim.appear_bottom_right_out);
               }else {
                   getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
               }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(App.getContext(), TopicActivity.class);
                    intent.putExtra("INDEX", position);
                    startActivity(intent);
                }
            });
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        SubMenu sub3= menu.addSubMenu("Thiết Lập Chế Độ 3D");
        sub3.add(0,TOGGLE_ROTATION_MENU_ITEM,0,"Toggle Rotation");
        sub3.add(0,TOGGLE_LIGHTING_MENU_ITEM,1,"Toggle Lighting");
        menu.add(0,STATE_3D,0,"Chế độ 3D");
        menu.add(0,STATE_NOMARL,0,"Chế độ mặc định");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case STATE_NOMARL:
                lvTopic.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
                return true;
            case STATE_3D:
                mListView.setVisibility(View.VISIBLE);
                lvTopic.setVisibility(View.GONE);
                return true;
            case TOGGLE_ROTATION_MENU_ITEM:
                mListView.enableRotation(!mListView.isRotationEnabled());
                return true;
            case TOGGLE_LIGHTING_MENU_ITEM:
                mListView.enableLight(!mListView.isLightEnabled());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
