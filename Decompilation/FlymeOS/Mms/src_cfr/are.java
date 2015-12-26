/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
import android.view.View;
import flyme.support.v7.widget.RecyclerView;

public class are {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e = 0;
    public int f = 0;

    public View a(RecyclerView.m m2) {
        m2 = m2.b(this.b);
        this.b += this.c;
        return m2;
    }

    public boolean a(RecyclerView.q q2) {
        if (this.b >= 0 && this.b < q2.f()) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.a + ", mCurrentPosition=" + this.b + ", mItemDirection=" + this.c + ", mLayoutDirection=" + this.d + ", mStartLine=" + this.e + ", mEndLine=" + this.f + '}';
    }
}

