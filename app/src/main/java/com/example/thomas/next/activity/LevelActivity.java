package com.example.thomas.next.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thomas.next.object.Level;
import com.example.thomas.next.R;
import com.example.thomas.next.util.AppUtil;
import com.example.thomas.next.util.SharedPrefs;

/**
 * The Activity that shows the level and takes the user input.
 */
public class LevelActivity extends AppCompatActivity {
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
        actualLevel = getIntent().getParcelableExtra(LevelMenu.ACTUAL_LEVEL);
        level = AppUtil.getActualLevel();
        series = (TextView) findViewById(R.id.series);
        answer = (EditText) findViewById(R.id.answer);

        series.setText(actualLevel.getSeries());
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
            AppUtil.showDialog(msg, this);
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
            showDialog(actualLevel.getDescription());
        }
        else {
            AppUtil.showDialog(getResources().getString(R.string.false_answer), this);
        }
    }

    private void showDialog(String msg) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (level == actualLevel.getId()) {
                    SharedPrefs.addScore(getApplicationContext(), actualLevel.getScore());
                    setResult(Activity.RESULT_OK);
                }
                activity.finish();
            }
        };
        AppUtil.showDialog(msg, this, onClickListener);
    }
}
