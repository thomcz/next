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
    public final static int RESULT_BACK_PRESSED = 42;
    public final static int RESULT_SOLVED = 14;

    public final static String ACTUAL_LEVEL = "actual_level";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);
        levels = getIntent().getParcelableArrayListExtra(StageMenu.STAGE_LEVELS);
        levelAdapter = new LevelAdapter(levels, this);

        seriesListview = ((ListView) findViewById(R.id.series_listview));
        seriesListview.setAdapter(levelAdapter);
        seriesListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openLevel(position);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && data != null) {
            if (resultCode == Activity.RESULT_OK) {
                setActivityResult(data);
                Level l = data.getParcelableExtra(ACTUAL_LEVEL);
                openLevel((l.getId() % 10) + 1);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                setActivityResult(data);
            } else if (resultCode == RESULT_BACK_PRESSED) {
                Level l = data.getParcelableExtra(ACTUAL_LEVEL);
                for (int i = (l.getId() % 10) - 1; !levels.get(i).getSolved(); i--) {
                    levels.get(i).setSolved(true);
                    levels.get(i).setUnlocked(true);
                }
            }
        }
    }

    private void openLevel(int position) {
        Intent level = new Intent(getApplicationContext(), LevelActivity.class);
        level.putExtra(ACTUAL_LEVEL, levels.get(position));
        startActivityForResult(level, 0);
    }

    private void setActivityResult(Intent data) {
        Level l = data.getParcelableExtra(ACTUAL_LEVEL);
        setLevel(l);
        levelAdapter.notifyDataSetChanged();
        Intent intent = new Intent();
        intent.putExtra(StageMenu.STAGE_LEVELS, levels);
        this.setResult(Activity.RESULT_OK, intent);
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
