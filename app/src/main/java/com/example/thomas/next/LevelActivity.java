package com.example.thomas.next;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class LevelActivity extends ActionBarActivity {
    private Level actualLevel;
    private int level;
    private LevelActivity activity;
    private TextView series;
    private EditText answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        activity = this;
        actualLevel = getIntent().getParcelableExtra("level");
        level = getIntent().getIntExtra("levelInt", 0);
        series = (TextView) findViewById(R.id.series);
        answer = (EditText) findViewById(R.id.answer);

        series.setText(actualLevel.getSeries());

        //setLevel();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    /*private void setLevel() {
        switch (Integer.valueOf(hits.getText().toString())) {
            case 1: actualLevel = new Level(new int[]{0,2,4,6,8}, 10, "+2");break;
            case 2: actualLevel = new Level(new int[]{1,3,2,4,3}, 5, "+2, -1");break;
            case 3: actualLevel = new Level(new int[]{0,1,2,4,8}, 16, "2^n");break;
            default:actualLevel = new Level(new int[]{0,1,2,3,4}, 5, "+1");
        }
        series.setText(actualLevel.getSeries());
    }

    private void incrHits() {
        int old = Integer.valueOf(hits.getText().toString());
        hits.setText(String.valueOf(old + 1));
    }
    private void incrLose() {
        lose.setText(String.valueOf(Integer.valueOf(lose.getText().toString()) + 1));
    }*/

    public void sendAnswer(View view) {
        int a = Integer.valueOf(answer.getText().toString());
        if (a == actualLevel.getResult()) {
            AlertDialog ad = new AlertDialog.Builder(this).create();
            ad.setCancelable(false); // This blocks the 'BACK' button
            ad.setMessage("You did it!");
            ad.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //incrHits();
                    //setLevel();
                    if (level == actualLevel.getId())
                        setResult(Activity.RESULT_OK);
                    activity.finish();
                }
            });
            ad.show();
        }
        else {
            AlertDialog ad = new AlertDialog.Builder(this).create();
            ad.setCancelable(false); // This blocks the 'BACK' button
            ad.setMessage("nope");
            ad.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //incrLose();
                }
            });
            ad.show();
        }
    }

    public void test(View v) {
        Intent i = new Intent(getApplicationContext(), MainMenu.class);
        startActivity(i);
    }
}
