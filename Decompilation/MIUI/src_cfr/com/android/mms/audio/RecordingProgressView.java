/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.util.LinkedList
 */
package com.android.mms.audio;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.android.mms.R;
import java.util.LinkedList;

public class RecordingProgressView
extends View {
    private int mCount;
    private int mDefaultColor;
    private int mDrawingPositionX;
    private LinkedList<Integer> mElements = new LinkedList();
    private int mFilterCount = 10;
    private int mIndicatorColor;
    private int mIndicatorMaxHeight;
    private int mIndicatorNum;
    private int mIndicatorRadius;
    private int mIndicatorWidth;
    private int mInterval;
    private int mMaxIndicatorNum;

    public RecordingProgressView(Context context) {
        super(context);
    }

    public RecordingProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.initParams(context, attributeSet);
    }

    public RecordingProgressView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.initParams(context, attributeSet);
    }

    private void drawDots(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(this.mDefaultColor);
        paint.setStyle(Paint.Style.FILL);
        this.mDrawingPositionX = 0;
        int n = this.getHeight() / 2;
        int n2 = this.mIndicatorRadius;
        for (int i = 0; i < this.mIndicatorNum - this.mElements.size(); ++i) {
            canvas.drawCircle((float)(this.mDrawingPositionX + this.mIndicatorRadius), (float)(this.mIndicatorRadius + (n - n2)), (float)this.mIndicatorRadius, paint);
            this.mDrawingPositionX += this.mInterval;
        }
    }

    private void drawVolumes(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(this.mIndicatorColor);
        paint.setStyle(Paint.Style.FILL);
        int n = 0;
        for (Integer n2 : this.mElements) {
            if (this.mElements.size() - n > this.mIndicatorNum) {
                ++n;
                continue;
            }
            int n3 = n2;
            int n4 = (this.getHeight() - n3) / 2;
            canvas.drawCircle((float)(this.mDrawingPositionX + this.mIndicatorRadius), (float)n4, (float)this.mIndicatorRadius, paint);
            canvas.drawRect((float)this.mDrawingPositionX, (float)n4, (float)(this.mDrawingPositionX + this.mIndicatorWidth), (float)(n4 + n3), paint);
            canvas.drawCircle((float)(this.mDrawingPositionX + this.mIndicatorRadius), (float)(n4 + n3), (float)this.mIndicatorRadius, paint);
            this.mDrawingPositionX += this.mInterval;
        }
    }

    private void initParams(Context context, AttributeSet attributeSet) {
        int n;
        context = context.obtainStyledAttributes(attributeSet, R.styleable.RecordingProgressView);
        this.mInterval = context.getDimensionPixelSize(0, 25);
        this.mIndicatorWidth = context.getDimensionPixelSize(1, 4);
        this.mIndicatorRadius = this.mIndicatorWidth / 2;
        this.mIndicatorMaxHeight = context.getDimensionPixelSize(2, 80);
        this.mIndicatorNum = n = context.getInt(3, 30);
        this.mMaxIndicatorNum = n;
        this.mDefaultColor = context.getColor(4, -7829368);
        this.mIndicatorColor = context.getColor(5, -16776961);
        context.recycle();
    }

    private void update() {
        this.invalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    private int volumeToPix(double d2) {
        double d3;
        double d4 = d3 = d2 * 0.05;
        if (d3 > (double)(this.mIndicatorMaxHeight / 2)) {
            int n = this.mCount;
            this.mCount = n + 1;
            d4 = n < this.mFilterCount ? (double)(this.mIndicatorMaxHeight / 2) : (double)(this.mIndicatorMaxHeight / 2) + Math.sqrt((double)(d2 - (double)(this.mIndicatorMaxHeight / 2) / 0.05)) * 0.05 * 5.0;
        }
        d2 = d4;
        if (d4 > (double)this.mIndicatorMaxHeight) {
            d2 = this.mIndicatorMaxHeight;
        }
        return (int)d2;
    }

    public void addVolumes(double d2) {
        if (this.mElements.size() == this.mMaxIndicatorNum) {
            this.mElements.removeFirst();
        }
        this.mElements.add((Object)this.volumeToPix(d2));
        this.update();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.drawDots(canvas);
        this.drawVolumes(canvas);
    }

    protected void onMeasure(int n, int n2) {
        this.setMeasuredDimension(this.mInterval * (this.mIndicatorNum - 1) + this.mIndicatorWidth, RecordingProgressView.getDefaultSize((int)this.getSuggestedMinimumHeight(), (int)n2));
    }

    public void reset() {
        this.mElements.clear();
        this.update();
        this.mCount = 0;
        this.setWidth(this.mInterval * (this.mMaxIndicatorNum - 1) + this.mIndicatorWidth);
    }

    public void setWidth(int n) {
        int n2 = (n - this.mIndicatorWidth) / this.mInterval + 1;
        if (this.mIndicatorNum != n2) {
            this.mIndicatorNum = n2;
            ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
            layoutParams.width = n;
            this.setLayoutParams(layoutParams);
        }
    }
}

