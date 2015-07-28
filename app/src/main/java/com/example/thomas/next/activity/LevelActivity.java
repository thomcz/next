package com.example.thomas.next.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thomas.next.object.Level;
import com.example.thomas.next.R;

/**
 * The Activity that shows the level and takes the user input.
 */
public class LevelActivity extends ActionBarActivity {
    /** The actual level. **/
    private Level actualLevel;
    /** The level the user is actually. **/
    private int level;
    /** This pointer. **/
    private LevelActivity activity;
    /** The TextView that shows the series. **/
    private TextView series;
    /** The EditText that takes the answer. **/
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
        int id = item.getItemId();

        if (id == R.id.action_description) {
            String msg = getResources().getString(R.string.dont_show_description);
            if (level > actualLevel.getId()) {
                msg = actualLevel.getDescription();
            }
            showDialog(msg, false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Checks if the answer is correct and shows a dialog to notify the user.
     * @param view the actual view
     */
    public void sendAnswer(View view) {
        int result = 0;
        try {
            result = Integer.valueOf(answer.getText().toString());
        } catch (NumberFormatException e) {

        }
        if (result == actualLevel.getResult()) {
            showDialog(actualLevel.getDescription(), true);
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
