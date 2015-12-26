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

public abstract class arl {
    protected final RecyclerView.h a;
    private int b = Integer.MIN_VALUE;

    private arl(RecyclerView.h h2) {
        this.a = h2;
    }

    /* synthetic */ arl(RecyclerView.h h2, arm arm2) {
        this(h2);
    }

    public static arl a(RecyclerView.h h2) {
        return new arm(h2);
    }

    public static arl a(RecyclerView.h h2, int n2) {
        switch (n2) {
            default: {
                throw new IllegalArgumentException("invalid orientation");
            }
            case 0: {
                return arl.a(h2);
            }
            case 1: 
        }
        return arl.b(h2);
    }

    public static arl b(RecyclerView.h h2) {
        return new arn(h2);
    }

    public abstract int a(View var1);

    public void a() {
        this.b = this.f();
    }

    public abstract void a(int var1);

    public int b() {
        if (Integer.MIN_VALUE == this.b) {
            return 0;
        }
        return this.f() - this.b;
    }

    public abstract int b(View var1);

    public abstract int c();

    public abstract int c(View var1);

    public abstract int d();

    public abstract int d(View var1);

    public abstract int e();

    public abstract int f();

    public abstract int g();
}

