/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

public class RtlSpacingHelper {
    public static final int UNDEFINED = Integer.MIN_VALUE;
    private int mEnd = Integer.MIN_VALUE;
    private int mExplicitLeft = 0;
    private int mExplicitRight = 0;
    private boolean mIsRelative = false;
    private boolean mIsRtl = false;
    private int mLeft = 0;
    private int mRight = 0;
    private int mStart = Integer.MIN_VALUE;

    public int getEnd() {
        if (this.mIsRtl) {
            return this.mLeft;
        }
        return this.mRight;
    }

    public int getLeft() {
        return this.mLeft;
    }

    public int getRight() {
        return this.mRight;
    }

    public int getStart() {
        if (this.mIsRtl) {
            return this.mRight;
        }
        return this.mLeft;
    }

    public void setAbsolute(int n2, int n3) {
        this.mIsRelative = false;
        if (n2 != Integer.MIN_VALUE) {
            this.mExplicitLeft = n2;
            this.mLeft = n2;
        }
        if (n3 != Integer.MIN_VALUE) {
            this.mExplicitRight = n3;
            this.mRight = n3;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setDirection(boolean bl2) {
        if (bl2 == this.mIsRtl) {
            return;
        }
        this.mIsRtl = bl2;
        if (!this.mIsRelative) {
            this.mLeft = this.mExplicitLeft;
            this.mRight = this.mExplicitRight;
            return;
        }
        if (bl2) {
            int n2 = this.mEnd != Integer.MIN_VALUE ? this.mEnd : this.mExplicitLeft;
            this.mLeft = n2;
            n2 = this.mStart != Integer.MIN_VALUE ? this.mStart : this.mExplicitRight;
            this.mRight = n2;
            return;
        }
        int n3 = this.mStart != Integer.MIN_VALUE ? this.mStart : this.mExplicitLeft;
        this.mLeft = n3;
        n3 = this.mEnd != Integer.MIN_VALUE ? this.mEnd : this.mExplicitRight;
        this.mRight = n3;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setRelative(int n2, int n3) {
        this.mStart = n2;
        this.mEnd = n3;
        this.mIsRelative = true;
        if (this.mIsRtl) {
            if (n3 != Integer.MIN_VALUE) {
                this.mLeft = n3;
            }
            if (n2 == Integer.MIN_VALUE) return;
            {
                this.mRight = n2;
                return;
            }
        } else {
            if (n2 != Integer.MIN_VALUE) {
                this.mLeft = n2;
            }
            if (n3 == Integer.MIN_VALUE) return;
            {
                this.mRight = n3;
                return;
            }
        }
    }
}

