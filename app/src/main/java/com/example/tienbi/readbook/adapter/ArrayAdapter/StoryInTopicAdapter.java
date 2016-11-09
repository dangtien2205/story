package com.example.tienbi.readbook.adapter.ArrayAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tienbi.readbook.App;
import com.example.tienbi.readbook.mode.Story;
import com.example.tienbi.readbook.R;

import java.util.ArrayList;

/**
 * Created by TienBi on 21/09/2016.
 */
public class StoryInTopicAdapter extends BaseAdapter {
    ArrayList<Story> stories;
    public StoryInTopicAdapter(ArrayList<Story> stories) {
        this.stories=stories;
    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public Object getItem(int position) {
        return stories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= LayoutInflater.from(App.getContext());
        convertView = inflater.inflate(R.layout.layout_card_story,parent, false);

        TextView textViewCard1 = (TextView) convertView.findViewById(R.id.txtContent1);
        textViewCard1.setText(stories.get(position).getContent());
        TextView textViewCard2 = (TextView) convertView.findViewById(R.id.txtTitle1);
        textViewCard2.setText(stories.get(position).getTitle());

        return convertView;
    }
}
