/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Math
 *  java.lang.Object
 */
import android.view.View;
import flyme.support.v7.widget.RecyclerView;

public class arr {
    public static int a(RecyclerView.q q2, arl arl2, View view, View view2, RecyclerView.h h2, boolean bl2) {
        if (h2.r() == 0 || q2.f() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!bl2) {
            return Math.abs((int)(h2.d(view) - h2.d(view2))) + 1;
        }
        int n2 = arl2.b(view2);
        int n3 = arl2.a(view);
        return Math.min((int)arl2.f(), (int)(n2 - n3));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int a(RecyclerView.q q2, arl arl2, View view, View view2, RecyclerView.h h2, boolean bl2, boolean bl3) {
        int n2;
        int n3 = n2 = 0;
        if (h2.r() == 0) return n3;
        n3 = n2;
        if (q2.f() == 0) return n3;
        n3 = n2;
        if (view == null) return n3;
        if (view2 == null) {
            return n2;
        }
        n2 = Math.min((int)h2.d(view), (int)h2.d(view2));
        n3 = Math.max((int)h2.d(view), (int)h2.d(view2));
        n2 = bl3 ? Math.max((int)0, (int)(q2.f() - n3 - 1)) : Math.max((int)0, (int)n2);
        n3 = n2;
        if (!bl2) return n3;
        n3 = Math.abs((int)(arl2.b(view2) - arl2.a(view)));
        int n4 = Math.abs((int)(h2.d(view) - h2.d(view2)));
        float f2 = (float)n3 / (float)(n4 + 1);
        return Math.round((float)((float)n2 * f2 + (float)(arl2.c() - arl2.a(view))));
    }

    public static int b(RecyclerView.q q2, arl arl2, View view, View view2, RecyclerView.h h2, boolean bl2) {
        if (h2.r() == 0 || q2.f() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!bl2) {
            return q2.f();
        }
        int n2 = arl2.b(view2);
        int n3 = arl2.a(view);
        int n4 = Math.abs((int)(h2.d(view) - h2.d(view2)));
        return (int)((float)(n2 - n3) / (float)(n4 + 1) * (float)q2.f());
    }
}

