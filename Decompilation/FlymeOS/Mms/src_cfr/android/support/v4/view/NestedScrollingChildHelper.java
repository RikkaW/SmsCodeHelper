/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewParent
 *  java.lang.Object
 */
package android.support.v4.view;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.view.View;
import android.view.ViewParent;

public class NestedScrollingChildHelper {
    private boolean mIsNestedScrollingEnabled;
    private ViewParent mNestedScrollingParent;
    private int[] mTempNestedScrollConsumed;
    private final View mView;

    public NestedScrollingChildHelper(View view) {
        this.mView = view;
    }

    public boolean dispatchNestedFling(float f2, float f3, boolean bl2) {
        if (this.isNestedScrollingEnabled() && this.mNestedScrollingParent != null) {
            return ViewParentCompat.onNestedFling(this.mNestedScrollingParent, this.mView, f2, f3, bl2);
        }
        return false;
    }

    public boolean dispatchNestedPreFling(float f2, float f3) {
        if (this.isNestedScrollingEnabled() && this.mNestedScrollingParent != null) {
            return ViewParentCompat.onNestedPreFling(this.mNestedScrollingParent, this.mView, f2, f3);
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean dispatchNestedPreScroll(int n2, int n3, int[] arrn, int[] arrn2) {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (!this.isNestedScrollingEnabled()) return bl3;
        bl3 = bl2;
        if (this.mNestedScrollingParent == null) return bl3;
        if (n2 != 0 || n3 != 0) {
            int n4;
            int n5;
            if (arrn2 != null) {
                this.mView.getLocationInWindow(arrn2);
                n4 = arrn2[0];
                n5 = arrn2[1];
            } else {
                n5 = 0;
                n4 = 0;
            }
            int[] arrn3 = arrn;
            if (arrn == null) {
                if (this.mTempNestedScrollConsumed == null) {
                    this.mTempNestedScrollConsumed = new int[2];
                }
                arrn3 = this.mTempNestedScrollConsumed;
            }
            arrn3[0] = 0;
            arrn3[1] = 0;
            ViewParentCompat.onNestedPreScroll(this.mNestedScrollingParent, this.mView, n2, n3, arrn3);
            if (arrn2 != null) {
                this.mView.getLocationInWindow(arrn2);
                arrn2[0] = arrn2[0] - n4;
                arrn2[1] = arrn2[1] - n5;
            }
            if (arrn3[0] != 0) return true;
            bl3 = bl2;
            if (arrn3[1] == 0) return bl3;
            return true;
        }
        bl3 = bl2;
        if (arrn2 == null) return bl3;
        arrn2[0] = 0;
        arrn2[1] = 0;
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean dispatchNestedScroll(int n2, int n3, int n4, int n5, int[] arrn) {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (!this.isNestedScrollingEnabled()) return bl3;
        bl3 = bl2;
        if (this.mNestedScrollingParent == null) return bl3;
        if (n2 != 0 || n3 != 0 || n4 != 0 || n5 != 0) {
            int n6;
            int n7;
            if (arrn != null) {
                this.mView.getLocationInWindow(arrn);
                n7 = arrn[0];
                n6 = arrn[1];
            } else {
                n6 = 0;
                n7 = 0;
            }
            ViewParentCompat.onNestedScroll(this.mNestedScrollingParent, this.mView, n2, n3, n4, n5);
            if (arrn == null) return true;
            this.mView.getLocationInWindow(arrn);
            arrn[0] = arrn[0] - n7;
            arrn[1] = arrn[1] - n6;
            return true;
        }
        bl3 = bl2;
        if (arrn == null) return bl3;
        arrn[0] = 0;
        arrn[1] = 0;
        return false;
    }

    public boolean hasNestedScrollingParent() {
        if (this.mNestedScrollingParent != null) {
            return true;
        }
        return false;
    }

    public boolean isNestedScrollingEnabled() {
        return this.mIsNestedScrollingEnabled;
    }

    public void onDetachedFromWindow() {
        ViewCompat.stopNestedScroll(this.mView);
    }

    public void onStopNestedScroll(View view) {
        ViewCompat.stopNestedScroll(this.mView);
    }

    public void setNestedScrollingEnabled(boolean bl2) {
        if (this.mIsNestedScrollingEnabled) {
            ViewCompat.stopNestedScroll(this.mView);
        }
        this.mIsNestedScrollingEnabled = bl2;
    }

    public boolean startNestedScroll(int n2) {
        if (this.hasNestedScrollingParent()) {
            return true;
        }
        if (this.isNestedScrollingEnabled()) {
            View view = this.mView;
            for (ViewParent viewParent = this.mView.getParent(); viewParent != null; viewParent = viewParent.getParent()) {
                if (ViewParentCompat.onStartNestedScroll(viewParent, view, this.mView, n2)) {
                    this.mNestedScrollingParent = viewParent;
                    ViewParentCompat.onNestedScrollAccepted(viewParent, view, this.mView, n2);
                    return true;
                }
                if (!(viewParent instanceof View)) continue;
                view = (View)viewParent;
            }
        }
        return false;
    }

    public void stopNestedScroll() {
        if (this.mNestedScrollingParent != null) {
            ViewParentCompat.onStopNestedScroll(this.mNestedScrollingParent, this.mView);
            this.mNestedScrollingParent = null;
        }
    }
}

