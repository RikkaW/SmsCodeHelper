/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.text.method.TransformationMethod
 *  android.util.AttributeSet
 *  android.widget.TextView
 */
package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R;
import android.support.v7.internal.text.AllCapsTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

public class AppCompatTextView
extends TextView {
    public AppCompatTextView(Context context) {
        this(context, null);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, n2, 0);
        int n3 = typedArray.getResourceId(R.styleable.AppCompatTextView_android_textAppearance, -1);
        typedArray.recycle();
        if (n3 != -1) {
            typedArray = context.obtainStyledAttributes(n3, R.styleable.TextAppearance);
            if (typedArray.hasValue(R.styleable.TextAppearance_textAllCaps)) {
                this.setAllCaps(typedArray.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
            }
            typedArray.recycle();
        }
        if ((context = context.obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, n2, 0)).hasValue(R.styleable.AppCompatTextView_textAllCaps)) {
            this.setAllCaps(context.getBoolean(R.styleable.AppCompatTextView_textAllCaps, false));
        }
        context.recycle();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setAllCaps(boolean bl2) {
        AllCapsTransformationMethod allCapsTransformationMethod = bl2 ? new AllCapsTransformationMethod(this.getContext()) : null;
        this.setTransformationMethod((TransformationMethod)allCapsTransformationMethod);
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

