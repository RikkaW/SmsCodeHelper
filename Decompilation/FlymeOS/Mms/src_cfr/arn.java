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

final class arn
extends arl {
    arn(RecyclerView.h h2) {
        super(h2, null);
    }

    @Override
    public int a(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        return this.a.h(view) - layoutParams.topMargin;
    }

    @Override
    public void a(int n2) {
        this.a.g(n2);
    }

    @Override
    public int b(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        int n2 = this.a.j(view);
        return layoutParams.bottomMargin + n2;
    }

    @Override
    public int c() {
        return this.a.v();
    }

    @Override
    public int c(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        int n2 = this.a.f(view);
        int n3 = layoutParams.topMargin;
        return layoutParams.bottomMargin + (n2 + n3);
    }

    @Override
    public int d() {
        return this.a.t() - this.a.x();
    }

    @Override
    public int d(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        int n2 = this.a.e(view);
        int n3 = layoutParams.leftMargin;
        return layoutParams.rightMargin + (n2 + n3);
    }

    @Override
    public int e() {
        return this.a.t();
    }

    @Override
    public int f() {
        return this.a.t() - this.a.v() - this.a.x();
    }

    @Override
    public int g() {
        return this.a.x();
    }
}

