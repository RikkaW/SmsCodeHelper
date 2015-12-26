/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextPaint
 *  android.text.style.TextAppearanceSpan
 */
package com.android.mms.ui;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.TextAppearanceSpan;

public class TextSizeAdjustSpan
extends TextAppearanceSpan {
    private boolean mNeedUnderline;

    public TextSizeAdjustSpan(Context context, int n) {
        super(context, n);
    }

    public void needUnderline() {
        this.mNeedUnderline = true;
    }

    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        if (this.mNeedUnderline) {
            textPaint.setUnderlineText(true);
        }
    }

    public void updateMeasureState(TextPaint textPaint) {
        float f2 = textPaint.getTextSize();
        super.updateMeasureState(textPaint);
        if (f2 > 0.0f && this.getTextSize() > 0) {
            textPaint.setTextSize(f2);
        }
    }
}

