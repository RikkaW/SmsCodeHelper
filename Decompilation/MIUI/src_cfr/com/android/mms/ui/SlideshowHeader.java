/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.widget.LinearLayout
 *  java.lang.Math
 */
package com.android.mms.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class SlideshowHeader
extends LinearLayout {
    private View mHomeView;
    private View mSlideBtnView;
    private int mStatusBarHeight = 0;
    private View mSubTitleView;
    private View mTitleView;

    public SlideshowHeader(Context context) {
        super(context);
    }

    public SlideshowHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SlideshowHeader(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHomeView = this.findViewById(2131820651);
        this.mTitleView = this.findViewById(2131820652);
        this.mSubTitleView = this.findViewById(2131820653);
        this.mSlideBtnView = this.findViewById(2131820654);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        super.onLayout(bl, n, n2, n3, n4);
        int n5 = n2 + this.mStatusBarHeight;
        n2 = this.mSubTitleView.getVisibility() == 0 ? (n4 - n5 - this.mTitleView.getMeasuredHeight() - this.mSubTitleView.getMeasuredHeight()) / 2 : (n4 - n5 - this.mTitleView.getMeasuredHeight()) / 2;
        n2 = Math.max((int)(this.mTitleView.getMeasuredHeight() / 2 + n2 - this.mHomeView.getMeasuredHeight() / 2), (int)0) + this.mStatusBarHeight;
        this.mHomeView.layout(n, n2, this.mHomeView.getMeasuredWidth() + n, this.mHomeView.getMeasuredHeight() + n2);
        n = Math.max((int)((n4 - n5 - this.mSlideBtnView.getMeasuredHeight()) / 2), (int)0) + this.mStatusBarHeight;
        this.mSlideBtnView.layout(n3 - this.mSlideBtnView.getMeasuredWidth(), n, n3, this.mSlideBtnView.getMeasuredHeight() + n);
    }

    public void setStatusBarHeight(int n) {
        this.mStatusBarHeight = n;
    }
}

