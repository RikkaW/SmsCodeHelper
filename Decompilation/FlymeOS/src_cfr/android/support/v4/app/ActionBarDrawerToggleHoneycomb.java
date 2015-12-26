/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.ActionBar
 *  android.app.Activity
 *  android.content.res.TypedArray
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.Log
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewParent
 *  android.widget.ImageView
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 */
package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import java.lang.reflect.Method;

class ActionBarDrawerToggleHoneycomb {
    private static final String TAG = "ActionBarDrawerToggleHoneycomb";
    private static final int[] THEME_ATTRS = new int[]{16843531};

    ActionBarDrawerToggleHoneycomb() {
    }

    public static Drawable getThemeUpIndicator(Activity activity) {
        activity = activity.obtainStyledAttributes(THEME_ATTRS);
        Drawable drawable2 = activity.getDrawable(0);
        activity.recycle();
        return drawable2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Object setActionBarDescription(Object object, Activity activity, int n2) {
        if (object == null) {
            object = new SetIndicatorInfo(activity);
        }
        SetIndicatorInfo setIndicatorInfo = (SetIndicatorInfo)object;
        if (setIndicatorInfo.setHomeAsUpIndicator == null) return object;
        try {
            activity = activity.getActionBar();
            setIndicatorInfo.setHomeActionContentDescription.invoke((Object)activity, new Object[]{n2});
            if (Build.VERSION.SDK_INT > 19) return object;
            activity.setSubtitle(activity.getSubtitle());
        }
        catch (Exception exception) {
            Log.w((String)"ActionBarDrawerToggleHoneycomb", (String)"Couldn't set content description via JB-MR2 API", (Throwable)exception);
            return object;
        }
        return object;
    }

    public static Object setActionBarUpIndicator(Object object, Activity activity, Drawable drawable2, int n2) {
        if (object == null) {
            object = new SetIndicatorInfo(activity);
        }
        SetIndicatorInfo setIndicatorInfo = (SetIndicatorInfo)object;
        if (setIndicatorInfo.setHomeAsUpIndicator != null) {
            try {
                activity = activity.getActionBar();
                setIndicatorInfo.setHomeAsUpIndicator.invoke((Object)activity, new Object[]{drawable2});
                setIndicatorInfo.setHomeActionContentDescription.invoke((Object)activity, new Object[]{n2});
                return object;
            }
            catch (Exception var1_2) {
                Log.w((String)"ActionBarDrawerToggleHoneycomb", (String)"Couldn't set home-as-up indicator via JB-MR2 API", (Throwable)var1_2);
                return object;
            }
        }
        if (setIndicatorInfo.upIndicatorView != null) {
            setIndicatorInfo.upIndicatorView.setImageDrawable(drawable2);
            return object;
        }
        Log.w((String)"ActionBarDrawerToggleHoneycomb", (String)"Couldn't set home-as-up indicator");
        return object;
    }

    static class SetIndicatorInfo {
        public Method setHomeActionContentDescription;
        public Method setHomeAsUpIndicator;
        public ImageView upIndicatorView;

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        SetIndicatorInfo(Activity activity) {
            try {
                this.setHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", new Class[]{Drawable.class});
                this.setHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", new Class[]{Integer.TYPE});
                return;
            }
            catch (NoSuchMethodException var2_2) {
                ViewGroup viewGroup;
                if ((activity = activity.findViewById(16908332)) == null || (viewGroup = (ViewGroup)activity.getParent()).getChildCount() != 2) return;
                activity = viewGroup.getChildAt(0);
                viewGroup = viewGroup.getChildAt(1);
                if (activity.getId() == 16908332) {
                    activity = viewGroup;
                }
                if (!(activity instanceof ImageView)) return;
                this.upIndicatorView = (ImageView)activity;
                return;
            }
        }
    }

}

