/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.AttributeSet
 *  android.widget.CheckBox
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class AppCompatCheckBox
extends CheckBox {
    private static final int[] TINT_ATTRS = new int[]{16843015};
    private Drawable mButtonDrawable;
    private TintManager mTintManager;

    public AppCompatCheckBox(Context context) {
        this(context, null);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkboxStyle);
    }

    public AppCompatCheckBox(Context object, AttributeSet attributeSet, int n2) {
        super((Context)object, attributeSet, n2);
        if (TintManager.SHOULD_BE_USED) {
            object = TintTypedArray.obtainStyledAttributes(this.getContext(), attributeSet, TINT_ATTRS, n2, 0);
            this.setButtonDrawable(object.getDrawable(0));
            object.recycle();
            this.mTintManager = object.getTintManager();
        }
    }

    public int getCompoundPaddingLeft() {
        int n2;
        int n3 = n2 = super.getCompoundPaddingLeft();
        if (Build.VERSION.SDK_INT < 17) {
            n3 = n2;
            if (this.mButtonDrawable != null) {
                n3 = n2 + this.mButtonDrawable.getIntrinsicWidth();
            }
        }
        return n3;
    }

    public void setButtonDrawable(int n2) {
        if (this.mTintManager != null) {
            this.setButtonDrawable(this.mTintManager.getDrawable(n2));
            return;
        }
        super.setButtonDrawable(n2);
    }

    public void setButtonDrawable(Drawable drawable2) {
        super.setButtonDrawable(drawable2);
        this.mButtonDrawable = drawable2;
    }
}

