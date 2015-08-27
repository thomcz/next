package com.example.thomas.next.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.thomas.next.adapter.StageAdapter;
import com.example.thomas.next.object.Level;
import com.example.thomas.next.R;
import com.example.thomas.next.util.AppUtil;
import com.example.thomas.next.object.Stage;

import java.util.ArrayList;

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
    private ArrayList<Stage> levelItems;
    private ListView levelList;

    public final static String STAGE_LEVELS = "stage_levels";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_menu);

        levels = AppUtil.getLevel(this);
        levelItems = AppUtil.getStages(this, levels);
        actualLevel = AppUtil.getActualLevel();
        stageAdapter = new StageAdapter(levelItems, actualLevel, this);
        levelList = ((ListView)findViewById(R.id.level_listview));
        levelList.setAdapter(stageAdapter);
        levelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent levelMenu = new Intent(getApplicationContext(), LevelMenu.class);
                levelMenu.putParcelableArrayListExtra(STAGE_LEVELS, levelItems.get(position).getLevels());
                startActivityForResult(levelMenu, 0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                stageAdapter.setLevel();
                stageAdapter.notifyDataSetChanged();
            }
        }
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
            String message = getResources().getString(R.string.your_highscore) + "<b>" + AppUtil.getHighscore() + "</b>";
            AppUtil.showDialog(message, this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
