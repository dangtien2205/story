package com.example.tienbi.readbook.adapter.ArrayAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.tienbi.readbook.activity.StoryActivity;
import com.example.tienbi.readbook.App;
import com.example.tienbi.readbook.mode.Story;
import com.example.tienbi.readbook.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by TienBi on 21/09/2016.
 */
public class StoryAdapter extends ArrayAdapter<Story> {
    Activity context;
    int resource;
    ArrayList<Story> objects;
    int topic;

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public StoryAdapter(Activity context, int resource, ArrayList<Story> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        convertView = layoutInflater.inflate(resource, parent, false);

        TextView txtTopic = (TextView) convertView.findViewById(R.id.txtName);
        Button imageView = (Button) convertView.findViewById(R.id.btnLike);
        TextView txtColor =(TextView)convertView.findViewById(R.id.txtColor);

        txtTopic.setText(objects.get(position).getTitle());
        if (objects.get(position).isLike()==1)
            imageView.setBackgroundResource(R.drawable.ic_like);
        else
            imageView.setBackgroundResource(R.drawable.ic_dislike);
        Random random= new Random();
        txtColor.setBackgroundColor(Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256)));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(App.getContext(), StoryActivity.class);
                intent.putExtra("INDEX",position);
                intent.putExtra("TOPIC",topic);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                App.getContext().startActivity(intent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    context.overridePendingTransition(R.anim.unzoom_in, R.anim.unzoom_out);
                }else {
                    context.overridePendingTransition(R.anim.flip_vertical_in, R.anim.flip_vertical_out);
                }
            }
        });
        return convertView;
    }
}
