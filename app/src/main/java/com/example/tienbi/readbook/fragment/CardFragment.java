package com.example.tienbi.readbook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tienbi.readbook.Interface.CardAdapter;
import com.example.tienbi.readbook.mode.Story;
import com.example.tienbi.readbook.R;


public class CardFragment extends Fragment {

    private CardView mCardView;
    private Story story;

    public CardFragment(Story story) {
        this.story=story;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_pager_story, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()* CardAdapter.MAX_ELEVATION_FACTOR);
        TextView txtTitle= (TextView)view.findViewById(R.id.txtTitle2);
        TextView txtContent= (TextView)view.findViewById(R.id.txtContent2);

        txtTitle.setText(story.getTitle());
        txtContent.setText(story.getContent());

        return view;
    }

    public CardView getCardView() {
        return mCardView;
    }
}
