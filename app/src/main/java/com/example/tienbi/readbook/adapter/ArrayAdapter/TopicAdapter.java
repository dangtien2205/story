package com.example.tienbi.readbook.adapter.ArrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tienbi.readbook.mode.Topic;
import com.example.tienbi.readbook.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by TienBi on 18/09/2016.
 */
public class TopicAdapter extends ArrayAdapter<Topic> {
    Context context;
    int resource;
    ArrayList<Topic> objects;

    public TopicAdapter(Context context, int resource, ArrayList<Topic> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, parent, false);

        ImageView iwTopic = (ImageView) convertView.findViewById(R.id.iwTopic);
        TextView txtTopic = (TextView) convertView.findViewById(R.id.txtTopic);
        TextView txtNumber = (TextView) convertView.findViewById(R.id.txtNumber);

        Topic topic = objects.get(position);

        iwTopic.setImageResource(getResId(topic.getImageId(), R.drawable.class));
        txtTopic.setText("Truyện Cười "+topic.getTopic());
        txtNumber.setText(topic.getStories().size()+"");

        return convertView;
    }
    public static int getResId(String variableName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
