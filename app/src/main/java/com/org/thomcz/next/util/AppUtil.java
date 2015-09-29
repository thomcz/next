package com.org.thomcz.next.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.Html;

import com.org.thomcz.next.R;
import com.org.thomcz.next.object.Level;
import com.org.thomcz.next.object.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 28.07.2015.
 */
public class AppUtil {

    public static ArrayList<Stage> getStages(Context context, ArrayList<Level> levels) {
        ArrayList<Stage> level = new ArrayList<Stage>();

        Resources resources = context.getResources();
        TypedArray name = resources.obtainTypedArray(R.array.name);
        TypedArray bottom = resources.obtainTypedArray(R.array.bottom);
        TypedArray limit = resources.obtainTypedArray(R.array.limit);
        TypedArray image = resources.obtainTypedArray(R.array.image);

        for (int i = 0; i < name.length(); i++) {
            ArrayList<Level> stageLevel = new ArrayList<>(levels.subList(bottom.getInt(i, 0), limit.getInt(i,0)));
            level.add(new Stage(name.getString(i), stageLevel.get(0).getUnlocked(), stageLevel, image.getResourceId(i,0)));
        }
        name.recycle();
        bottom.recycle();
        limit.recycle();
        image.recycle();

        return level;
    }

    public static ArrayList<Level> getLevel(Context context) {
        ArrayList<Level> levels = new ArrayList<Level>();
        Resources resources = context.getResources();
        TypedArray id = resources.obtainTypedArray(R.array.id);
        TypedArray series = resources.obtainTypedArray(R.array.series);
        TypedArray result = resources.obtainTypedArray(R.array.result);
        TypedArray resultString = resources.obtainTypedArray(R.array.description);
        TypedArray score = resources.obtainTypedArray(R.array.score);

        ArrayList<Integer> actualScore = SharedPrefs.getScore(context);

        for (int i = 0; i < id.length(); i++) {
            int levelScore = actualScore.size() > i ? actualScore.get(i) : 0;
            int levelId = id.getInt(i, 0);
            levels.add(new Level(levelId, levelId <= actualScore.size(), levelScore != 0, series.getString(i), result.getInt(i, 0), resultString.getString(i), score.getInt(i, 0), levelScore));
        }
        id.recycle();
        series.recycle();
        result.recycle();
        resultString.recycle();

        return levels;
    }

    public static void showDialog(String msg, Context context) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        };
        showDialog(msg, context, onClickListener);
    }

    public static void showDialog(String msg, Context context, DialogInterface.OnClickListener onClickListener) {
        AlertDialog ad = new AlertDialog.Builder(context).create();
        ad.setCancelable(false); // This blocks the 'BACK' button
        ad.setMessage(Html.fromHtml(msg));
        ad.setButton("OK", onClickListener);
        ad.show();
    }
}
