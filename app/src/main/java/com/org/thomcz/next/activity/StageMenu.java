package com.org.thomcz.next.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.org.thomcz.next.adapter.StageAdapter;
import com.org.thomcz.next.object.Level;
import com.org.thomcz.next.R;
import com.org.thomcz.next.util.AppUtil;
import com.org.thomcz.next.object.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * The Menu to choose a stage.
 */
public class StageMenu extends AppCompatActivity {
    /** The users actual level. **/
    private int actualLevel;
    private StageAdapter stageAdapter;
    /** List of all levels. **/
    private ArrayList<Level> levels;
    /** List of all stages. **/
    private ArrayList<Stage> stages;
    private ListView levelList;

    public final static String STAGE_LEVELS = "stage_levels";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_menu);

        levels = AppUtil.getLevel(this);
        stages = AppUtil.getStages(this, levels);
        stageAdapter = new StageAdapter(stages, actualLevel, this);
        levelList = ((ListView)findViewById(R.id.level_listview));
        levelList.setAdapter(stageAdapter);
        levelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent levelMenu = new Intent(getApplicationContext(), LevelMenu.class);
                levelMenu.putParcelableArrayListExtra(STAGE_LEVELS, stages.get(position).getLevels());
                startActivityForResult(levelMenu, 0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<Level> l = data.getParcelableArrayListExtra(STAGE_LEVELS);
                if (l == null) {
                    return;
                }
                int index = l.get(0).getId() / 10;
                stages.get(index).setLevels(l);
                setUnlocked(index);
                stageAdapter.notifyDataSetChanged();
            }
        }
    }

    private void setUnlocked(int index) {
        if (index < stages.size() - 1) {
            if (allSolved(stages.get(index).getLevels())) {
                stages.get(index + 1).setUnlocked(true);
                stages.get(index + 1).getLevels().get(0).setUnlocked(true);
            }
        } else if (index == stages.size() - 1) {
            if (allSolved(stages.get(index).getLevels())) {
                AppUtil.showDialog(getResources().getString(R.string.congratulation), this);
            }
        }
    }

    private boolean allSolved(List<Level> levelList) {
        for  (Level l : levelList) {
            if (!l.getSolved()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stage_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_highscore) {
            String message = getResources().getString(R.string.your_highscore) + "<b>" + getHighscore() + "</b>";
            AppUtil.showDialog(message, this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int getHighscore() {
        int score = 0;
        for (Stage s : stages) {
            for (Level l : s.getLevels()) {
                if (!l.getSolved()) {
                    return score;
                }
                score += l.getScore();
            }
        }
        return score;
    }
}
