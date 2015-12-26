/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.AbsListView
 *  java.lang.Object
 */
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.TintTypedArray;
import android.view.View;
import android.widget.AbsListView;

public class arb
extends ara {
    private Context b;
    private int c;
    private int d;

    public arb(AbsListView object, Context context, int n2) {
        super((AbsListView)object);
        this.b = context;
        object = TintTypedArray.obtainStyledAttributes(this.b, null, R.styleable.MzListViewProxy, n2, 0);
        this.c = object.getDimensionPixelSize(R.styleable.MzListViewProxy_mzDividerPaddingStart, 0);
        this.d = object.getDimensionPixelSize(R.styleable.MzListViewProxy_mzDividerPaddingEnd, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public int[] a(int n2) {
        n2 = ViewCompat.getLayoutDirection((View)this.a) == 1 ? 1 : 0;
        int[] arrn = new int[2];
        if (n2 != 0) {
            arrn[0] = this.d;
            arrn[1] = this.c;
            return arrn;
        }
        arrn[1] = this.d;
        arrn[0] = this.c;
        return arrn;
    }
}

