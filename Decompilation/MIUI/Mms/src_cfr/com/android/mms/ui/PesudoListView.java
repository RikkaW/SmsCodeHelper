/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 */
package com.android.mms.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class PesudoListView
extends ViewGroup {
    public PesudoListView(Context context) {
        super(context);
    }

    public PesudoListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PesudoListView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        for (n4 = 0; n4 < this.getChildCount(); ++n4) {
            View view = this.getChildAt(n4);
            int n5 = 0;
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                n5 = ((ViewGroup.MarginLayoutParams)layoutParams).topMargin;
            }
            view.layout(n, n2 + n5, n3, n2 + n5 + view.getMeasuredHeight());
        }
    }

    protected void onMeasure(int n, int n2) {
        this.measureChildren(n, 0);
        this.setMeasuredDimension(View.MeasureSpec.getSize((int)n), View.MeasureSpec.getSize((int)n2));
    }
}

