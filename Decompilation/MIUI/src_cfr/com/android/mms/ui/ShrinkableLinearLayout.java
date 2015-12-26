/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.LinearLayout
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.mms.R;

public class ShrinkableLinearLayout
extends LinearLayout {
    private int mShrinkableChildIndex = -1;

    public ShrinkableLinearLayout(Context context) {
        super(context);
    }

    public ShrinkableLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        context = context.obtainStyledAttributes(attributeSet, R.styleable.ShrinkableLinearLayout);
        this.mShrinkableChildIndex = context.getInt(0, -1);
        context.recycle();
    }

    protected void onMeasure(int n, int n2) {
        if (this.getOrientation() == 1) {
            this.shrinkIfNecessaryV(n, n2);
            super.onMeasure(n, n2);
            return;
        }
        this.shrinkIfNecessaryH(n, n2);
        super.onMeasure(n, n2);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    void shrinkIfNecessaryH(int n, int n2) {
        if (this.mShrinkableChildIndex == -1) return;
        if (this.getOrientation() == 1) {
            return;
        }
        if (View.MeasureSpec.getMode((int)n) == 0) return;
        View view = this.getChildAt(this.mShrinkableChildIndex);
        view.getLayoutParams().width = -2;
        super.onMeasure(0, n2);
        n2 = view.getMeasuredWidth();
        int n3 = this.getMeasuredWidth();
        if ((n = View.MeasureSpec.getSize((int)n)) >= n3) return;
        n = n2 -= n3 - n;
        if (n2 < 0) {
            n = 0;
        }
        view.getLayoutParams().width = n;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    void shrinkIfNecessaryV(int n, int n2) {
        if (this.mShrinkableChildIndex == -1) return;
        if (this.getOrientation() == 0) {
            return;
        }
        if (View.MeasureSpec.getMode((int)n2) == 0) return;
        View view = this.getChildAt(this.mShrinkableChildIndex);
        view.getLayoutParams().height = -2;
        super.onMeasure(n, 0);
        n = view.getMeasuredHeight();
        int n3 = this.getMeasuredHeight();
        if ((n2 = View.MeasureSpec.getSize((int)n2)) >= n3) return;
        n = n2 = n - (n3 - n2);
        if (n2 < 0) {
            n = 0;
        }
        view.getLayoutParams().height = n;
    }
}

