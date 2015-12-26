/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Paint
 *  android.graphics.Paint$FontMetricsInt
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.text.style.DynamicDrawableSpan
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.widget.FrameLayout
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;
import android.view.View;
import android.widget.FrameLayout;

public class WidgetDrawableSpan
extends DynamicDrawableSpan {
    private static final int MESEASURE_SPEC = View.MeasureSpec.makeMeasureSpec((int)0, (int)0);
    private final Context mContext;
    private final FrameLayout mFrame;
    private final Resources mResource;

    public WidgetDrawableSpan(Context context, View view) {
        this.mContext = context;
        this.mResource = this.mContext.getResources();
        this.mFrame = new FrameLayout(context);
        this.mFrame.addView(view);
        this.mFrame.setDrawingCacheEnabled(true);
    }

    public Drawable getDrawable() {
        this.mFrame.measure(MESEASURE_SPEC, MESEASURE_SPEC);
        this.mFrame.layout(0, 0, this.mFrame.getMeasuredWidth(), this.mFrame.getMeasuredHeight());
        Bitmap bitmap = this.mFrame.getDrawingCache();
        bitmap = new BitmapDrawable(this.mResource, bitmap);
        bitmap.setBounds(0, 0, bitmap.getIntrinsicWidth(), bitmap.getIntrinsicHeight());
        return bitmap;
    }

    public int getSize(Paint paint, CharSequence charSequence, int n, int n2, Paint.FontMetricsInt fontMetricsInt) {
        n = super.getSize(paint, charSequence, n, n2, fontMetricsInt);
        if (fontMetricsInt != null) {
            n2 = fontMetricsInt.bottom - fontMetricsInt.ascent;
            fontMetricsInt.ascent = - (int)((double)n2 / 3.0 * 2.0);
            fontMetricsInt.descent = (int)((double)n2 / 3.0 * 1.0);
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = fontMetricsInt.descent;
        }
        return n;
    }
}

