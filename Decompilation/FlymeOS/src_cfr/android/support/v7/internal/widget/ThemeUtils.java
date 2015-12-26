/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.ColorStateList
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.content.res.TypedArray
 *  android.graphics.Color
 *  android.util.AttributeSet
 *  android.util.TypedValue
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.ThreadLocal
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

public class ThemeUtils {
    static final int[] ACTIVATED_STATE_SET;
    static final int[] CHECKED_STATE_SET;
    static final int[] DISABLED_STATE_SET;
    static final int[] EMPTY_STATE_SET;
    static final int[] FOCUSED_STATE_SET;
    static final int[] NOT_PRESSED_OR_FOCUSED_STATE_SET;
    static final int[] PRESSED_STATE_SET;
    static final int[] SELECTED_STATE_SET;
    private static final int[] TEMP_ARRAY;
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE;

    static {
        TL_TYPED_VALUE = new ThreadLocal();
        DISABLED_STATE_SET = new int[]{-16842910};
        FOCUSED_STATE_SET = new int[]{16842908};
        ACTIVATED_STATE_SET = new int[]{16843518};
        PRESSED_STATE_SET = new int[]{16842919};
        CHECKED_STATE_SET = new int[]{16842912};
        SELECTED_STATE_SET = new int[]{16842913};
        NOT_PRESSED_OR_FOCUSED_STATE_SET = new int[]{-16842919, -16842908};
        EMPTY_STATE_SET = new int[0];
        TEMP_ARRAY = new int[1];
    }

    public static ColorStateList createDisabledStateList(int n2, int n3) {
        return new ColorStateList((int[][])new int[][]{DISABLED_STATE_SET, EMPTY_STATE_SET}, new int[]{n3, n2});
    }

    public static int getDisabledThemeAttrColor(Context context, int n2) {
        ColorStateList colorStateList = ThemeUtils.getThemeAttrColorStateList(context, n2);
        if (colorStateList != null && colorStateList.isStateful()) {
            return colorStateList.getColorForState(DISABLED_STATE_SET, colorStateList.getDefaultColor());
        }
        colorStateList = ThemeUtils.getTypedValue();
        context.getTheme().resolveAttribute(16842803, (TypedValue)colorStateList, true);
        return ThemeUtils.getThemeAttrColor(context, n2, colorStateList.getFloat());
    }

    public static int getThemeAttrColor(Context context, int n2) {
        ThemeUtils.TEMP_ARRAY[0] = n2;
        context = context.obtainStyledAttributes(null, TEMP_ARRAY);
        try {
            n2 = context.getColor(0, 0);
            return n2;
        }
        finally {
            context.recycle();
        }
    }

    static int getThemeAttrColor(Context context, int n2, float f2) {
        n2 = ThemeUtils.getThemeAttrColor(context, n2);
        return ColorUtils.setAlphaComponent(n2, Math.round((float)((float)Color.alpha((int)n2) * f2)));
    }

    public static ColorStateList getThemeAttrColorStateList(Context context, int n2) {
        ThemeUtils.TEMP_ARRAY[0] = n2;
        context = context.obtainStyledAttributes(null, TEMP_ARRAY);
        try {
            ColorStateList colorStateList = context.getColorStateList(0);
            return colorStateList;
        }
        finally {
            context.recycle();
        }
    }

    private static TypedValue getTypedValue() {
        TypedValue typedValue;
        TypedValue typedValue2 = typedValue = (TypedValue)TL_TYPED_VALUE.get();
        if (typedValue == null) {
            typedValue2 = new TypedValue();
            TL_TYPED_VALUE.set((Object)typedValue2);
        }
        return typedValue2;
    }
}

