package com.example.tienbi.readbook.adapter.ArrayAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.tienbi.readbook.activity.StoryActivity;
import com.example.tienbi.readbook.App;
import com.example.tienbi.readbook.Interface.UpdateAdapter;
import com.example.tienbi.readbook.manager.TopicManager;
import com.example.tienbi.readbook.mode.Story;
import com.example.tienbi.readbook.R;

import java.util.ArrayList;

/**
 * Created by TienBi on 23/09/2016.
 */
public class StoryFavouriteAdapter extends ArrayAdapter<Story> {
    Activity context;
    int resource;
    ArrayList<Story> objects;
    UpdateAdapter updateAdapter;

    public void setUpdateAdapter(UpdateAdapter updateAdapter) {
        this.updateAdapter = updateAdapter;
    }

    public StoryFavouriteAdapter(Activity context, int resource, ArrayList<Story> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater=context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(resource,parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(objects.get(position).getTitle());

        holder.btnDisLike.setOnClickListener(onDisLikeListener(position, holder));
        holder.btnRead.setOnClickListener(onReadListener(position, holder));

        return convertView;
    }

    private View.OnClickListener onReadListener(final int position,final ViewHolder holder) {
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(App.getContext(), StoryActivity.class);
                intent.putExtra("INDEX",position);
                intent.putExtra("STORY",objects);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                App.getContext().startActivity(intent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    context.overridePendingTransition(R.anim.unzoom_in, R.anim.unzoom_out);
                }else {
                    context.overridePendingTransition(R.anim.flip_vertical_in, R.anim.flip_vertical_out);
                }
            }
        };
    }

    private View.OnClickListener onDisLikeListener(final int position,final ViewHolder holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.remove(position);
                holder.swipeLayout.close();
                updateAdapter.update();
                TopicManager.getInstance().xulyLike(objects.get(position).getIdstory(),0);
                objects.get(position).setIsLike(0);
            }
        };
    }


    private class ViewHolder {
        private TextView title;
        private View btnRead;
        private View btnDisLike;
        private SwipeLayout swipeLayout;

        public ViewHolder(View v) {
            swipeLayout = (SwipeLayout)v.findViewById(R.id.swipe_layout);
            btnRead = v.findViewById(R.id.iwRead);
            btnDisLike = v.findViewById(R.id.iwdislike);
            title = (TextView) v.findViewById(R.id.txtTitle3);
            swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        }
    }
}
