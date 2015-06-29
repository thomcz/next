package com.example.thomas.next;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainMenu extends ActionBarActivity {
    private int actualLevel;
    private LevelAdapter levelAdapter;
    private ArrayList<Level> levels;
    private ListView levelList;
    private SharedPrefs sharedPrefs;
    private ArrayList<LevelListItem> levelItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        sharedPrefs = new SharedPrefs();
        actualLevel = sharedPrefs.getLevel(getApplicationContext());
        //actualLevel = 2;
        getLevel();
        levelItems = getStages();
        levelAdapter = new LevelAdapter(levelItems, actualLevel, this);
        levelList = ((ListView)findViewById(R.id.level_listview));
        levelList.setAdapter(levelAdapter);
        levelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent levelMenu = new Intent(getApplicationContext(), LevelMenu.class);

                levelMenu.putParcelableArrayListExtra("gay", levelItems.get(position).getLevels());
                levelMenu.putExtra("levelInt", actualLevel);
                startActivityForResult(levelMenu,0);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                actualLevel = data.getIntExtra("levelInt", actualLevel);
                levelAdapter.setLevel(actualLevel);
                levelAdapter.notifyDataSetChanged();
            }
        }
    }
    private ArrayList<LevelListItem> getStages() {
        ArrayList<LevelListItem> level = new ArrayList<LevelListItem>();

        Resources resources = getResources();
        TypedArray name = resources.obtainTypedArray(R.array.name);
        TypedArray size = resources.obtainTypedArray(R.array.size);
        TypedArray bottom = resources.obtainTypedArray(R.array.bottom);
        TypedArray limit = resources.obtainTypedArray(R.array.limit);
        TypedArray image = resources.obtainTypedArray(R.array.image);

        for (int i = 0; i < name.length(); i++) {
            ArrayList<Level> foo = new ArrayList<>(levels.subList(bottom.getInt(i, 0), limit.getInt(i,0)));
            level.add(new LevelListItem(name.getString(i), size.getInt(i, 0), foo, image.getResourceId(i,0)));
        }
        name.recycle();
        size.recycle();
        bottom.recycle();
        limit.recycle();
        image.recycle();

        return level;
    }

    private void getLevel() {
        levels = new ArrayList<Level>();
        Resources resources = getResources();
        TypedArray id = resources.obtainTypedArray(R.array.id);
        TypedArray series = resources.obtainTypedArray(R.array.series);
        TypedArray result = resources.obtainTypedArray(R.array.result);
        TypedArray resultString = resources.obtainTypedArray(R.array.resultString);

        for (int i = 0; i < id.length(); i++) {
            levels.add(new Level(id.getInt(i, 0), series.getString(i), result.getInt(i, 0), resultString.getString(i)));
        }
        id.recycle();
        series.recycle();
        result.recycle();
        resultString.recycle();
    }
    /*private void levelAddTest() {
        levels = new ArrayList<Level>();
        levels.add(new Level(0,new int[]{0,1,2,3,4}, 5, "+1"));
        levels.add(new Level(1,new int[]{0,2,4,6,8}, 10, "+2"));
        levels.add(new Level(2,new int[]{1,3,2,4,3}, 5, "+2, -1"));
        levels.add(new Level(3,new int[]{0,1,2,4,8}, 16, "2^n"));
        levels.add(new Level(4,new int[]{0,3,6,9,12}, 15, "+3"));
        levels.add(new Level(5,new int[]{0,1,2,3,0}, 1, "mod 4"));
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
