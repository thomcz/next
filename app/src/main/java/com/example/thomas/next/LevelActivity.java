package com.example.thomas.next;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
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
        getMenuInflater().inflate(R.menu.menu_level, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_description) {
            String msg = getResources().getString(R.string.dont_show_description);
            if (level > actualLevel.getId()) {
                msg = actualLevel.getResultString();
            }
            showDialog(msg, false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void sendAnswer(View view) {
        int a = Integer.valueOf(answer.getText().toString());
        if (a == actualLevel.getResult()) {
            showDialog(actualLevel.getResultString(), true);
        }
        else {
            showDialog(getResources().getString(R.string.false_answer), false);
        }
    }

    private void showDialog(String msg, final boolean right) {
        AlertDialog ad = new AlertDialog.Builder(this).create();
        ad.setCancelable(false); // This blocks the 'BACK' button
        ad.setMessage(Html.fromHtml(msg));
        ad.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (right) {
                    if (level == actualLevel.getId())
                        setResult(Activity.RESULT_OK);
                    activity.finish();
                }
            }
        });
        ad.show();
    }
}
