/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.ColorStateList
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.view.View
 *  android.view.View$OnApplyWindowInsetsListener
 *  android.view.WindowInsets
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompatLollipop$1;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.view.WindowInsetsCompatApi21;
import android.view.View;
import android.view.WindowInsets;

class ViewCompatLollipop {
    ViewCompatLollipop() {
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2 = windowInsetsCompat;
        if (windowInsetsCompat instanceof WindowInsetsCompatApi21) {
            WindowInsets windowInsets = ((WindowInsetsCompatApi21)windowInsetsCompat).unwrap();
            view = view.dispatchApplyWindowInsets(windowInsets);
            windowInsetsCompat2 = windowInsetsCompat;
            if (view != windowInsets) {
                windowInsetsCompat2 = new WindowInsetsCompatApi21((WindowInsets)view);
            }
        }
        return windowInsetsCompat2;
    }

    public static boolean dispatchNestedFling(View view, float f2, float f3, boolean bl2) {
        return view.dispatchNestedFling(f2, f3, bl2);
    }

    public static boolean dispatchNestedPreFling(View view, float f2, float f3) {
        return view.dispatchNestedPreFling(f2, f3);
    }

    public static boolean dispatchNestedPreScroll(View view, int n2, int n3, int[] arrn, int[] arrn2) {
        return view.dispatchNestedPreScroll(n2, n3, arrn, arrn2);
    }

    public static boolean dispatchNestedScroll(View view, int n2, int n3, int n4, int n5, int[] arrn) {
        return view.dispatchNestedScroll(n2, n3, n4, n5, arrn);
    }

    static ColorStateList getBackgroundTintList(View view) {
        return view.getBackgroundTintList();
    }

    static PorterDuff.Mode getBackgroundTintMode(View view) {
        return view.getBackgroundTintMode();
    }

    public static float getElevation(View view) {
        return view.getElevation();
    }

    public static String getTransitionName(View view) {
        return view.getTransitionName();
    }

    public static float getTranslationZ(View view) {
        return view.getTranslationZ();
    }

    public static float getZ(View view) {
        return view.getZ();
    }

    public static boolean hasNestedScrollingParent(View view) {
        return view.hasNestedScrollingParent();
    }

    public static boolean isImportantForAccessibility(View view) {
        return view.isImportantForAccessibility();
    }

    public static boolean isNestedScrollingEnabled(View view) {
        return view.isNestedScrollingEnabled();
    }

    public static WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2 = windowInsetsCompat;
        if (windowInsetsCompat instanceof WindowInsetsCompatApi21) {
            WindowInsets windowInsets = ((WindowInsetsCompatApi21)windowInsetsCompat).unwrap();
            view = view.onApplyWindowInsets(windowInsets);
            windowInsetsCompat2 = windowInsetsCompat;
            if (view != windowInsets) {
                windowInsetsCompat2 = new WindowInsetsCompatApi21((WindowInsets)view);
            }
        }
        return windowInsetsCompat2;
    }

    public static void requestApplyInsets(View view) {
        view.requestApplyInsets();
    }

    static void setBackgroundTintList(View view, ColorStateList colorStateList) {
        view.setBackgroundTintList(colorStateList);
    }

    static void setBackgroundTintMode(View view, PorterDuff.Mode mode) {
        view.setBackgroundTintMode(mode);
    }

    public static void setElevation(View view, float f2) {
        view.setElevation(f2);
    }

    public static void setNestedScrollingEnabled(View view, boolean bl2) {
        view.setNestedScrollingEnabled(bl2);
    }

    public static void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener)new ViewCompatLollipop$1(onApplyWindowInsetsListener));
    }

    public static void setTransitionName(View view, String string2) {
        view.setTransitionName(string2);
    }

    public static void setTranslationZ(View view, float f2) {
        view.setTranslationZ(f2);
    }

    public static boolean startNestedScroll(View view, int n2) {
        return view.startNestedScroll(n2);
    }

    public static void stopNestedScroll(View view) {
        view.stopNestedScroll();
    }
}

