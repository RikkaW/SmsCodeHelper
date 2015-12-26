/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.View
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Constructor
 */
package android.support.v7.internal.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Constructor;
import java.util.Map;

public class AppCompatViewInflater {
    private static final String LOG_TAG = "AppCompatViewInflater";
    private static final Map<String, Constructor<? extends View>> sConstructorMap;
    static final Class<?>[] sConstructorSignature;
    private final Object[] mConstructorArgs = new Object[2];

    static {
        sConstructorSignature = new Class[]{Context.class, AttributeSet.class};
        sConstructorMap = new ArrayMap<String, Constructor<? extends View>>();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private View createView(Context var1_1, String var2_3, String var3_4) {
        var4_6 = var5_5 = AppCompatViewInflater.sConstructorMap.get(var2_3);
        if (var5_5 != null) ** GOTO lbl8
        try {
            var4_6 = var1_1.getClassLoader();
            var1_1 = var3_4 != null ? var3_4 + var2_3 : var2_3;
            var4_6 = var4_6.loadClass((String)var1_1).asSubclass((Class)View.class).getConstructor(AppCompatViewInflater.sConstructorSignature);
            AppCompatViewInflater.sConstructorMap.put(var2_3, (Constructor<? extends View>)var4_6);
lbl8: // 2 sources:
            var4_6.setAccessible(true);
            return (View)var4_6.newInstance(this.mConstructorArgs);
        }
        catch (Exception var1_2) {
            return null;
        }
    }

    private View createViewFromTag(Context context, String string2, AttributeSet attributeSet) {
        String string3 = string2;
        if (string2.equals((Object)"view")) {
            string3 = attributeSet.getAttributeValue(null, "class");
        }
        try {
            this.mConstructorArgs[0] = context;
            this.mConstructorArgs[1] = attributeSet;
            if (-1 == string3.indexOf(46)) {
                context = this.createView(context, string3, "android.widget.");
                return context;
            }
            context = this.createView(context, string3, null);
            return context;
        }
        catch (Exception var1_2) {
            return null;
        }
        finally {
            this.mConstructorArgs[0] = null;
            this.mConstructorArgs[1] = null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private static Context themifyContext(Context context, AttributeSet object, boolean bl2, boolean bl3) {
        object = context.obtainStyledAttributes((AttributeSet)object, R.styleable.View, 0, 0);
        int n2 = bl2 ? object.getResourceId(R.styleable.View_android_theme, 0) : 0;
        int n3 = n2;
        if (bl3) {
            n3 = n2;
            if (n2 == 0) {
                n3 = n2 = object.getResourceId(R.styleable.View_theme, 0);
                if (n2 != 0) {
                    Log.i((String)"AppCompatViewInflater", (String)"app:theme is now deprecated. Please move to using android:theme instead.");
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

    /*
     * Enabled aggressive block sorting
     */
    public final View createView(View view, String string2, @NonNull Context context, @NonNull AttributeSet attributeSet, boolean bl2, boolean bl3, boolean bl4) {
        View view2;
        block3 : {
            view = bl2 && view != null ? view.getContext() : context;
            if (!bl3) {
                view2 = view;
                if (!bl4) break block3;
            }
            view2 = AppCompatViewInflater.themifyContext((Context)view, attributeSet, bl3, bl4);
        }
        if (context != view2) {
            return this.createViewFromTag((Context)view2, string2, attributeSet);
        }
        return null;
    }
}

