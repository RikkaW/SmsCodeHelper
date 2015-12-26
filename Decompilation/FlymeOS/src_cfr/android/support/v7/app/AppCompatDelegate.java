/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.util.AttributeSet
 *  android.view.MenuInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.Window
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegateImplV11;
import android.support.v7.app.AppCompatDelegateImplV14;
import android.support.v7.app.AppCompatDelegateImplV7;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public abstract class AppCompatDelegate {
    static final String TAG = "AppCompatDelegate";

    AppCompatDelegate() {
    }

    public static AppCompatDelegate create(Activity activity, AppCompatCallback appCompatCallback) {
        return AppCompatDelegate.create((Context)activity, activity.getWindow(), appCompatCallback);
    }

    public static AppCompatDelegate create(Dialog dialog, AppCompatCallback appCompatCallback) {
        return AppCompatDelegate.create(dialog.getContext(), dialog.getWindow(), appCompatCallback);
    }

    private static AppCompatDelegate create(Context context, Window window, AppCompatCallback appCompatCallback) {
        int n2 = Build.VERSION.SDK_INT;
        if (n2 >= 14) {
            return new AppCompatDelegateImplV14(context, window, appCompatCallback);
        }
        if (n2 >= 11) {
            return new AppCompatDelegateImplV11(context, window, appCompatCallback);
        }
        return new AppCompatDelegateImplV7(context, window, appCompatCallback);
    }

    public abstract void addContentView(View var1, ViewGroup.LayoutParams var2);

    public abstract View createView(View var1, String var2, @NonNull Context var3, @NonNull AttributeSet var4);

    public abstract ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();

    public abstract MenuInflater getMenuInflater();

    public abstract ActionBar getSupportActionBar();

    public abstract void installViewFactory();

    public abstract void invalidateOptionsMenu();

    public abstract boolean isHandleNativeActionModesEnabled();

    public abstract void onConfigurationChanged(Configuration var1);

    public abstract void onCreate(Bundle var1);

    public abstract void onDestroy();

    public abstract void onPostCreate(Bundle var1);

    public abstract void onPostResume();

    public abstract void onStop();

    public abstract boolean requestWindowFeature(int var1);

    public abstract void setContentView(int var1);

    public abstract void setContentView(View var1);

    public abstract void setContentView(View var1, ViewGroup.LayoutParams var2);

    public abstract void setHandleNativeActionModesEnabled(boolean var1);

    public abstract void setSupportActionBar(Toolbar var1);

    public abstract void setTitle(CharSequence var1);

    public abstract void setUiOptions(int var1);

    public abstract ActionMode startMultiChoiceActionMode(ActionMode.Callback var1);

    public abstract ActionMode startSupportActionMode(ActionMode.Callback var1);
}

