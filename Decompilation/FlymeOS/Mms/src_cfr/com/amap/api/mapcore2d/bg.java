/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Point
 *  android.os.RemoteException
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 *  android.widget.ListView
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Point;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.ay;
import com.amap.api.mapcore2d.br;
import com.amap.api.mapcore2d.cn;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.x;
import com.amap.api.maps2d.model.LatLng;

class bg
extends ViewGroup {
    private ag a;

    public bg(Context context, ag ag2) {
        super(context);
        this.a = ag2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(View view, int n2, int n3, int n4, int n5, int n6) {
        int n7 = n6 & 7;
        int n8 = n6 & 112;
        if (n7 == 5) {
            n6 = n4 - n2;
        } else {
            n6 = n4;
            if (n7 == 1) {
                n6 = n4 - n2 / 2;
            }
        }
        if (n8 == 80) {
            n4 = n5 - n3;
        } else {
            n4 = n5;
            if (n8 == 16) {
                n4 = n5 - n3 / 2;
            }
        }
        view.layout(n6, n4, n6 + n2, n4 + n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(View view, int n2, int n3, int[] arrn) {
        View view2;
        if (view instanceof ListView && (view2 = (View)view.getParent()) != null) {
            arrn[0] = view2.getWidth();
            arrn[1] = view2.getHeight();
        }
        if (n2 <= 0 || n3 <= 0) {
            view.measure(0, 0);
        }
        arrn[0] = n2 == -2 ? view.getMeasuredWidth() : (n2 == -1 ? this.getMeasuredWidth() : n2);
        if (n3 == -2) {
            arrn[1] = view.getMeasuredHeight();
            return;
        }
        if (n3 == -1) {
            arrn[1] = this.getMeasuredHeight();
            return;
        }
        arrn[1] = n3;
    }

    private void a(View view, a a2) {
        int[] arrn = new int[2];
        this.a(view, a2.width, a2.height, arrn);
        this.a(view, arrn[0], arrn[1], a2.c, a2.d, a2.e);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(cn cn2, int[] arrn, int n2) {
        int n3 = cn2.b();
        if (n3 == 1) {
            this.a((View)cn2, arrn[0], arrn[1], this.getWidth() - arrn[0], (this.getHeight() + arrn[1]) / 2, n2);
            return;
        } else {
            if (n3 != 0) return;
            {
                this.a((View)cn2, arrn[0], arrn[1], this.getWidth() - arrn[0], this.getHeight(), n2);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void b(View view, a a2) {
        int[] arrn;
        block5 : {
            arrn = new int[2];
            this.a(view, a2.width, a2.height, arrn);
            if (view instanceof cn) {
                this.a((cn)view, arrn, a2.e);
                return;
            }
            if (view instanceof ay) {
                this.a(view, arrn[0], arrn[1], this.getWidth() - arrn[0], arrn[1], a2.e);
                return;
            }
            if (view instanceof x) {
                this.a(view, arrn[0], arrn[1], 0, 0, a2.e);
                return;
            }
            if (a2.b == null) return;
            ae ae2 = new ae((int)(a2.b.latitude * 1000000.0), (int)(a2.b.longitude * 1000000.0));
            try {
                ae2 = this.a.s().a(ae2, null);
                if (ae2 != null) break block5;
            }
            catch (RemoteException var3_5) {
                cy.a((Throwable)var3_5, "MapOverlayViewGroup", "layoutMap");
                return;
            }
            return;
        }
        ae2.x += a2.c;
        ae2.y += a2.d;
        this.a(view, arrn[0], arrn[1], ae2.x, ae2.y, a2.e);
    }

    public void a() {
        this.onLayout(false, 0, 0, 0, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        n3 = this.getChildCount();
        n2 = 0;
        while (n2 < n3) {
            View view = this.getChildAt(n2);
            if (view != null) {
                if (view.getLayoutParams() instanceof a) {
                    a a2 = (a)view.getLayoutParams();
                    if (a2.a == 0) {
                        this.b(view, a2);
                    } else {
                        this.a(view, a2);
                    }
                } else {
                    this.a(view, new a(view.getLayoutParams()));
                }
            }
            ++n2;
        }
    }

    public static class a
    extends ViewGroup.LayoutParams {
        public int a = 1;
        public LatLng b = null;
        public int c = 0;
        public int d = 0;
        public int e = 51;

        public a(int n2, int n3, LatLng latLng, int n4, int n5, int n6) {
            super(n2, n3);
            this.a = 0;
            this.b = latLng;
            this.c = n4;
            this.d = n5;
            this.e = n6;
        }

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

}

