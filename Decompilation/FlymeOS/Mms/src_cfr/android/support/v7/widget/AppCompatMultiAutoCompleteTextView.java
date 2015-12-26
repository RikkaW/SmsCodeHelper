/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.ColorStateList
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.View
 *  android.widget.MultiAutoCompleteTextView
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.TintContextWrapper;
import android.support.v7.internal.widget.TintInfo;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;

public class AppCompatMultiAutoCompleteTextView
extends MultiAutoCompleteTextView
implements TintableBackgroundView {
    private static final int[] TINT_ATTRS = new int[]{16842964, 16843126};
    private TintInfo mBackgroundTint;
    private TintInfo mInternalBackgroundTint;
    private TintManager mTintManager;

    public AppCompatMultiAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.autoCompleteTextViewStyle);
    }

    public AppCompatMultiAutoCompleteTextView(Context object, AttributeSet attributeSet, int n2) {
        super(TintContextWrapper.wrap((Context)object), attributeSet, n2);
        if (TintManager.SHOULD_BE_USED) {
            object = TintTypedArray.obtainStyledAttributes(this.getContext(), attributeSet, TINT_ATTRS, n2, 0);
            this.mTintManager = object.getTintManager();
            if (object.hasValue(0) && (attributeSet = object.getTintManager().getTintList(object.getResourceId(0, -1))) != null) {
                this.setInternalBackgroundTint((ColorStateList)attributeSet);
            }
            if (object.hasValue(1)) {
                this.setDropDownBackgroundDrawable(object.getDrawable(1));
            }
            object.recycle();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void applySupportBackgroundTint() {
        if (this.getBackground() == null) return;
        {
            if (this.mBackgroundTint != null) {
                TintManager.tintViewBackground((View)this, this.mBackgroundTint);
                return;
            } else {
                if (this.mInternalBackgroundTint == null) return;
                {
                    TintManager.tintViewBackground((View)this, this.mInternalBackgroundTint);
                    return;
                }
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setInternalBackgroundTint(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.mInternalBackgroundTint == null) {
                this.mInternalBackgroundTint = new TintInfo();
            }
            this.mInternalBackgroundTint.mTintList = colorStateList;
            this.mInternalBackgroundTint.mHasTintList = true;
        } else {
            this.mInternalBackgroundTint = null;
        }
        this.applySupportBackgroundTint();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.applySupportBackgroundTint();
    }

    @Nullable
    @Override
    public ColorStateList getSupportBackgroundTintList() {
        if (this.mBackgroundTint != null) {
            return this.mBackgroundTint.mTintList;
        }
        return null;
    }

    @Nullable
    @Override
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        if (this.mBackgroundTint != null) {
            return this.mBackgroundTint.mTintMode;
        }
        return null;
    }

    public void setBackgroundDrawable(Drawable drawable2) {
        super.setBackgroundDrawable(drawable2);
        this.setInternalBackgroundTint(null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setBackgroundResource(int n2) {
        super.setBackgroundResource(n2);
        ColorStateList colorStateList = this.mTintManager != null ? this.mTintManager.getTintList(n2) : null;
        this.setInternalBackgroundTint(colorStateList);
    }

    public void setDropDownBackgroundResource(int n2) {
        if (this.mTintManager != null) {
            this.setDropDownBackgroundDrawable(this.mTintManager.getDrawable(n2));
            return;
        }
        super.setDropDownBackgroundResource(n2);
    }

    @Override
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        this.mBackgroundTint.mTintList = colorStateList;
        this.mBackgroundTint.mHasTintList = true;
        this.applySupportBackgroundTint();
    }

    @Override
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        this.mBackgroundTint.mTintMode = mode;
        this.mBackgroundTint.mHasTintMode = true;
        this.applySupportBackgroundTint();
    }
}

