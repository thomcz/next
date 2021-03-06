package com.org.thomcz.next.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.org.thomcz.next.object.Level;
import com.org.thomcz.next.R;
import com.org.thomcz.next.util.AppUtil;
import com.org.thomcz.next.util.SharedPrefs;

/**
 * The Activity that shows the level and takes the user input.
 */
public class LevelActivity extends AppCompatActivity {
    /** The actual level. **/
    private Level actualLevel;
    /** The level the user is actually. **/
    //private int level;
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
        series = (TextView) findViewById(R.id.series);
        answer = (EditText) findViewById(R.id.answer);
        answer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    findViewById(R.id.send).performClick();
                    return true;
                }
                return false;
            }
        });
        addSeries();
    }

    private void addSeries() {
        String[] split = actualLevel.getSeries().split("\\s+");
        StringBuilder temp = new StringBuilder();
        StringBuilder text = new StringBuilder();
        for (String s : split) {
            float actualSize = series.getPaint().measureText(temp.append(s + " ").toString());
            if (actualSize < AppUtil.getDisplaySize(this).x) {
                text.append(s + " ");
            } else {
                text.deleteCharAt(text.length() - 1);
                text.append("\n" + s + " ");
                temp.setLength(0);
            }
        }
        series.setText(text.toString().trim());
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
            if (actualLevel.getSolved()) {
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
        } else if (result == 42) {
            AppUtil.showDialog(getResources().getString(R.string.answer_42), this);
        }
        else {
            AppUtil.showDialog(getResources().getString(R.string.false_answer), this);
        }
    }

    private void showDialog(String msg) {

        OnClickListener positiveCL = null;
        if (actualLevel.getSolved() || actualLevel.getId() % 10 == 9) {
            positiveCL = null;
        } else {
            positiveCL = getClickListener(Activity.RESULT_OK);
        }
        OnClickListener negativeCL = getClickListener(Activity.RESULT_CANCELED);

        AppUtil.showDialog(msg, this, positiveCL, negativeCL);
    }

    private OnClickListener getClickListener(final int result) {
        return new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (!actualLevel.getSolved()) {
                    SharedPrefs.addScore(getApplicationContext(), actualLevel);
                    Intent intent = new Intent();
                    intent.putExtra(LevelMenu.ACTUAL_LEVEL, actualLevel);
                    setResult(result, intent);
                }
                activity.finish();
            }
        };
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(LevelMenu.ACTUAL_LEVEL, actualLevel);
        setResult(LevelMenu.RESULT_BACK_PRESSED, intent);
        activity.finish();
    }
}
