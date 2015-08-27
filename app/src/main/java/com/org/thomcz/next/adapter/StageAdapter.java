package com.org.thomcz.next.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.org.thomcz.next.R;
import com.org.thomcz.next.object.Level;
import com.org.thomcz.next.object.Stage;
import com.org.thomcz.next.util.AppUtil;

import java.util.ArrayList;

/**
 * The adapter to show the stages in listview.
 */
public class StageAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private Activity activity;
    /** The list of levelListItems **/
    private ArrayList<Stage> stages;
    /** The level the user is actually. **/
    private int actualLevel;
    private View v;

    /**
     * The Constructor of the StageAdapter.
     * @param stages the StageListItems
     * @param actualLevel the level the user is actually
     * @param activity
     */
    public StageAdapter(ArrayList<Stage> stages, int actualLevel, Activity activity) {
        this.activity = activity;
        this.actualLevel = actualLevel;
        inflater = (LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.stages = stages;
    }

    @Override
    public int getCount() {
        return stages.size();
    }

    @Override
    public Object getItem(int position) {
        return stages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        v = convertView;
        if (v == null)
            v = inflater.inflate(R.layout.stage_item, null);
        ((TextView)v.findViewById(R.id.stage_name)).setText(stages.get(position).getName());

        final String actualStageScore = String.valueOf(getActualStageScore(stages.get(position), position));
        ((TextView)v.findViewById(R.id.actual_stage_score)).setText(activity.getResources().getString(R.string.actual_stage_score) + actualStageScore);

        final String stageScore = String.valueOf(getStageScore(stages.get(position)));
        ((TextView)v.findViewById(R.id.stage_score)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = stages.get(position).getName() + ": " + "<b>" + actualStageScore + " / " + stageScore +"</b>";
                AppUtil.showDialog(message, activity);
            }
        });
        ((TextView)v.findViewById(R.id.stage_score)).setText(stageScore);

        ((ImageView)v.findViewById(R.id.stage_Image)).setImageResource(stages.get(position).getImageRessource());

        if (position < stages.size() % 10 && actualLevel >= position * 10) {
            v.setBackgroundResource(R.drawable.rounded_background);
        }
        return v;
    }
    @Override
    public boolean isEnabled(int position){
        if (position < stages.size() % 10 && actualLevel >= position * 10) {
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

    private int getStageScore(Stage stage) {
        int score = 0;
        for (Level level : stage.getLevels()) {
            score += level.getScore();
        }
        return score;
    }
    private int getActualStageScore(Stage stage, int position) {
        int score = 0;
        int index =  actualLevel - (position * 10);
        for (int i = 0; i < stage.getLevels().size() && index > 0; i++) {
            score += stage.getLevels().get(i).getScore();
            index--;
        }
        return score;
    }
}
