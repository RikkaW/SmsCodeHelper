/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v4.view;

import android.view.View;

public interface NestedScrollingParent {
    public int getNestedScrollAxes();

    public boolean onNestedFling(View var1, float var2, float var3, boolean var4);

    public boolean onNestedPreFling(View var1, float var2, float var3);

    public void onNestedPreScroll(View var1, int var2, int var3, int[] var4);

    public void onNestedScroll(View var1, int var2, int var3, int var4, int var5);

    public void onNestedScrollAccepted(View var1, View var2, int var3);

    public boolean onStartNestedScroll(View var1, View var2, int var3);

    public void onStopNestedScroll(View var1);
}

