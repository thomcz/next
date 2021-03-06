package com.org.thomcz.next.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.org.thomcz.next.object.Level;
import com.org.thomcz.next.R;
import com.org.thomcz.next.util.AppUtil;

import java.util.ArrayList;


/**
 * The Adapter to show the level.
 */
public class LevelAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Activity activity;
    /** List of all levels of the stage. **/
    private ArrayList<Level> level;

    public LevelAdapter(ArrayList<Level> level, Activity activity) {
        this.level = level;
        this.activity = activity;
        inflater = (LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return level.size();
    }

    @Override
    public Object getItem(int position) {
        return level.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null)
            v = inflater.inflate(R.layout.level_item, null);
        ((TextView)v.findViewById(R.id.level_score)).setText(String.valueOf(level.get(position).getScore()));
        v.findViewById(R.id.level_description_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = level.get(position).getSolved() ? level.get(position).getDescription() :
                        activity.getResources().getString(R.string.dont_show_description);
                AppUtil.showDialog(message, activity);
            }
        });
        if (level.get(position).getUnlocked() && !level.get(position).getSolved()) {
            v.setBackgroundResource(R.drawable.actual_background);
            ((TextView)v.findViewById(R.id.series_text)).setText(level.get(position).getSeries());
        }
        else if (level.get(position).getUnlocked()) {
            v.setBackgroundResource(R.drawable.unlocked_background);
            ((TextView)v.findViewById(R.id.series_text)).setText(level.get(position).getSeries());
        } else {
            v.setBackgroundResource(R.drawable.locked_background_gray);
            ((TextView)v.findViewById(R.id.series_text)).setText(activity.getResources().getString(R.string.series_unknown));
        }

        return v;
    }

    /*@Override
    public boolean isEnabled(int position) {
       //if (level.get(position).getUnlocked())
           return true;
       //return false;
    }*/
}
