/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 */
import android.view.View;
import android.view.ViewGroup;
import flyme.support.v7.widget.RecyclerView;

final class arm
extends arl {
    arm(RecyclerView.h h2) {
        super(h2, null);
    }

    @Override
    public int a(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        return this.a.g(view) - layoutParams.leftMargin;
    }

    @Override
    public void a(int n2) {
        this.a.f(n2);
    }

    @Override
    public int b(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        int n2 = this.a.i(view);
        return layoutParams.rightMargin + n2;
    }

    @Override
    public int c() {
        return this.a.u();
    }

    @Override
    public int c(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        int n2 = this.a.e(view);
        int n3 = layoutParams.leftMargin;
        return layoutParams.rightMargin + (n2 + n3);
    }

    @Override
    public int d() {
        return this.a.s() - this.a.w();
    }

    @Override
    public int d(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        int n2 = this.a.f(view);
        int n3 = layoutParams.topMargin;
        return layoutParams.bottomMargin + (n2 + n3);
    }

    @Override
    public int e() {
        return this.a.s();
    }

    @Override
    public int f() {
        return this.a.s() - this.a.u() - this.a.w();
    }

    @Override
    public int g() {
        return this.a.w();
    }
}

