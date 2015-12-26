/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.Rect
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Handler
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.View
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.support.v7.internal.widget.ViewUtils$1;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewUtils {
    private static final String TAG = "ViewUtils";
    private static Method sComputeFitSystemWindowsMethod;
    private static Method sMakeOptionalFitsSystemWindowsMethod;

    private ViewUtils() {
    }

    static /* synthetic */ Method access$102(Method method) {
        sMakeOptionalFitsSystemWindowsMethod = method;
        return method;
    }

    public static int combineMeasuredStates(int n2, int n3) {
        return n2 | n3;
    }

    public static boolean computeFitSystemWindows(View view, Rect rect, Rect rect2) {
        if (sComputeFitSystemWindowsMethod != null) {
            try {
                boolean bl2 = (Boolean)sComputeFitSystemWindowsMethod.invoke((Object)view, new Object[]{rect, rect2});
                return bl2;
            }
            catch (Exception var0_1) {
                Log.d((String)"ViewUtils", (String)"Could not invoke computeFitSystemWindows", (Throwable)var0_1);
            }
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void getComputeFitSystemWindowsMethod() {
        if (Build.VERSION.SDK_INT < 18) return;
        try {
            sComputeFitSystemWindowsMethod = View.class.getDeclaredMethod("computeFitSystemWindows", new Class[]{Rect.class, Rect.class});
            if (sComputeFitSystemWindowsMethod.isAccessible()) return;
            sComputeFitSystemWindowsMethod.setAccessible(true);
            return;
        }
        catch (NoSuchMethodException var0) {
            Log.d((String)"ViewUtils", (String)"Could not find method computeFitSystemWindows. Oh well.");
            return;
        }
    }

    public static void init() {
        if (sComputeFitSystemWindowsMethod == null) {
            new InitThread(null).start();
        }
    }

    public static boolean isLayoutRtl(View view) {
        if (ViewCompat.getLayoutDirection(view) == 1) {
            return true;
        }
        return false;
    }

    public static void makeOptionalFitsSystemWindows(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                if (sMakeOptionalFitsSystemWindowsMethod == null) {
                    new Thread((Runnable)new ViewUtils$1(view, new Handler())).start();
                    return;
                }
                sMakeOptionalFitsSystemWindowsMethod.invoke((Object)view, new Object[0]);
                return;
            }
            catch (InvocationTargetException var0_1) {
                Log.d((String)"ViewUtils", (String)"Could not invoke makeOptionalFitsSystemWindows", (Throwable)var0_1);
                return;
            }
            catch (IllegalAccessException var0_2) {
                Log.d((String)"ViewUtils", (String)"Could not invoke makeOptionalFitsSystemWindows", (Throwable)var0_2);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Context themifyContext(Context context, AttributeSet object, boolean bl2, boolean bl3) {
        object = context.obtainStyledAttributes((AttributeSet)object, R.styleable.View, 0, 0);
        int n2 = bl2 ? object.getResourceId(R.styleable.View_android_theme, 0) : 0;
        int n3 = n2;
        if (bl3) {
            n3 = n2;
            if (n2 == 0) {
                n3 = n2 = object.getResourceId(R.styleable.View_theme, 0);
                if (n2 != 0) {
                    Log.i((String)"ViewUtils", (String)"app:theme is now deprecated. Please move to using android:theme instead.");
                    n3 = n2;
                }
            }
        }
        object.recycle();
        object = context;
        if (n3 == 0) return object;
        if (!(context instanceof ContextThemeWrapper)) return new ContextThemeWrapper(context, n3);
        object = context;
        if (((ContextThemeWrapper)context).getThemeResId() == n3) return object;
        return new ContextThemeWrapper(context, n3);
    }

    static class InitThread
    extends Thread {
        private InitThread() {
        }

        /* synthetic */ InitThread(ViewUtils$1 viewUtils$1) {
            this();
        }

        public void run() {
            ViewUtils.getComputeFitSystemWindowsMethod();
        }
    }

}

