package com.example.thomas.next.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.thomas.next.R;
import com.example.thomas.next.util.AppUtil;


public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportActionBar().hide();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    public void play(View view) {
        Intent intent = new Intent(getApplicationContext(), StageMenu.class);
        startActivity(intent);
    }

    public void settings(View view) {
        AppUtil.showDialog("coming soon!", this);
        /*Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);*/
    }
}
