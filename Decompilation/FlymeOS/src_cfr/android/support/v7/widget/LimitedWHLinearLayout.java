/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.widget.LinearLayout
 */
package android.support.v7.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class LimitedWHLinearLayout
extends LinearLayout {
    private int mMaxHeight = Integer.MAX_VALUE;
    private int mMaxWidth = Integer.MAX_VALUE;

    public LimitedWHLinearLayout(Context context) {
        super(context);
    }

    public LimitedWHLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    protected void onMeasure(int n2, int n3) {
        boolean bl2 = true;
        super.onMeasure(n2, n3);
        int n4 = this.getMeasuredHeight();
        int n5 = this.getMeasuredWidth();
        boolean bl3 = false;
        if (n4 > this.mMaxHeight) {
            n3 = View.MeasureSpec.makeMeasureSpec((int)this.mMaxHeight, (int)1073741824);
            bl3 = true;
        }
        if (n5 > this.mMaxWidth) {
            n2 = View.MeasureSpec.makeMeasureSpec((int)this.mMaxWidth, (int)1073741824);
            bl3 = bl2;
        }
        if (bl3) {
            super.onMeasure(n2, n3);
        }
    }

    public void setMaxHeight(int n2) {
        this.mMaxHeight = n2;
    }

    public void setMaxWidth(int n2) {
        this.mMaxWidth = n2;
    }
}

