package me.gitai.library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by dp on 15-8-22.
 */
public class SharedPreferencesUtil {
    private static Context ctx;

    public static void initialize(Context context){
        ctx = context;
    }

    public synchronized static SharedPreferences getInstence(String name){
        if (name !=null)
            return ctx.getSharedPreferences(name, 0);
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    public synchronized static SharedPreferences.Editor getEditor(String name){
        return ctx.getSharedPreferences(name, 0).edit();
    }

}
