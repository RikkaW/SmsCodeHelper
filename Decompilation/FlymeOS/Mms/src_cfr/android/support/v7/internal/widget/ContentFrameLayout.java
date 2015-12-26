/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Rect
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.TypedValue
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.widget.FrameLayout
 *  java.lang.Math
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;

public class ContentFrameLayout
extends FrameLayout {
    private final Rect mDecorPadding = new Rect();
    private TypedValue mFixedHeightMajor;
    private TypedValue mFixedHeightMinor;
    private TypedValue mFixedWidthMajor;
    private TypedValue mFixedWidthMinor;
    private TypedValue mMinWidthMajor;
    private TypedValue mMinWidthMinor;

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
    }

    public void dispatchFitSystemWindows(Rect rect) {
        this.fitSystemWindows(rect);
    }

    public TypedValue getFixedHeightMajor() {
        if (this.mFixedHeightMajor == null) {
            this.mFixedHeightMajor = new TypedValue();
        }
        return this.mFixedHeightMajor;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.mFixedHeightMinor == null) {
            this.mFixedHeightMinor = new TypedValue();
        }
        return this.mFixedHeightMinor;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.mFixedWidthMajor == null) {
            this.mFixedWidthMajor = new TypedValue();
        }
        return this.mFixedWidthMajor;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.mFixedWidthMinor == null) {
            this.mFixedWidthMinor = new TypedValue();
        }
        return this.mFixedWidthMinor;
    }

    public TypedValue getMinWidthMajor() {
        if (this.mMinWidthMajor == null) {
            this.mMinWidthMajor = new TypedValue();
        }
        return this.mMinWidthMajor;
    }

    public TypedValue getMinWidthMinor() {
        if (this.mMinWidthMinor == null) {
            this.mMinWidthMinor = new TypedValue();
        }
        return this.mMinWidthMinor;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onMeasure(int var1_1, int var2_2) {
        var7_3 = 0;
        var11_4 = this.getContext().getResources().getDisplayMetrics();
        var4_5 = var11_4.widthPixels < var11_4.heightPixels;
        var8_6 = View.MeasureSpec.getMode((int)var1_1);
        var9_7 = View.MeasureSpec.getMode((int)var2_2);
        if (var8_6 != Integer.MIN_VALUE) ** GOTO lbl-1000
        var10_8 = var4_5 != false ? this.mFixedWidthMinor : this.mFixedWidthMajor;
        if (var10_8 == null || var10_8.type == 0) ** GOTO lbl-1000
        var3_9 = var10_8.type == 5 ? (int)var10_8.getDimension(var11_4) : (var10_8.type == 6 ? (int)var10_8.getFraction((float)var11_4.widthPixels, (float)var11_4.widthPixels) : 0);
        if (var3_9 > 0) {
            var6_10 = View.MeasureSpec.makeMeasureSpec((int)Math.min((int)(var3_9 - (this.mDecorPadding.left + this.mDecorPadding.right)), (int)View.MeasureSpec.getSize((int)var1_1)), (int)1073741824);
            var5_11 = true;
        } else lbl-1000: // 3 sources:
        {
            var5_11 = false;
            var6_10 = var1_1;
        }
        var3_9 = var2_2;
        if (var9_7 == Integer.MIN_VALUE) {
            var10_8 = var4_5 != false ? this.mFixedHeightMajor : this.mFixedHeightMinor;
            var3_9 = var2_2;
            if (var10_8 != null) {
                var3_9 = var2_2;
                if (var10_8.type != 0) {
                    var1_1 = var10_8.type == 5 ? (int)var10_8.getDimension(var11_4) : (var10_8.type == 6 ? (int)var10_8.getFraction((float)var11_4.heightPixels, (float)var11_4.heightPixels) : 0);
                    var3_9 = var2_2;
                    if (var1_1 > 0) {
                        var3_9 = View.MeasureSpec.makeMeasureSpec((int)Math.min((int)(var1_1 - (this.mDecorPadding.top + this.mDecorPadding.bottom)), (int)View.MeasureSpec.getSize((int)var2_2)), (int)1073741824);
                    }
                }
            }
        }
        super.onMeasure(var6_10, var3_9);
        var9_7 = this.getMeasuredWidth();
        var6_10 = View.MeasureSpec.makeMeasureSpec((int)var9_7, (int)1073741824);
        if (var5_11 || var8_6 != Integer.MIN_VALUE) ** GOTO lbl-1000
        var10_8 = var4_5 != false ? this.mMinWidthMinor : this.mMinWidthMajor;
        if (var10_8 == null || var10_8.type == 0) ** GOTO lbl-1000
        var1_1 = var10_8.type == 5 ? (int)var10_8.getDimension(var11_4) : (var10_8.type == 6 ? (int)var10_8.getFraction((float)var11_4.widthPixels, (float)var11_4.widthPixels) : 0);
        var2_2 = var1_1;
        if (var1_1 > 0) {
            var2_2 = var1_1 - (this.mDecorPadding.left + this.mDecorPadding.right);
        }
        if (var9_7 < var2_2) {
            var1_1 = View.MeasureSpec.makeMeasureSpec((int)var2_2, (int)1073741824);
            var2_2 = 1;
        } else lbl-1000: // 3 sources:
        {
            var1_1 = var6_10;
            var2_2 = var7_3;
        }
        if (var2_2 == 0) return;
        super.onMeasure(var1_1, var3_9);
    }

    public void setDecorPadding(int n2, int n3, int n4, int n5) {
        this.mDecorPadding.set(n2, n3, n4, n5);
        if (ViewCompat.isLaidOut((View)this)) {
            this.requestLayout();
        }
    }
}

