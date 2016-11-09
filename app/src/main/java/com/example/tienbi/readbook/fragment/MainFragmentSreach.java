package com.example.tienbi.readbook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tienbi.readbook.custom.MyCallBack;
import com.example.tienbi.readbook.R;
import com.zys.brokenview.BrokenTouchListener;
import com.zys.brokenview.BrokenView;

/**
 * Created by TienBi on 21/09/2016.
 */
public class MainFragmentSreach extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_main_search,container,false);
        LinearLayout layout2 = (LinearLayout) view.findViewById(R.id.layout2);
        MyCallBack myCallBack = new MyCallBack();
        BrokenView brokenView = BrokenView.add2Window(getActivity());
        brokenView.setCallback(myCallBack);
        BrokenTouchListener listener = new BrokenTouchListener.Builder(brokenView).
                setComplexity(16).
                setBreakDuration(800).
                setFallDuration(1800).
                setCircleRiftsRadius(20).
                build();
        layout2.setOnTouchListener(listener);
        return view;
    }
}
