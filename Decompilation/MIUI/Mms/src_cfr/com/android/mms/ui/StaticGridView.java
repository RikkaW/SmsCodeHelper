/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  java.lang.Math
 *  java.lang.Object
 */
package com.android.mms.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class StaticGridView
extends ViewGroup {
    private int mBottom;
    private int mChildHeight;
    private int mChildWidth;
    private int mColumnCount;
    private Initializer mInitializer;
    private boolean mIsInitialized;
    private int mLeft;
    private int mRight;
    private int mRowCount;
    private int mTop;

    public StaticGridView(Context context, int n, int n2, int n3, int n4) {
        super(context);
        this.set(n, n2, n3, n4);
        this.setDrawingCacheEnabled(true);
        this.setWillNotDraw(false);
    }

    public StaticGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void addView(View view, int n, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, n, layoutParams);
    }

    protected void doLayout(int n, int n2, int n3, int n4) {
        for (n = 0; n < this.getChildCount(); ++n) {
            this.layoutChildByIndex(n);
        }
    }

    protected void layoutChildByIndex(int n) {
        int n2 = n / this.mColumnCount;
        int n3 = n % this.mColumnCount;
        this.getChildAt(n).layout(this.mChildWidth * n3, this.mChildHeight * n2, this.mChildWidth * (n3 + 1), this.mChildHeight * (n2 + 1));
    }

    int measureDimension(int n, int n2) {
        int n3 = n2;
        switch (View.MeasureSpec.getMode((int)n)) {
            default: {
                n3 = 0;
            }
            case 0: {
                return n3;
            }
            case 1073741824: {
                return View.MeasureSpec.getSize((int)n);
            }
            case Integer.MIN_VALUE: 
        }
        return Math.min((int)n2, (int)View.MeasureSpec.getSize((int)n));
    }

    public void onDraw(Canvas canvas) {
        if (this.mIsInitialized || this.mInitializer == null) {
            return;
        }
        this.mIsInitialized = true;
        this.mInitializer.onInitialize(this);
        this.measureChildren(View.MeasureSpec.makeMeasureSpec((int)this.mChildWidth, (int)1073741824), View.MeasureSpec.makeMeasureSpec((int)this.mChildHeight, (int)1073741824));
        this.doLayout(this.mLeft, this.mTop, this.mRight, this.mBottom);
    }

    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        if (this.mIsInitialized || this.mInitializer == null) {
            this.doLayout(n, n2, n3, n4);
            return;
        }
        this.mLeft = n;
        this.mTop = n2;
        this.mRight = n3;
        this.mBottom = n4;
    }

    protected void onMeasure(int n, int n2) {
        this.setMeasuredDimension(this.measureDimension(n, this.mChildWidth * this.mColumnCount), this.measureDimension(n2, this.mChildHeight * this.mRowCount));
        this.measureChildren(View.MeasureSpec.makeMeasureSpec((int)this.mChildWidth, (int)1073741824), View.MeasureSpec.makeMeasureSpec((int)this.mChildHeight, (int)1073741824));
    }

    public void set(int n, int n2, int n3, int n4) {
        this.mRowCount = Math.max((int)1, (int)n);
        this.mColumnCount = Math.max((int)1, (int)n2);
        this.mChildHeight = Math.max((int)1, (int)n4);
        this.mChildWidth = Math.max((int)1, (int)n3);
    }

    public void setInitializer(Initializer initializer) {
        this.mInitializer = initializer;
    }

    public static interface Initializer {
        public void onInitialize(StaticGridView var1);
    }

}

