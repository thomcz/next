package com.example.thomas.next;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * The Adapter to show the series.
 */
public class SeriesAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Activity activity;
    /** List of all levels of the stage. **/
    private ArrayList<Level> level;
    /** actual level of the user. **/
    private int actualLevel;

    public SeriesAdapter(ArrayList<Level> level, int actualLevel, Activity activity) {
        this.level = level;
        this.actualLevel = actualLevel;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null)
            v = inflater.inflate(R.layout.level_item, null);
        ((TextView)v.findViewById(R.id.series_text)).setText(level.get(position).getSeries());
        if (level.get(position).getId() <= actualLevel) {
            v.setBackgroundResource(R.drawable.rounded_background);
        } else {
            v.setBackgroundResource(R.drawable.rounded_background_gray);
        }
        return v;
    }

    @Override
    public boolean isEnabled(int position) {
       if (level.get(position).getId() <= actualLevel)
           return true;
       return false;
    }

    /**
     * Sets the actual level.
     * @param actualLevel to set.
     */
    public void setLevel(int actualLevel) {
        this.actualLevel = actualLevel;
    }
}
