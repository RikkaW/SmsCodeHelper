/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.widget.LinearLayout
 *  java.lang.Object
 */
package com.android.mms.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SizeAwareLinearLayout
extends LinearLayout {
    private boolean isInListener = false;
    private OnMeasureListener mOnMeasureListener = null;

    public SizeAwareLinearLayout(Context context) {
        super(context);
    }

    public SizeAwareLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        super.onLayout(bl, n, n2, n3, n4);
        if (!this.isInListener && this.mOnMeasureListener != null) {
            this.isInListener = true;
            this.mOnMeasureListener.onPostLayout();
            this.isInListener = false;
        }
    }

    protected void onMeasure(int n, int n2) {
        if (!this.isInListener && this.mOnMeasureListener != null) {
            this.isInListener = true;
            this.mOnMeasureListener.onPreMeasure(this, n, n2);
            this.isInListener = false;
        }
        super.onMeasure(n, n2);
    }

    public void setOnMeasureListener(OnMeasureListener onMeasureListener) {
        this.mOnMeasureListener = onMeasureListener;
    }

    static interface OnMeasureListener {
        public void onPostLayout();

        public void onPreMeasure(SizeAwareLinearLayout var1, int var2, int var3);
    }

}

