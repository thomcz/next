package com.org.thomcz.next.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.org.thomcz.next.object.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by thomas on 02.06.15.
 */
public class SharedPrefs {
    private static final String PREFS_NAME = "NEXT";
    private static final String LEVEL = "LEVEL";
    private static final String SCORE = "SCORE";

    public static void saveLevel(Context context, int level) {
        save(context, LEVEL, level);
    }

    public static int getLevel(Context context) {
        return get(context, LEVEL);
    }

    public static void saveScore(Context context, int score) {
        save(context, SCORE, score);
    }

    /*public static int getScore(Context context) {
        return get(context, SCORE);
    }
*/
    private static void save(Context context, String key, int value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    private static int get(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, 0);
    }

    public static void saveScore(Context context, ArrayList<Integer> score) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String json = gson.toJson(score);

        editor.putString(SCORE, json);

        editor.commit();

    }

    public static void addScore(Context context, Level level) {
        ArrayList<Integer> scoreList = getScore(context);
        if (scoreList == null)
            scoreList = new ArrayList<Integer>();

        scoreList.add(level.getScore());
        saveScore(context, scoreList);
    }

    public static ArrayList<Integer> getScore(Context context) {
        SharedPreferences settings;
        List<Integer> score;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(SCORE)) {
            String jsonFavorites = settings.getString(SCORE, null);
            Gson gson = new Gson();
            Integer[] scoreItems = gson.fromJson(jsonFavorites,
                    Integer[].class);

            score = Arrays.asList(scoreItems);
            score = new ArrayList<Integer>(score);
        } else {
            saveScore(context, new ArrayList<Integer>());
            return getScore(context);
        }

        return (ArrayList<Integer>) score;
    }

}
