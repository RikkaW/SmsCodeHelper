/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.ColorStateList
 *  android.content.res.TypedArray
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.text.method.TransformationMethod
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.accessibility.AccessibilityEvent
 *  android.view.accessibility.AccessibilityNodeInfo
 *  android.widget.Button
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.appcompat.R;
import android.support.v7.internal.text.AllCapsTransformationMethod;
import android.support.v7.internal.widget.ThemeUtils;
import android.support.v7.internal.widget.TintInfo;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

public class AppCompatButton
extends Button
implements TintableBackgroundView {
    private static final int[] TINT_ATTRS = new int[]{16842964};
    private TintInfo mBackgroundTint;
    private TintInfo mInternalBackgroundTint;
    private TintManager mTintManager;

    public AppCompatButton(Context context) {
        this(context, null);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyle);
    }

    /*
     * Enabled aggressive block sorting
     */
    public AppCompatButton(Context context, AttributeSet attributeSet, int n2) {
        Object object;
        super(context, attributeSet, n2);
        if (TintManager.SHOULD_BE_USED) {
            ColorStateList colorStateList;
            object = TintTypedArray.obtainStyledAttributes(this.getContext(), attributeSet, TINT_ATTRS, n2, 0);
            if (object.hasValue(0) && (colorStateList = object.getTintManager().getTintList(object.getResourceId(0, -1))) != null) {
                this.setInternalBackgroundTint(colorStateList);
            }
            this.mTintManager = object.getTintManager();
            object.recycle();
        }
        object = context.obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, n2, 0);
        int n3 = object.getResourceId(R.styleable.AppCompatTextView_android_textAppearance, -1);
        object.recycle();
        if (n3 != -1) {
            object = context.obtainStyledAttributes(n3, R.styleable.TextAppearance);
            if (object.hasValue(R.styleable.TextAppearance_textAllCaps)) {
                this.setAllCaps(object.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
            }
            object.recycle();
        }
        if ((attributeSet = context.obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, n2, 0)).hasValue(R.styleable.AppCompatTextView_textAllCaps)) {
            this.setAllCaps(attributeSet.getBoolean(R.styleable.AppCompatTextView_textAllCaps, false));
        }
        attributeSet.recycle();
        attributeSet = this.getTextColors();
        if (attributeSet != null && !attributeSet.isStateful()) {
            n2 = Build.VERSION.SDK_INT < 21 ? ThemeUtils.getDisabledThemeAttrColor(context, 16842808) : ThemeUtils.getThemeAttrColor(context, 16842808);
            this.setTextColor(ThemeUtils.createDisabledStateList(attributeSet.getDefaultColor(), n2));
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

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName((CharSequence)Button.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName((CharSequence)Button.class.getName());
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setAllCaps(boolean bl2) {
        AllCapsTransformationMethod allCapsTransformationMethod = bl2 ? new AllCapsTransformationMethod(this.getContext()) : null;
        this.setTransformationMethod((TransformationMethod)allCapsTransformationMethod);
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

    public void setTextAppearance(Context context, int n2) {
        super.setTextAppearance(context, n2);
        context = context.obtainStyledAttributes(n2, R.styleable.TextAppearance);
        if (context.hasValue(R.styleable.TextAppearance_textAllCaps)) {
            this.setAllCaps(context.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
        }
        context.recycle();
    }
}

