package com.example.thomas.next.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by thomas on 02.06.15.
 */
public class SharedPrefs {
    public static final String PREFS_NAME = "NEXT";
    public static final String LEVEL = "LEVEL";

    public void saveLevel(Context context, int level) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(LEVEL, level);
        editor.commit();
    }

    public int getLevel(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPref.getInt(LEVEL,0);
    }
}
