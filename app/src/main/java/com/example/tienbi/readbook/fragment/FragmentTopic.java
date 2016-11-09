package com.example.tienbi.readbook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tienbi.readbook.adapter.ArrayAdapter.StoryAdapter;
import com.example.tienbi.readbook.manager.TopicManager;
import com.example.tienbi.readbook.R;
import com.nhaarman.listviewanimations.appearance.simple.SwingRightInAnimationAdapter;

/**
 * Created by TienBi on 21/09/2016.
 */
public class FragmentTopic extends Fragment {
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_topic, container, false);

        ListView lvTopic = (ListView) view.findViewById(R.id.lvStory);
        StoryAdapter topicAdapter = new StoryAdapter(getActivity(), R.layout.layout_item_story, TopicManager.getInstance().getTopics().get(index).getStories());
        topicAdapter.setTopic(index);
        SwingRightInAnimationAdapter animationAdapter = new SwingRightInAnimationAdapter(topicAdapter);
        animationAdapter.setAbsListView(lvTopic);
        lvTopic.setAdapter(animationAdapter);

        return view;
    }
}
