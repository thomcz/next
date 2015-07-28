package com.example.thomas.next.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.thomas.next.object.Level;

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

    public static int getScore(Context context) {
        return get(context, SCORE);
    }

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
}
