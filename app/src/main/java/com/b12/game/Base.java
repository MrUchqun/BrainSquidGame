package com.b12.game;

import android.content.Context;
import android.content.SharedPreferences;

public class Base {
    private static Base base = null;
    private Context context;

    private Base(Context context){
        this.context = context;
    }

    public static void init(Context context){
        base = new Base(context);
    }

    public static Base getInstance(){
        return base;
    }

    public void setStepCount(String key, int msg){
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, msg).apply();
    }
    public int getStepCount(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    public void setFinishedTime(String key, String msg){
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, msg).apply();
    }
    public String getFinishedTime(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "00:00");
    }

    public void setIsVolumeOn(String key, boolean msg){
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, msg).apply();
    }

}
