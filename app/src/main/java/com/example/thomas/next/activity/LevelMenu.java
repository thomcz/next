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

import com.example.thomas.next.object.Level;
import com.example.thomas.next.R;
import com.example.thomas.next.adapter.SeriesAdapter;
import com.example.thomas.next.util.SharedPrefs;

import java.util.ArrayList;

/**
 * The Menu to choose a level.
 */
public class LevelMenu extends ActionBarActivity {
    /** The level of the stage. **/
    private ArrayList<Level> levels;
    /** The users actuall level. **/
    private int actualLevel;
    private SeriesAdapter seriesAdapter;
    private ListView seriesListview;
    private SharedPrefs sharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);
        sharedPrefs = new SharedPrefs();
        levels = getIntent().getParcelableArrayListExtra("gay");
        actualLevel = getIntent().getIntExtra("levelInt", 0);
        seriesAdapter = new SeriesAdapter(levels, actualLevel , this);

        seriesListview = ((ListView)findViewById(R.id.series_listview));
        seriesListview.setAdapter(seriesAdapter);
        seriesListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent level = new Intent(getApplicationContext(), LevelActivity.class);

                level.putExtra("level", levels.get(position));
                level.putExtra("levelInt", actualLevel);
                startActivityForResult(level, 0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                actualLevel++;
                Intent intent = new Intent();
                intent.putExtra("levelInt", actualLevel);
                this.setResult(Activity.RESULT_OK, intent);
                seriesAdapter.setLevel(actualLevel);
                seriesAdapter.notifyDataSetChanged();
                sharedPrefs.saveLevel(this,actualLevel);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_level_menu, menu);
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
