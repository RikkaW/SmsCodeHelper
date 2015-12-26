/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.android.mms.ui;

import com.android.mms.ui.SlideViewInterface;

public interface AdaptableSlideViewInterface
extends SlideViewInterface {
    public void setImageRegion(int var1, int var2, int var3, int var4);

    public void setOnSizeChangedListener(OnSizeChangedListener var1);

    public void setTextRegion(int var1, int var2, int var3, int var4);

    public void setVideoRegion(int var1, int var2, int var3, int var4);

    public static interface OnSizeChangedListener {
        public void onSizeChanged(int var1, int var2);
    }

}

