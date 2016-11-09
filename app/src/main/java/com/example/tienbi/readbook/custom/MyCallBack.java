package com.example.tienbi.readbook.custom;

import android.view.View;
import android.widget.Toast;

import com.example.tienbi.readbook.App;
import com.zys.brokenview.BrokenCallback;

/**
 * Created by TienBi on 23/09/2016.
 */
public class MyCallBack extends BrokenCallback {
    @Override
    public void onFalling(View v) {
        Toast.makeText(App.getContext(), "Nhẹ tay thôi mày ,bể màn hình giờ", Toast.LENGTH_SHORT).show();
    }
}
