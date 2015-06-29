package com.example.thomas.next;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by thomas on 15.05.15.
 */
public class LevelAdapter extends BaseAdapter{
    private TextView groupName;
    private TextView groupSize;
    private LayoutInflater inflater=null;
    private Activity activity;
    private ArrayList<LevelListItem> levels;
    private int actualLevel;
    private View v;

    public LevelAdapter(ArrayList<LevelListItem> levels, int actualLevel, Activity activity) {
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
            v = inflater.inflate(R.layout.level_menu, null);
        ((TextView)v.findViewById(R.id.levelGroupName)).setText(levels.get(position).getName());
        ((TextView)v.findViewById(R.id.levelGroupSize)).setText(String.valueOf(levels.get(position).getSize()));
        ((ImageView)v.findViewById(R.id.levelImage)).setImageResource(levels.get(position).getImageRessource());
        /*switch (position) {
            case 0: if (actualLevel >= 0) {v.setBackgroundResource(R.drawable.rounded_background);} break;
            case 1: if (actualLevel >= 10) {v.setBackgroundResource(R.drawable.rounded_background);} break;
            case 2: if (actualLevel >= 20) {v.setBackgroundResource(R.drawable.rounded_background);} break;
            default:;
        }*/
        if (position < levels.size() % 10 && actualLevel >= position*10) {
            v.setBackgroundResource(R.drawable.rounded_background);
        }
        return v;
    }
    @Override
    public boolean isEnabled(int position){
        /*switch (position) {
            case 0: if (actualLevel >= 0) {return true;} break;
            case 1: if (actualLevel >= 10) {return true;} break;
            case 2: if (actualLevel >= 20) {return true;} break;
            default: return false;
        }*/
        if (position < levels.size() % 10 && actualLevel >= position*10) {
            return true;
        }
        return false;
    }
    @Override
    public boolean areAllItemsEnabled(){
        return false;
    }

    public void setLevel(int actualLevel) {
        this.actualLevel = actualLevel;
    }
}
