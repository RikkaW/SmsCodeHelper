/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.drawable.Drawable
 *  android.view.View
 *  android.view.View$OnApplyWindowInsetsListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.WindowInsets
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayoutImpl;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;

class DrawerLayoutCompatApi21 {
    private static final int[] THEME_ATTRS = new int[]{16843828};

    DrawerLayoutCompatApi21() {
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void applyMarginInsets(ViewGroup.MarginLayoutParams marginLayoutParams, Object object, int n2) {
        WindowInsets windowInsets = (WindowInsets)object;
        if (n2 == 3) {
            object = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), 0, windowInsets.getSystemWindowInsetBottom());
        } else {
            object = windowInsets;
            if (n2 == 5) {
                object = windowInsets.replaceSystemWindowInsets(0, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
            }
        }
        marginLayoutParams.leftMargin = object.getSystemWindowInsetLeft();
        marginLayoutParams.topMargin = object.getSystemWindowInsetTop();
        marginLayoutParams.rightMargin = object.getSystemWindowInsetRight();
        marginLayoutParams.bottomMargin = object.getSystemWindowInsetBottom();
    }

    public static void configureApplyInsets(View view) {
        if (view instanceof DrawerLayoutImpl) {
            view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener)new InsetsListener());
            view.setSystemUiVisibility(1280);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void dispatchChildInsets(View view, Object object, int n2) {
        WindowInsets windowInsets = (WindowInsets)object;
        if (n2 == 3) {
            object = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), 0, windowInsets.getSystemWindowInsetBottom());
        } else {
            object = windowInsets;
            if (n2 == 5) {
                object = windowInsets.replaceSystemWindowInsets(0, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
            }
        }
        view.dispatchApplyWindowInsets((WindowInsets)object);
    }

    public static Drawable getDefaultStatusBarBackground(Context context) {
        context = context.obtainStyledAttributes(THEME_ATTRS);
        try {
            Drawable drawable2 = context.getDrawable(0);
            return drawable2;
        }
        finally {
            context.recycle();
        }
    }

    public static int getTopInset(Object object) {
        if (object != null) {
            return ((WindowInsets)object).getSystemWindowInsetTop();
        }
        return 0;
    }

    static class InsetsListener
    implements View.OnApplyWindowInsetsListener {
        InsetsListener() {
        }

        /*
         * Enabled aggressive block sorting
         */
        public WindowInsets onApplyWindowInsets(View object, WindowInsets windowInsets) {
            object = (DrawerLayoutImpl)object;
            boolean bl2 = windowInsets.getSystemWindowInsetTop() > 0;
            object.setChildInsets((Object)windowInsets, bl2);
            return windowInsets.consumeSystemWindowInsets();
        }
    }

}

