package com.example.tienbi.readbook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tienbi.readbook.adapter.ArrayAdapter.StoryFavouriteAdapter;
import com.example.tienbi.readbook.Interface.UpdateAdapter;
import com.example.tienbi.readbook.manager.TopicManager;
import com.example.tienbi.readbook.R;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;

/**
 * Created by TienBi on 21/09/2016.
 */
public class MainFragmentFavourite extends Fragment implements UpdateAdapter{
    ScaleInAnimationAdapter animationAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_main_favourite,container,false);

        ListView lvStoryFavourite = (ListView) view.findViewById(R.id.lvFavourite);
        StoryFavouriteAdapter storyFavouriteAdapter=new StoryFavouriteAdapter(getActivity(),R.layout.layout_item_story_favourite, TopicManager.getInstance().loadStoryFavourite());
        storyFavouriteAdapter.setUpdateAdapter(this);
        animationAdapter = new ScaleInAnimationAdapter(storyFavouriteAdapter);
        animationAdapter.setAbsListView(lvStoryFavourite);
        lvStoryFavourite.setAdapter(animationAdapter);

        return view;
    }

    @Override
    public void update() {
        animationAdapter.notifyDataSetChanged();
    }
}
