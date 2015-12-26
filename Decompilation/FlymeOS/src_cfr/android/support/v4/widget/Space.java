/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  java.lang.Math
 */
package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class Space
extends View {
    public Space(Context context) {
        this(context, null);
    }

    public Space(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Space(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
        if (this.getVisibility() == 0) {
            this.setVisibility(4);
        }
    }

    private static int getDefaultSize2(int n2, int n3) {
        int n4 = View.MeasureSpec.getMode((int)n3);
        n3 = View.MeasureSpec.getSize((int)n3);
        switch (n4) {
            default: {
                return n2;
            }
            case Integer.MIN_VALUE: {
                return Math.min((int)n2, (int)n3);
            }
            case 1073741824: 
        }
        return n3;
    }

    public void draw(Canvas canvas) {
    }

    protected void onMeasure(int n2, int n3) {
        this.setMeasuredDimension(Space.getDefaultSize2(this.getSuggestedMinimumWidth(), n2), Space.getDefaultSize2(this.getSuggestedMinimumHeight(), n3));
    }
}

