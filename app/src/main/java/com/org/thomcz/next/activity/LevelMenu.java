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
import android.widget.Toast;

import com.org.thomcz.next.object.Level;
import com.org.thomcz.next.R;
import com.org.thomcz.next.adapter.LevelAdapter;

import java.util.ArrayList;

/**
 * The Menu to choose a level.
 */
public class LevelMenu extends AppCompatActivity {
    /** The level of the stage. **/
    private ArrayList<Level> levels;
    private LevelAdapter levelAdapter;
    private ListView seriesListview;

    public final static String ACTUAL_LEVEL = "actual_level";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);
        levels = getIntent().getParcelableArrayListExtra(StageMenu.STAGE_LEVELS);
        Toast.makeText(getApplicationContext(), "onCreate: levels.getIntent: " +levels.size(), Toast.LENGTH_SHORT).show();
        /*if (levels == null) {
            levels = savedInstanceState.getParcelableArrayList(StageMenu.STAGE_LEVELS);
            Toast.makeText(getApplicationContext(), "onCreate: levels=savedInstance: " +levels.size(), Toast.LENGTH_SHORT).show();

        }*/
        levelAdapter = new LevelAdapter(levels, this);

        seriesListview = ((ListView) findViewById(R.id.series_listview));
        seriesListview.setAdapter(levelAdapter);
        seriesListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent level = new Intent(getApplicationContext(), LevelActivity.class);
                level.putExtra(ACTUAL_LEVEL, levels.get(position));
                startActivityForResult(level, 0);
            }
        });
    }

    /*@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList(StageMenu.STAGE_LEVELS, levels);
    }*/
    /*@Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        levels = savedInstanceState.getParcelableArrayList(StageMenu.STAGE_LEVELS);
    }*/

    /*@Override
    protected void onPause() {

    }
    @Override
      protected void onResume() {

    }*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                Level l = data.getParcelableExtra(ACTUAL_LEVEL);
                setLevel(l);
                levelAdapter.notifyDataSetChanged();
                Intent intent = new Intent();
                intent.putExtra(StageMenu.STAGE_LEVELS, levels);
                this.setResult(Activity.RESULT_OK, intent);
            }
        }
    }

    /**
     * Sets the actual level of the user.
     */
    private void setLevel(Level oldLevel) {
        //this.actualLevel = AppUtil.getActualLevel();
        int index = (oldLevel.getId() % 10);
        if (index < levels.size() - 1) {
            levels.get(index + 1).setUnlocked(true);
        }
        levels.get(index).setSolved(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
