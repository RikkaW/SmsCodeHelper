/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.Canvas
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;

public final class ViewStubCompat
extends View {
    private OnInflateListener mInflateListener;
    private int mInflatedId;
    private WeakReference<View> mInflatedViewRef;
    private LayoutInflater mInflater;
    private int mLayoutResource = 0;

    public ViewStubCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
        context = context.obtainStyledAttributes(attributeSet, R.styleable.ViewStubCompat, n2, 0);
        this.mInflatedId = context.getResourceId(R.styleable.ViewStubCompat_android_inflatedId, -1);
        this.mLayoutResource = context.getResourceId(R.styleable.ViewStubCompat_android_layout, 0);
        this.setId(context.getResourceId(R.styleable.ViewStubCompat_android_id, -1));
        context.recycle();
        this.setVisibility(8);
        this.setWillNotDraw(true);
    }

    protected void dispatchDraw(Canvas canvas) {
    }

    public void draw(Canvas canvas) {
    }

    public int getInflatedId() {
        return this.mInflatedId;
    }

    public LayoutInflater getLayoutInflater() {
        return this.mInflater;
    }

    public int getLayoutResource() {
        return this.mLayoutResource;
    }

    /*
     * Enabled aggressive block sorting
     */
    public View inflate() {
        ViewParent viewParent = this.getParent();
        if (viewParent != null && viewParent instanceof ViewGroup) {
            if (this.mLayoutResource == 0) {
                throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
            }
            ViewGroup viewGroup = (ViewGroup)viewParent;
            viewParent = this.mInflater != null ? this.mInflater : LayoutInflater.from((Context)this.getContext());
            viewParent = viewParent.inflate(this.mLayoutResource, viewGroup, false);
            if (this.mInflatedId != -1) {
                viewParent.setId(this.mInflatedId);
            }
            int n2 = viewGroup.indexOfChild((View)this);
            viewGroup.removeViewInLayout((View)this);
            ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
            if (layoutParams != null) {
                viewGroup.addView((View)viewParent, n2, layoutParams);
            } else {
                viewGroup.addView((View)viewParent, n2);
            }
            this.mInflatedViewRef = new WeakReference<ViewParent>(viewParent);
            if (this.mInflateListener != null) {
                this.mInflateListener.onInflate(this, (View)viewParent);
            }
            return viewParent;
        }
        throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
    }

    protected void onMeasure(int n2, int n3) {
        this.setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int n2) {
        this.mInflatedId = n2;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.mInflater = layoutInflater;
    }

    public void setLayoutResource(int n2) {
        this.mLayoutResource = n2;
    }

    public void setOnInflateListener(OnInflateListener onInflateListener) {
        this.mInflateListener = onInflateListener;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setVisibility(int n2) {
        if (this.mInflatedViewRef != null) {
            View view = this.mInflatedViewRef.get();
            if (view == null) {
                throw new IllegalStateException("setVisibility called on un-referenced view");
            }
            view.setVisibility(n2);
            return;
        } else {
            super.setVisibility(n2);
            if (n2 != 0 && n2 != 4) return;
            {
                this.inflate();
                return;
            }
        }
    }

    public static interface OnInflateListener {
        public void onInflate(ViewStubCompat var1, View var2);
    }

}

