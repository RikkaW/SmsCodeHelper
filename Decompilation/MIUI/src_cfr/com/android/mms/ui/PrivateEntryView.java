/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.Canvas
 *  android.graphics.Color
 *  android.graphics.Paint
 *  android.util.AttributeSet
 *  android.view.View
 *  java.lang.Object
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PrivateEntryView
extends View {
    private Bitmap mBitmap;
    private int mBlueColor;
    private OnPrivateColorChangeListener mColorChangeListener;
    private int mCurrPosition;
    private int mGreenColor;
    private int mRedColor;

    public PrivateEntryView(Context context) {
        super(context);
    }

    public PrivateEntryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PrivateEntryView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    protected void onDraw(Canvas canvas) {
        float f2;
        super.onDraw(canvas);
        canvas.save();
        if (this.mCurrPosition > 100) {
            int n = (int)((float)(this.mCurrPosition - 100) / (float)(this.getHeight() - 100) * 90.0f);
            canvas.drawARGB(n, this.mRedColor, this.mGreenColor, this.mBlueColor);
            if (this.mColorChangeListener != null) {
                this.mColorChangeListener.onColorChange(Color.argb((int)n, (int)this.mRedColor, (int)this.mGreenColor, (int)this.mBlueColor));
            }
        }
        if (this.mBitmap != null && (float)this.mCurrPosition > (f2 = (float)(this.getHeight() - this.mBitmap.getHeight()) * 0.32f)) {
            float f3 = this.getHeight();
            float f4 = this.mCurrPosition;
            canvas.drawBitmap(this.mBitmap, (float)((this.getWidth() - this.mBitmap.getWidth()) / 2), f3 - (f4 - f2), null);
        }
        canvas.restore();
    }

    public void onScroll(int n) {
        if (this.mCurrPosition != n) {
            this.mCurrPosition = n;
            this.invalidate();
            if (n == 0 && this.mColorChangeListener != null) {
                this.mColorChangeListener.onColorChange(0);
            }
        }
    }

    public void setBackgroundColor(int n) {
        this.mRedColor = Color.red((int)n);
        this.mGreenColor = Color.green((int)n);
        this.mBlueColor = Color.blue((int)n);
    }

    public void setBackgroundResId(int n) {
        this.mBitmap = BitmapFactory.decodeResource((Resources)this.getResources(), (int)n);
    }

    public void setOnColorChangeListener(OnPrivateColorChangeListener onPrivateColorChangeListener) {
        this.mColorChangeListener = onPrivateColorChangeListener;
    }

    static interface OnPrivateColorChangeListener {
        public void onColorChange(int var1);
    }

}

