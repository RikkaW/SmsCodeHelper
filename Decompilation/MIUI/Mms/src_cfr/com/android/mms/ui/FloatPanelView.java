/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.View
 *  android.widget.LinearLayout
 *  java.lang.String
 */
package com.android.mms.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class FloatPanelView
extends LinearLayout {
    private int mPos;

    public FloatPanelView(Context context) {
        super(context);
    }

    public FloatPanelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        super.onLayout(bl, n, n2, n3, n4);
        View view = this.findViewById(2131820553);
        if (view == null) {
            Log.e((String)"FloatPanelView", (String)"counldn't find view");
            return;
        }
        n2 = this.mPos - view.getMeasuredWidth() / 2;
        if (n2 < 0) {
            n = 0;
        } else {
            n = n2;
            if (n2 > n3 - view.getMeasuredWidth()) {
                n = n3 - view.getMeasuredWidth();
            }
        }
        view.layout(n, view.getTop(), view.getMeasuredWidth() + n, view.getBottom());
    }

    public void setArrowPos(int n) {
        if (n != this.mPos) {
            this.mPos = n;
            this.requestLayout();
        }
    }
}

