package com.b12.game;

import android.content.Context;

public class Base {
    private static Base base = null;
    private Context context;

    private Base(Context context){
        this.context = context;
    }

    static void init(Context context){
        base = new Base(context);
    }

    static Base getInstance(){
        return base;
    }

    void setStepCount(String key, int msg){

    }
}
