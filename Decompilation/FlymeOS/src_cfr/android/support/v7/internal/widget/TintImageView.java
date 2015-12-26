/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.widget.ImageView
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TintImageView
extends ImageView {
    private static final int[] TINT_ATTRS = new int[]{16842964, 16843033};
    private final TintManager mTintManager;

    public TintImageView(Context context) {
        this(context, null);
    }

    public TintImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TintImageView(Context object, AttributeSet attributeSet, int n2) {
        super((Context)object, attributeSet, n2);
        object = TintTypedArray.obtainStyledAttributes(this.getContext(), attributeSet, TINT_ATTRS, n2, 0);
        if (object.length() > 0) {
            if (object.hasValue(0)) {
                this.setBackgroundDrawable(object.getDrawable(0));
            }
            if (object.hasValue(1)) {
                this.setImageDrawable(object.getDrawable(1));
            }
        }
        object.recycle();
        this.mTintManager = object.getTintManager();
    }

    public void setImageResource(int n2) {
        this.setImageDrawable(this.mTintManager.getDrawable(n2));
    }
}

