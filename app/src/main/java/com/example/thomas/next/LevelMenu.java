package com.example.thomas.next;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class LevelMenu extends ActionBarActivity {
    private ArrayList<Level> levels;
    private int actualLevel;
    private SeriesAdapter seriesAdapter;
    private ListView seriesListview;
    private SharedPrefs sharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);
        //int index = getIntent().getIntExtra("gay", 0);
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
                /*MainMenu activity = (MainMenu) getParent();
                if (activity != null) {
                    activity.levelUp();
                    seriesAdapter.notifyDataSetChanged();
                    setResult(Activity.RESULT_OK);
                }*/
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_menu, menu);
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
