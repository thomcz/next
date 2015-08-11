package com.example.thomas.next.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
public class StageMenu extends ActionBarActivity {
    /** The users actual level. **/
    private int actualLevel;
    private StageAdapter stageAdapter;
    /** List of all levels. **/
    private ArrayList<Level> levels;
    /** List of all stages. **/
    private ArrayList<Stage> levelItems;
    private ListView levelList;

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

                levelMenu.putParcelableArrayListExtra("gay", levelItems.get(position).getLevels());
                //levelMenu.putExtra("levelInt", actualLevel);
                startActivityForResult(levelMenu,0);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                //actualLevel = data.getIntExtra("levelInt", actualLevel);
                stageAdapter.setLevel();
                stageAdapter.notifyDataSetChanged();
            }
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stage_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
