package com.example.practice8.DATA.DataSources.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesDS {
    private final String fileName = "PREFERENCE_FILE_KEY";
    private final Context context;
    private final SharedPreferences sharedPreferences;
    public SharedPreferencesDS(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }
    public void writeString(String key, String content) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!sharedPreferences.getString(key, "").equals("")) editor.remove(key);
        editor.putString(key, content);
        editor.apply();
    }
    public void writeInt(String key, int content) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.getInt(key, -1) != -1) editor.remove(key);
        editor.putInt(key, content);
        editor.apply();
    }
    public void writeBool(String key, boolean content) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean(key, false)) editor.remove(key);
        editor.putBoolean(key, content);
        editor.apply();
    }
    public int getInt(String key) {
        int defaultValue = -1;
        return sharedPreferences.getInt(key, defaultValue);
    }
    public String getString(String key) {
        String defaultValue = "";
        return sharedPreferences.getString(key, defaultValue);
    }
    public boolean getBool(String key) {
        boolean defaultValue = false;
        return sharedPreferences.getBoolean(key, defaultValue);
    }
}
