/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.widget.CheckedTextView
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

public class AppCompatCheckedTextView
extends CheckedTextView {
    private static final int[] TINT_ATTRS = new int[]{16843016};
    private TintManager mTintManager;

    public AppCompatCheckedTextView(Context context) {
        this(context, null);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843720);
    }

    public AppCompatCheckedTextView(Context object, AttributeSet attributeSet, int n2) {
        super((Context)object, attributeSet, n2);
        if (TintManager.SHOULD_BE_USED) {
            object = TintTypedArray.obtainStyledAttributes(this.getContext(), attributeSet, TINT_ATTRS, n2, 0);
            this.setCheckMarkDrawable(object.getDrawable(0));
            object.recycle();
            this.mTintManager = object.getTintManager();
        }
    }

    public void setCheckMarkDrawable(int n2) {
        if (this.mTintManager != null) {
            this.setCheckMarkDrawable(this.mTintManager.getDrawable(n2));
            return;
        }
        super.setCheckMarkDrawable(n2);
    }
}

