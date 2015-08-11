package com.example.thomas.next.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thomas.next.R;
import com.example.thomas.next.object.Stage;
import com.example.thomas.next.util.AppUtil;

import java.util.ArrayList;

/**
 * The adapter to show the stages in listview.
 */
public class StageAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private Activity activity;
    /** The list of levelListItems **/
    private ArrayList<Stage> levels;
    /** The level the user is actually. **/
    private int actualLevel;
    private View v;

    /**
     * The Constructor of the StageAdapter.
     * @param levels the LevelListItems
     * @param actualLevel the level the user is actually
     * @param activity
     */
    public StageAdapter(ArrayList<Stage> levels, int actualLevel, Activity activity) {
        this.activity = activity;
        this.actualLevel = actualLevel;
        inflater = (LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.levels = levels;
    }

    @Override
    public int getCount() {
        return levels.size();
    }

    @Override
    public Object getItem(int position) {
        return levels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        v = convertView;
        if (v == null)
            v = inflater.inflate(R.layout.stage_item, null);
        ((TextView)v.findViewById(R.id.levelGroupName)).setText(levels.get(position).getName());
        ((TextView)v.findViewById(R.id.levelGroupSize)).setText(String.valueOf(levels.get(position).getSize()));
        ((ImageView)v.findViewById(R.id.levelImage)).setImageResource(levels.get(position).getImageRessource());

        if (position < levels.size() % 10 && actualLevel >= position*10) {
            v.setBackgroundResource(R.drawable.rounded_background);
        }
        return v;
    }
    @Override
    public boolean isEnabled(int position){
        if (position < levels.size() % 10 && actualLevel >= position*10) {
            return true;
        }
        return false;
    }
    @Override
    public boolean areAllItemsEnabled(){
        return false;
    }

    /**
     * Sets the actual level of the user.
     */
    public void setLevel() {
        this.actualLevel = AppUtil.getActualLevel();
    }
}
